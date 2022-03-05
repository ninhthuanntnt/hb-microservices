package com.ntnt.highblog.uaa.bloc;

import com.ntnt.highblog.uaa.api.client.HbDmmClient;
import com.ntnt.highblog.uaa.api.client.HbPaymentClient;
import com.ntnt.highblog.uaa.enums.CodeType;
import com.ntnt.highblog.uaa.enums.RoleType;
import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.error.exception.ValidatorException;
import com.ntnt.highblog.uaa.helper.SecurityHelper;
import com.ntnt.highblog.uaa.model.dto.request.RegisterReq;
import com.ntnt.highblog.uaa.model.dto.request.ResendEmailReq;
import com.ntnt.highblog.uaa.model.dto.request.WalletCreateReq;
import com.ntnt.highblog.uaa.model.dto.response.UserRes;
import com.ntnt.highblog.uaa.model.entity.Account;
import com.ntnt.highblog.uaa.model.entity.ConfirmationCode;
import com.ntnt.highblog.uaa.model.entity.Role;
import com.ntnt.highblog.uaa.service.AccountRoleService;
import com.ntnt.highblog.uaa.service.AccountService;
import com.ntnt.highblog.uaa.service.ConfirmationCodeService;
import com.ntnt.highblog.uaa.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class RegisterBloc {
    private final AccountService accountService;
    private final AccountRoleService accountRoleService;
    private final RoleService roleService;
    private final ConfirmationCodeService confirmationCodeService;

    private final HbDmmClient hbDmmClient;
    private final HbPaymentClient hbPaymentClient;

    private final MailBloc mailBloc;
    private final static RoleType DEFAULT_ACCOUNT_ROLE = RoleType.ROLE_INACTIVE_USER;

    public RegisterBloc(final AccountService accountService,
                        final AccountRoleService accountRoleService,
                        final RoleService roleService,
                        final ConfirmationCodeService confirmationCodeService,
                        final HbDmmClient hbDmmClient,
                        final HbPaymentClient hbPaymentClient,
                        final MailBloc mailBloc) {
        this.accountService = accountService;
        this.accountRoleService = accountRoleService;
        this.roleService = roleService;
        this.confirmationCodeService = confirmationCodeService;
        this.hbDmmClient = hbDmmClient;
        this.hbPaymentClient = hbPaymentClient;

        this.mailBloc = mailBloc;
    }

    @Transactional
    public Long register(final RegisterReq registerReq) {

        validateRegisterReq(registerReq);

        ResponseEntity<UserRes> responseEntity = hbDmmClient.createUser(registerReq);
        if (responseEntity.getStatusCode().isError()) {
            throw new ValidatorException("Cannot save", "user");
        } else {
            UserRes userRes = responseEntity.getBody();
            Account account = Account.builder()
                                     .userId(userRes.getId())
                                     .username(registerReq.getUsername())
                                     .password(registerReq.getPassword())
                                     .email(registerReq.getEmail())
                                     .build();
            accountService.saveNew(account);
            ResponseEntity<Void> walletRes = hbPaymentClient.createWallet(WalletCreateReq.builder()
                                                                                         .userId(userRes.getId())
                                                                                         .build());
            if(walletRes.getStatusCode().isError()) {
                throw new ValidatorException("Cannot create", "wallet");
            }

            Role role = roleService.getRoleByRoleType(DEFAULT_ACCOUNT_ROLE);
            accountRoleService.saveNew(account.getId(), Collections.singletonList(role.getId()));

            ConfirmationCode confirmationCode = confirmationCodeService.saveNew(account.getId(),
                                                                                CodeType.REGISTRATION);

            mailBloc.sendConfirmRegistrationMailTo(registerReq.getEmail(),
                                                   registerReq.getReturnUrl(),
                                                   confirmationCode);
            return confirmationCode.getId();
        }
    }

    @Transactional
    public void activateAccount(final Long confirmationCodeId, final String code) {
        log.info("Activate account with confirmationCodeId #{} and code #{}", confirmationCodeId, code);

        ConfirmationCode confirmationCode = confirmationCodeService.getByIdAndCodeAndType(confirmationCodeId,
                                                                                          code,
                                                                                          CodeType.REGISTRATION);
        validateConfirmationCode(confirmationCode);

        Role role = roleService.getRoleByRoleType(RoleType.ROLE_USER);
        accountRoleService.deleteOldAndSaveNew(confirmationCode.getAccountId(),
                                               Collections.singletonList(role.getId()));

        confirmationCodeService.inactivateConfirmationCode(confirmationCodeId);
    }

    @Transactional
    public void resendConfirmRegistration(final Long previousConfirmationCodeId,
                                          final ResendEmailReq resendEmailReq) {
        log.info("Resend confirm registration email by previousConfirmationCodeId #{}", previousConfirmationCodeId);

        Account account;
        if (previousConfirmationCodeId == null) {
            Long accountId = SecurityHelper.getCurrentAccountId();
            account = accountService.getAccountById(accountId);

            List<ConfirmationCode> previousCodes = confirmationCodeService.fetchByAccountIdAndCodeType(accountId,
                                                                                                       CodeType.REGISTRATION);

            confirmationCodeService.inactivateListConfirmationCode(previousCodes);

        } else {
            ConfirmationCode previousConfirmationCode =
                confirmationCodeService.getByIdAndType(previousConfirmationCodeId,
                                                       CodeType.REGISTRATION);
            validatePreviousConfirmationCode(previousConfirmationCode);

            confirmationCodeService.inactivateConfirmationCode(previousConfirmationCodeId);

            account = accountService.getAccountById(previousConfirmationCode.getAccountId());
        }


        ConfirmationCode newConfirmationCode = confirmationCodeService.saveNew(account.getId(),
                                                                               CodeType.REGISTRATION);
        mailBloc.sendConfirmRegistrationMailTo(account.getEmail(),
                                               resendEmailReq.getReturnUrl(),
                                               newConfirmationCode);
    }

    private void validateConfirmationCode(final ConfirmationCode confirmationCode) {
        Long accountId = confirmationCode.getAccountId();

        if (ObjectUtils.isEmpty(accountId)) {
            throw new ObjectNotFoundException("accountId");
        } else if (SecurityHelper.getNullableCurrentUsername().isPresent()
            && ObjectUtils.notEqual(SecurityHelper.getCurrentAccountId(), accountId)) {
            // For use-case user login into system then activate account
            throw new ValidatorException("Mismatch account id", "accountId");
        } else if (confirmationCode.isExpired()) {
            throw new ValidatorException("Expired confirmation code", "confirmationCode");
        } else if (!confirmationCode.isActivated()) {
            throw new ValidatorException("Inactivated confirmation code", "confirmationCode");
        }
    }

    private void validatePreviousConfirmationCode(final ConfirmationCode confirmationCode) {
        Long accountId = confirmationCode.getAccountId();

        if (ObjectUtils.isEmpty(accountId)) {
            throw new ObjectNotFoundException("accountId");
        } else if (!confirmationCode.isExpired()) {
            throw new ValidatorException("Unexpired confirmation code", "confirmationCode");
        }
    }

    private void validateRegisterReq(final RegisterReq registerReq) {
        if (accountService.isExistAccountByUsername(registerReq.getUsername()))
            throw new ValidatorException("Duplicate username", "username");
        else if (accountService.existAccountByEmail(registerReq.getEmail()))
            throw new ValidatorException("Duplicate email", "email");
    }
}
