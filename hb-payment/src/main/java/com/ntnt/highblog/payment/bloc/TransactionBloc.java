package com.ntnt.highblog.payment.bloc;

import com.ntnt.highblog.payment.enums.PaymentMethod;
import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.enums.SystemTransactionStatus;
import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import com.ntnt.highblog.payment.error.exception.ValidatorException;
import com.ntnt.highblog.payment.helper.CodeHelper;
import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.entity.SystemTransaction;
import com.ntnt.highblog.payment.model.entity.ThirdPartyTransaction;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import com.ntnt.highblog.payment.model.entity.Wallet;
import com.ntnt.highblog.payment.service.SystemTransactionService;
import com.ntnt.highblog.payment.service.UserTransactionService;
import com.ntnt.highblog.payment.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionBloc {

    private final UserTransactionService userTransactionService;
    private final SystemTransactionService systemTransactionService;
    private final WalletService walletService;

    public TransactionBloc(final UserTransactionService userTransactionService,
                           final SystemTransactionService systemTransactionService,
                           final WalletService walletService) {
        this.userTransactionService = userTransactionService;
        this.systemTransactionService = systemTransactionService;
        this.walletService = walletService;
    }

    public void saveUserAndSystemTransaction(final ThirdPartyTransaction thirdPartyTransaction,
                                             final PaymentType paymentType) {
        // TODO: Need an converter to convert third party transaction to system and user transaction
        switch (thirdPartyTransaction.getStatus()) {
            case CREATED: {
                UserTransaction userTransaction = buildUserTransaction(thirdPartyTransaction,
                                                                       UserTransactionStatus.CREATED,
                                                                       paymentType);
                userTransactionService.saveNew(userTransaction);
                systemTransactionService.saveNew(buildSystemTransaction(thirdPartyTransaction,
                                                                        userTransaction,
                                                                        SystemTransactionStatus.CREATED));
                break;
            }
            case APPROVED:
                break;
            case COMPLETED:
            case SUCCESS: {
                UserTransaction userTransaction = userTransactionService.getNullableByPaymentId(thirdPartyTransaction
                                                                                                    .getPaymentId());

                if (ObjectUtils.isEmpty(userTransaction)) {
                    userTransaction = buildUserTransaction(thirdPartyTransaction,
                                                           UserTransactionStatus.FINISHED,
                                                           paymentType);
                    userTransactionService.saveNew(userTransaction);
                } else {
                    userTransactionService.saveStatus(userTransaction, UserTransactionStatus.FINISHED);
                }

                systemTransactionService.saveNew(buildSystemTransaction(thirdPartyTransaction,
                                                                        userTransaction,
                                                                        SystemTransactionStatus.FINISHED));
                break;
            }
            case PENDING: {
                UserTransaction userTransaction = userTransactionService.getNullableByPaymentId(thirdPartyTransaction
                                                                                                    .getPaymentId());
                if (ObjectUtils.isEmpty(userTransaction)) {
                    userTransaction = buildUserTransaction(thirdPartyTransaction,
                                                           UserTransactionStatus.IN_PROGRESS,
                                                           paymentType);
                    userTransactionService.saveNew(userTransaction);
                } else {
                    userTransactionService.saveStatus(userTransaction, UserTransactionStatus.IN_PROGRESS);
                }

                systemTransactionService.saveNew(buildSystemTransaction(thirdPartyTransaction,
                                                                        userTransaction,
                                                                        SystemTransactionStatus.IN_PROGRESS));

                break;
            }
            case FAILED:
                break;
            case CANCELED: {
                UserTransaction userTransaction = userTransactionService.getNullableByPaymentId(thirdPartyTransaction
                                                                                                    .getPaymentId());

                userTransactionService.saveStatus(userTransaction, UserTransactionStatus.CANCELED);
                systemTransactionService.saveNew(buildSystemTransaction(thirdPartyTransaction,
                                                                        userTransaction,
                                                                        SystemTransactionStatus.CANCELED));
                break;
            }
            default:
                throw new ValidatorException("Invalid transaction status (SERVER ERROR)", "status");
        }
    }

    private UserTransaction buildUserTransaction(final ThirdPartyTransaction thirdPartyTransaction,
                                                 final UserTransactionStatus userTransactionStatus,
                                                 final PaymentType paymentType) {
        Long userId = SecurityHelper.getCurrentUserId();
        Wallet wallet = walletService.getToSaveByUserId(userId);

        return UserTransaction.builder()
                              .userId(userId)
                              .transactionNo("TR-" + CodeHelper.generateUUID())
                              .amount(thirdPartyTransaction.getAmount())
                              .status(userTransactionStatus)
                              .paymentMethod(PaymentMethod.PAYPAL)
                              .paymentType(paymentType)
                              .balance(wallet.getBalance())
                              .build();

    }

    private SystemTransaction buildSystemTransaction(final ThirdPartyTransaction thirdPartyTransaction,
                                                     final UserTransaction userTransaction,
                                                     final SystemTransactionStatus status) {
        return SystemTransaction.builder()
                                .senderTransactionId(userTransaction.getId())
                                .receiverTransactionId(userTransaction.getId())
                                .thirdPartyTransactionId(thirdPartyTransaction.getId())
                                .amount(userTransaction.getAmount())
                                .status(status)
                                .build();
    }
}
