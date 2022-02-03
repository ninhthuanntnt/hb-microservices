package com.ntnt.highblog.payment.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ntnt.highblog.payment.enums.PaymentMethod;
import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTransactionRes {

    private Long id;

    private Long userId;

    private String transactionNo;

    private BigDecimal amount;

    private UserTransactionStatus status;

    private PaymentMethod paymentMethod;

    private PaymentType paymentType;

    private boolean isReceiver;

    private BigDecimal balance;

    private Instant createdDate;

}
