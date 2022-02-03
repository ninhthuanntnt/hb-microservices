package com.ntnt.highblog.payment.service;

import com.ntnt.highblog.payment.error.exception.ValidatorException;
import com.ntnt.highblog.payment.model.entity.SystemTransaction;
import com.ntnt.highblog.payment.repository.SystemTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemTransactionService {

    private SystemTransactionRepository repository;

    public SystemTransactionService(final SystemTransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveNew(final SystemTransaction systemTransaction) {
        log.info("Save new systemTransaction with data #{}", systemTransaction);

        validateBeforeSaveNew(systemTransaction);

        repository.save(systemTransaction);
    }

    public void validateBeforeSaveNew(final SystemTransaction systemTransaction) {
        if (ObjectUtils.isNotEmpty(systemTransaction.getId())) {
            throw new ValidatorException("Invalid system transaction", "systemTransaction");
        }
    }

    @Transactional(readOnly = true)
    public List<SystemTransaction> fetchSystemTransactions(Long senderId) {
        log.info("fetch system transaction by senderId");
        return repository.fetchBySenderTransactionId(senderId);
    }

    @Transactional(readOnly = true)
    public boolean existByReceiverId(final Long receiverId) {
        log.info("exist by receiverId");
        return repository.existsByReceiverTransactionId(receiverId);
    }
}
