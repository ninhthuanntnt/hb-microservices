package com.ntnt.highblog.payment.bloc;

import com.ntnt.highblog.payment.api.client.HbDmmClient;
import com.ntnt.highblog.payment.enums.PaymentMethod;
import com.ntnt.highblog.payment.enums.PaymentType;
import com.ntnt.highblog.payment.enums.SystemTransactionStatus;
import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import com.ntnt.highblog.payment.error.exception.ValidatorException;
import com.ntnt.highblog.payment.helper.CodeHelper;
import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.dto.request.DonationReq;
import com.ntnt.highblog.payment.model.dto.response.UserRes;
import com.ntnt.highblog.payment.model.entity.SystemTransaction;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import com.ntnt.highblog.payment.model.entity.Wallet;
import com.ntnt.highblog.payment.service.SystemTransactionService;
import com.ntnt.highblog.payment.service.UserTransactionService;
import com.ntnt.highblog.payment.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PessimisticLockException;

import java.math.BigDecimal;

@Slf4j
@Component
public class DonationBloc {

    private final UserTransactionService userTransactionService;
    private final SystemTransactionService systemTransactionService;
    private final WalletService walletService;
    private final HbDmmClient hbDmmClient;

    public DonationBloc(final UserTransactionService userTransactionService,
                        final SystemTransactionService systemTransactionService,
                        final WalletService walletService,
                        final HbDmmClient hbDmmClient) {
        this.userTransactionService = userTransactionService;
        this.systemTransactionService = systemTransactionService;
        this.walletService = walletService;
        this.hbDmmClient = hbDmmClient;
    }

    @Transactional
    public void donate(final DonationReq donationReq) {
        log.info("Donate for user with nickName #{} with amount #{}",
                 donationReq.getNickName(),
                 donationReq.getAmount());

        // TODO: Use queue to handle transactions
        // TODO: Handle pessimistic locking exception then create IN_PROGRESS transaction and response immediately
        BigDecimal amount = donationReq.getAmount();

        Long senderId = SecurityHelper.getCurrentUserId();
        ResponseEntity<UserRes> res = hbDmmClient.getUserDetailByNickname(donationReq.getNickName());

        if (res.getStatusCode().isError()) {
            throw new ValidatorException("User not found", "nickname");
        }
        Long receiverId = res.getBody().getId();
        ValidateUserDonation(receiverId);

        boolean locked = true;
        while (locked) {
            try {
                Wallet senderWallet = walletService.getToSaveByUserId(senderId);
                Wallet receiverWallet = walletService.getToSaveByUserId(receiverId);
                locked = false;

                validateSenderWalletAndAmount(senderWallet, amount);

                UserTransaction senderTransaction = buildUserTransaction(senderId,
                                                                         amount,
                                                                         senderWallet.getBalance());

                UserTransaction receiverTransaction = buildUserTransaction(receiverId,
                                                                           amount,
                                                                           receiverWallet.getBalance());

                userTransactionService.saveNew(senderTransaction);
                userTransactionService.saveNew(receiverTransaction);

                SystemTransaction systemTransaction = buildSystemTransaction(senderTransaction.getId(),
                                                                             receiverTransaction.getId(),
                                                                             amount);

                systemTransactionService.saveNew(systemTransaction);

                walletService.subtractBalanceIfSufficient(senderId, amount);
                walletService.addBalance(receiverId, amount);

            } catch (PessimisticLockException ex) {
                log.info("Pessimistic lock when transfer money from userId #{} to #{}", senderId, receiverId);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
                locked = true;
            }
        }
    }

    private void validateSenderWalletAndAmount(final Wallet senderWallet, final BigDecimal amount) {

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new ValidatorException("Insufficient", "wallet");
        }
    }

    private UserTransaction buildUserTransaction(final Long userId,
                                                 final BigDecimal amount,
                                                 final BigDecimal balance) {

        return UserTransaction.builder()
                              .userId(userId)
                              .transactionNo("TR-" + CodeHelper.generateUUID())
                              .amount(amount)
                              .status(UserTransactionStatus.FINISHED)
                              .paymentMethod(PaymentMethod.WALLET)
                              .paymentType(PaymentType.DONATE)
                              .balance(balance)
                              .build();

    }

    private SystemTransaction buildSystemTransaction(final Long senderTransactionId,
                                                     final Long receiverTransactionId,
                                                     final BigDecimal amount) {

        return SystemTransaction.builder()
                                .senderTransactionId(senderTransactionId)
                                .receiverTransactionId(receiverTransactionId)
                                .thirdPartyTransactionId(null)
                                .amount(amount)
                                .status(SystemTransactionStatus.FINISHED)
                                .build();
    }

    private void ValidateUserDonation(final Long userId) {
        Long currentUserId = SecurityHelper.getCurrentUserId();
        if (currentUserId == userId) throw new ValidatorException("wrong receiver nickname", "nickName");
    }

}
