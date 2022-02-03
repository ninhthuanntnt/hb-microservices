package com.ntnt.highblog.payment.model.entity;

import com.ntnt.highblog.payment.enums.PaymentMethod;
import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "hb_user_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserTransaction
    extends AbstractAuditingColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_no")
    private String transactionNo;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private UserTransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    private BigDecimal balance;

    @Transient
    private boolean isReceiver;

    @Transient
    private String paymentId;

    public UserTransaction(final Long id,
                           final Long userId,
                           final String transactionNo,
                           final BigDecimal amount,
                           final UserTransactionStatus status,
                           final PaymentMethod paymentMethod,
                           final PaymentType paymentType,
                           final BigDecimal balance,
                           final String paymentId) {
        this.id = id;
        this.userId = userId;
        this.transactionNo = transactionNo;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentType = paymentType;
        this.balance = balance;
        this.paymentId = paymentId;
    }
}
