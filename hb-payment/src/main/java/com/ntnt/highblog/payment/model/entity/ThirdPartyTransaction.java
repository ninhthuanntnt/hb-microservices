package com.ntnt.highblog.payment.model.entity;

import com.ntnt.highblog.payment.enums.CurrencyType;
import com.ntnt.highblog.payment.enums.ThirdPartyTransactionStatus;
import com.ntnt.highblog.payment.model.converter.MapToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "hb_third_party_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ThirdPartyTransaction
    extends AbstractAuditingColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "payment_id")
    private String paymentId;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @Builder.Default
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type")
    private CurrencyType currencyType = CurrencyType.USD;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ThirdPartyTransactionStatus status;

    @Column(name = "fee")
    private BigDecimal fee;

    @Column(name = "additional_params", columnDefinition = "json")
    @Convert(attributeName = "params", converter = MapToJsonConverter.class)
    private Map<String, Object> additionalParams;
}
