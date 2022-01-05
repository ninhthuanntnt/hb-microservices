package com.ntnt.highblog.uaa.service;

import com.ntnt.highblog.uaa.constant.Constant;
import com.ntnt.highblog.uaa.enums.CodeType;
import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.error.exception.ValidatorException;
import com.ntnt.highblog.uaa.helper.CodeHelper;
import com.ntnt.highblog.uaa.model.entity.ConfirmationCode;
import com.ntnt.highblog.uaa.repository.ConfirmationCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ConfirmationCodeService {

    private final ConfirmationCodeRepository repository;
    private static final Long DEFAULT_EXPIRATION = Constant.Expiration.CONFIRMATION_CODE_EXPIRATION;

    public ConfirmationCodeService(final ConfirmationCodeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ConfirmationCode getByIdAndType(final Long id, final CodeType codeType) {
        log.info("Get confirmation code by id #{} and type #{}", id, codeType);

        return repository.findByIdAndCodeType(id, codeType)
                         .orElseThrow(() -> new ObjectNotFoundException("confirmationCode"));
    }

    @Transactional(readOnly = true)
    public ConfirmationCode getByIdAndCodeAndType(final Long id, final String code, final CodeType codeType) {
        log.info("Get confirmation code by id #{} and code #{} and codeType #{}", id, code, codeType);

        return repository.findByIdAndCodeAndCodeType(id, code, codeType)
                         .orElseThrow(() -> new ObjectNotFoundException("confirmationCode"));
    }

    @Transactional(readOnly = true)
    public List<ConfirmationCode> fetchByAccountIdAndCodeType(final Long accountId, final CodeType codeType) {
        log.info("Get confirmation code by accountId #{} and codeType #{}", accountId, codeType);

        return repository.findByAccountIdAndCodeType(accountId, codeType);
    }

    @Transactional
    public void saveNew(ConfirmationCode confirmationCode) {
        log.info("Save confirmation code with data #{}", confirmationCode);

        validateConfirmationCodeBeforeSaveNew(confirmationCode);

        repository.save(confirmationCode);
    }

    @Transactional
    public ConfirmationCode saveNew(final Long accountId, final CodeType codeType) {
        String code = CodeHelper.generateUUID();
        log.info("Create confirmation code with code #{} for type #{}", code, codeType.name());

        ConfirmationCode confirmationCode = ConfirmationCode.builder()
                                                            .accountId(accountId)
                                                            .code(code)
                                                            .codeType(codeType)
                                                            .expiration(DEFAULT_EXPIRATION)
                                                            .build();
        this.saveNew(confirmationCode);
        return confirmationCode;
    }

    @Transactional
    public void inactivateConfirmationCode(final Long id) {
        log.info("Inactive confirmation code by Id #{}", id);
        ConfirmationCode confirmationCode = repository.findById(id)
                                                      .orElseThrow(() -> new ObjectNotFoundException("confirmationCode"));

        validateConfirmationCodeBeforeInactive(confirmationCode);

        confirmationCode.setActivated(false);

        repository.save(confirmationCode);
    }

    @Transactional
    public void inactivateConfirmationCode(final ConfirmationCode confirmationCode) {
        log.info("Inactive confirmation code by Id #{}", confirmationCode.getId());

        validateConfirmationCodeBeforeInactive(confirmationCode);

        confirmationCode.setActivated(false);

        repository.save(confirmationCode);
    }

    @Transactional
    public void inactivateListConfirmationCode(final List<ConfirmationCode> confirmationCodes) {
        log.info("Inactive confirmation code by data #{}", confirmationCodes);

        confirmationCodes.forEach(confirmationCode -> {
            confirmationCode.setActivated(false);
        });

        repository.saveAll(confirmationCodes);
    }

    private void validateConfirmationCodeBeforeSaveNew(final ConfirmationCode confirmationCode) {
        if (Objects.nonNull(confirmationCode.getId())) {
            throw new ValidatorException("Invalid confirmation code id", "id");
        }
    }

    private void validateConfirmationCodeBeforeInactive(final ConfirmationCode confirmationCode) {
        if(!confirmationCode.isActivated())
            throw new ValidatorException("Confirmation code already inactivated", "confirmationCode");
    }
}
