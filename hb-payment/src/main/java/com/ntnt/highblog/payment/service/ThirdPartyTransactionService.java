package com.ntnt.highblog.payment.service;

import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import com.ntnt.highblog.payment.repository.ThirdPartyTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ThirdPartyTransactionService {
    private final ThirdPartyTransactionRepository repository;

    public ThirdPartyTransactionService(final ThirdPartyTransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ThirdPartyTransaction saveNew(final ThirdPartyTransaction thirdPartyTransaction) {
        log.info("Save new thirdPartyTransaction with data #{}", thirdPartyTransaction);

        return repository.save(thirdPartyTransaction);
    }
}
