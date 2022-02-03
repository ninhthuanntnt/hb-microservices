package com.ntnt.highblog.payment.service.payment;

import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;

import java.math.BigDecimal;

public interface PaymentService {
    ThirdPartyTransaction createPayment(BigDecimal amount);

    ThirdPartyTransaction executePayment(String paymentId);

    ThirdPartyTransaction cancelPayment(String paymentId);

    ThirdPartyTransaction withdraw(String email, BigDecimal amount);
}
