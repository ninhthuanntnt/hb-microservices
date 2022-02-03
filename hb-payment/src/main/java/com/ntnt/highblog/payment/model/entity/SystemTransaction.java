package com.ntnt.highblog.payment.model.entity;

import com.ntnt.highblog.payment.enums.SystemTransactionStatus;
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
@Table(name = "hb_system_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SystemTransaction
    extends AbstractAuditingColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "sender_transaction_id")
    private Long senderTransactionId;

    @NotNull
    @Column(name = "receiver_transaction_id")
    private Long receiverTransactionId;

    @Column(name = "third_party_transaction_id")
    private Long thirdPartyTransactionId;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private SystemTransactionStatus status;

    @NotNull
    @Builder.Default
    @Column(name = "fee_rate")
    private Float feeRate = 0F;

    @Transient
    private ThirdPartyTransaction thirdPartyTransaction;

    public SystemTransaction(final SystemTransaction systemTransaction,
                             final ThirdPartyTransaction thirdPartyTransaction) {
        this.amount = systemTransaction.amount;
        this.feeRate = systemTransaction.feeRate;
        this.receiverTransactionId = systemTransaction.receiverTransactionId;
        this.senderTransactionId = systemTransaction.senderTransactionId;
        this.id = systemTransaction.id;
        this.status = systemTransaction.status;
        this.thirdPartyTransactionId = systemTransaction.thirdPartyTransactionId;

        this.thirdPartyTransaction = thirdPartyTransaction;
    }

}
