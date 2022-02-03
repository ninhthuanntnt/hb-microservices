package com.ntnt.highblog.payment.repository;

import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyTransactionRepository
    extends JpaRepository<ThirdPartyTransaction, Long> {

    Optional<ThirdPartyTransaction> findFirstByPaymentIdOrderByIdDesc(String paymentId);
}
