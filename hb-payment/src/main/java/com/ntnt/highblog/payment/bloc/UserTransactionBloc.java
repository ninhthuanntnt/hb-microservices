package com.ntnt.highblog.payment.bloc;


import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.enums.ThirdPartyTransactionStatus;
import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import com.ntnt.highblog.payment.helper.PaginationHelper;
import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.dto.request.BasePaginationReq;
import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import com.ntnt.highblog.payment.service.SystemTransactionService;
import com.ntnt.highblog.payment.service.ThirdPartyTransactionService;
import com.ntnt.highblog.payment.service.UserTransactionService;
import com.ntnt.highblog.payment.service.payment.PaypalPaymentService;
import com.paypal.payouts.PayoutBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class UserTransactionBloc {
    private final UserTransactionService userTransactionService;
    private final SystemTransactionService systemTransactionService;
    private final ThirdPartyTransactionService thirdPartyTransactionService;
    private final PaypalPaymentService paypalPaymentService;
    private final TransactionBloc transactionBloc;

    public UserTransactionBloc(final UserTransactionService userTransactionService,
                               final SystemTransactionService systemTransactionService,
                               final ThirdPartyTransactionService thirdPartyTransactionService,
                               final PaypalPaymentService paypalPaymentService,
                               final TransactionBloc transactionBloc) {
        this.userTransactionService = userTransactionService;
        this.systemTransactionService = systemTransactionService;
        this.thirdPartyTransactionService = thirdPartyTransactionService;
        this.paypalPaymentService = paypalPaymentService;
        this.transactionBloc = transactionBloc;
    }

    @Transactional(readOnly = true)
    public Page<UserTransaction> fetchUserTransactions(BasePaginationReq req) {
        PageRequest pageRequest = PaginationHelper.generatePageRequest(req);
        Long currentUserId = SecurityHelper.getCurrentUserId();

        log.info("Fetch transactions of user userId#{}", currentUserId);
        Page<UserTransaction> page = userTransactionService.fetchAllByUserId(currentUserId, pageRequest);
        page.forEach(userTransaction -> userTransaction.setReceiver(
            userTransaction.getPaymentType() != PaymentType.WITHDRAW
                && systemTransactionService.existByReceiverId(userTransaction.getId())
        ));
        return page;
    }

    @Transactional
    public void updateUserTransactionStatusFromJob() {
        log.info("Update userTransaction status from job");

        List<UserTransaction> userTransactions =
            userTransactionService.fetchByStatusIn(Arrays.asList(UserTransactionStatus.CREATED,
                                                                 UserTransactionStatus.IN_PROGRESS));

        userTransactions.forEach(userTransaction -> {
            if (userTransaction.getPaymentType() == PaymentType.WITHDRAW) {
                PayoutBatch payoutBatch = paypalPaymentService.getPayoutResponse(userTransaction.getPaymentId());
                ThirdPartyTransactionStatus status =
                    ThirdPartyTransactionStatus.valueOf(payoutBatch.items()
                                                                   .get(0)
                                                                   .transactionStatus());
                if (status == ThirdPartyTransactionStatus.SUCCESS
                    || status == ThirdPartyTransactionStatus.FAILED
                    || status == ThirdPartyTransactionStatus.CANCELED) {
                    HashMap<String, Object> additionalParams = new HashMap<String, Object>() {{
                        put("senderBatchId", payoutBatch.batchHeader().senderBatchHeader().senderBatchId());
                        put("senderItemId", payoutBatch.items().get(0).payoutItem().senderItemId());
                    }};
                    ThirdPartyTransaction thirdPartyTransaction =
                        ThirdPartyTransaction.builder()
                                             .paymentId(payoutBatch.batchHeader().payoutBatchId())
                                             .amount(userTransaction.getAmount())
                                             .status(ThirdPartyTransactionStatus.valueOf(payoutBatch.items()
                                                                                                    .get(0)
                                                                                                    .transactionStatus()))
                                             .fee(new BigDecimal(payoutBatch.batchHeader()
                                                                            .fees()
                                                                            .value()))
                                             .additionalParams(additionalParams)
                                             .build();

                    thirdPartyTransactionService.saveNew(thirdPartyTransaction);

                    transactionBloc.saveUserAndSystemTransaction(thirdPartyTransaction,
                                                                 userTransaction.getPaymentType());
                }
            }
        });
    }
}
