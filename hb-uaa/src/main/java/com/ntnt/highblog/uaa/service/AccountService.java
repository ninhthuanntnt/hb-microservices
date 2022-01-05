package com.ntnt.highblog.uaa.service;

import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.error.exception.ValidatorException;
import com.ntnt.highblog.uaa.model.entity.Account;
import com.ntnt.highblog.uaa.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(final AccountRepository accountRepository,
                          final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional(readOnly = true)
    public boolean isExistAccountByUsername(final String username) {
        log.info("Check whether exists account with username #{}", username);

        return accountRepository.findByUsername(username)
                                .isPresent();
    }

    @Transactional(readOnly = true)
    public boolean existAccountByEmail(final String email) {
        log.info("Check whether exists account with email #{}", email);

        return accountRepository.findByEmail(email)
                                .isPresent();
    }

    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        log.info("Get account by id #{}", id);

        return accountRepository.findById(id)
                                .orElseThrow(() -> new ObjectNotFoundException("account"));
    }

    @Transactional(readOnly = true)
    public List<Account> fetchByUserIdIn(List<Long> userIds) {
        log.info("Fetch accounts by userIds in #{}", userIds);

        return accountRepository.findByUserIdIn(userIds);
    }

    @Transactional
    public void saveNew(final Account account) {
        log.info("Save new account with info #{}", account);
        validateAccountBeforeSaveNew(account);

        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);

        accountRepository.save(account);
    }

    private void validateAccountBeforeSaveNew(final Account account) {
        if (Objects.nonNull(account.getId()))
            throw new ValidatorException("Invalid account id", "id");
    }

    @Transactional
    public void savePassword(final Long accountId, final String newPassword) {
        log.info("Save password by accountId #{}", accountId);

        Account account = accountRepository.findById(accountId)
                                           .orElseThrow(() -> new ObjectNotFoundException("account"));

        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Account getAccountByUserId(final Long userId) {
        log.info("get account by userId #{}", userId);

        return accountRepository.findByUserId(userId);
    }
}
