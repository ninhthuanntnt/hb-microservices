package com.ntnt.highblog.payment.bloc;

import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.dto.request.PaypalWithdrawReq;
import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import com.ntnt.highblog.payment.service.WalletService;
import com.ntnt.highblog.payment.service.payment.PaymentService;
import com.ntnt.highblog.payment.service.payment.PaypalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PaypalWithdrawalBloc {

    private final PaymentService paymentService;
    private final WalletService walletService;
    private final TransactionBloc transactionBloc;

    public PaypalWithdrawalBloc(final PaypalPaymentService paymentService,
                                final WalletService walletService,
                                final TransactionBloc transactionBloc) {
        this.paymentService = paymentService;
        this.walletService = walletService;
        this.transactionBloc = transactionBloc;
    }

    @Transactional
    public void withdraw(final PaypalWithdrawReq paypalWithdrawReq) {

        walletService.subtractBalanceIfSufficient(SecurityHelper.getCurrentUserId(),
                                                  paypalWithdrawReq.getAmount());

        ThirdPartyTransaction thirdPartyTransaction = paymentService.withdraw(paypalWithdrawReq.getEmail(),
                                                                              paypalWithdrawReq.getAmount());

        transactionBloc.saveUserAndSystemTransaction(thirdPartyTransaction, PaymentType.WITHDRAW);
    }
}
