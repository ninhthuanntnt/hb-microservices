package com.ntnt.highblog.payment.bloc;

import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.dto.request.PaymentCreateReq;
import com.ntnt.highblog.payment.model.dto.request.PaymentExecuteReq;
import com.ntnt.highblog.payment.model.dto.response.PaymentCreateRes;
import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import com.ntnt.highblog.payment.service.WalletService;
import com.ntnt.highblog.payment.service.payment.PaymentService;
import com.ntnt.highblog.payment.service.payment.PaypalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class PaypalDepositBloc {

    private final PaymentService paymentService;
    private final WalletService walletService;
    private final TransactionBloc transactionBloc;

    public PaypalDepositBloc(final PaypalPaymentService paymentService,
                             final WalletService walletService,
                             final TransactionBloc transactionBloc) {
        this.paymentService = paymentService;
        this.walletService = walletService;
        this.transactionBloc = transactionBloc;
    }

    @Transactional
    public PaymentCreateRes createDeposit(final PaymentCreateReq paymentCreateReq) {
        ThirdPartyTransaction thirdPartyTransaction = paymentService.createPayment(paymentCreateReq.getAmount());

        transactionBloc.saveUserAndSystemTransaction(thirdPartyTransaction, PaymentType.DEPOSIT);

        return new PaymentCreateRes(thirdPartyTransaction.getPaymentId());
    }

    @Transactional
    public void executeDeposit(final PaymentExecuteReq paymentExecuteReq) {
        ThirdPartyTransaction thirdPartyTransaction = paymentService.executePayment(paymentExecuteReq.getPaymentId());

        transactionBloc.saveUserAndSystemTransaction(thirdPartyTransaction, PaymentType.DEPOSIT);

        BigDecimal additionalBalance = thirdPartyTransaction.getAmount().subtract(thirdPartyTransaction.getFee());

        walletService.addBalance(SecurityHelper.getCurrentUserId(), additionalBalance);

    }

    @Transactional
    public void cancelDeposit(final PaymentExecuteReq paymentExecuteReq) {
        ThirdPartyTransaction thirdPartyTransaction = paymentService.cancelPayment(paymentExecuteReq.getPaymentId());

        transactionBloc.saveUserAndSystemTransaction(thirdPartyTransaction, PaymentType.DEPOSIT);
    }
}
