package com.ntnt.highblog.payment.service;

import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import com.ntnt.highblog.payment.error.exception.ValidatorException;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import com.ntnt.highblog.payment.repository.UserTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class UserTransactionService {
    private final UserTransactionRepository repository;

    public UserTransactionService(final UserTransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public UserTransaction getNullableByPaymentId(final String paymentId) {
        log.info("Get by paymentId #{}", paymentId);

        return repository.findByPaymentId(paymentId)
                         .orElse(null);
    }

    @Transactional
    public void saveNew(final UserTransaction userTransaction) {
        log.info("Save new userTransaction with data #{}", userTransaction);

        validateBeforeSaveNew(userTransaction);

        repository.save(userTransaction);
    }

    @Transactional
    public void saveStatus(final UserTransaction userTransaction, final UserTransactionStatus status) {
        log.info("Save userTransaction with data #{}", userTransaction);

        validateBeforeSaveStatus(userTransaction);
        userTransaction.setStatus(status);

        repository.save(userTransaction);
    }

    public void validateBeforeSaveNew(final UserTransaction userTransaction) {
        if (ObjectUtils.isNotEmpty(userTransaction.getId())) {
            throw new ValidatorException("Invalid user transaction", "userTransaction");
        }
    }

    public void validateBeforeSaveStatus(final UserTransaction userTransaction) {
        if (ObjectUtils.isEmpty(userTransaction.getId())) {
            throw new ValidatorException("Invalid user transaction", "userTransaction");
        }
        if (userTransaction.getStatus().isFinalStatus()) {
            throw new ValidatorException("Transaction already completed", "userTransaction");
        }
    }

    @Transactional(readOnly = true)
    public Page<UserTransaction> fetchAllByUserId(Long currentUserId, PageRequest pageRequest) {
        return repository.findAllByUserId(currentUserId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<UserTransaction> searchDynamicTransactions(Long userId, String transactionNo, Instant startDate, Instant endDate, PageRequest pageRequest) {
        return repository.searchDynamicTransactions(userId, transactionNo, startDate, endDate, pageRequest);
    }

    @Transactional(readOnly = true)
    public List<UserTransaction> fetchByStatusIn(final List<UserTransactionStatus> statuses) {
        log.info("Fetch list userTransactions by status in #{}", statuses);

        return repository.fetchListUserTransactionByStatusIn(statuses);
    }
}
