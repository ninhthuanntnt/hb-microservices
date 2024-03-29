package com.ntnt.highblog.payment.repository;

import com.ntnt.highblog.payment.enums.UserTransactionStatus;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserTransactionRepository
    extends JpaRepository<UserTransaction, Long> {

    @Query("SELECT DISTINCT ut FROM UserTransaction ut"
        + " JOIN SystemTransaction st ON st.senderTransactionId = ut.id"
        + " JOIN ThirdPartyTransaction  tpp ON tpp.id = st.thirdPartyTransactionId"
        + " WHERE tpp.paymentId = :paymentId")
    Optional<UserTransaction> findByPaymentId(String paymentId);

    Page<UserTransaction> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT ut FROM UserTransaction ut"
        + " WHERE (:userId IS NULL OR ut.userId = :userId) "
        + " AND (:transactionNo IS NULL OR ut.transactionNo = :transactionNo )"
        + " AND (:startDate IS NULL AND :endDate IS NULL OR ut.createdDate BETWEEN  :startDate AND :endDate)")
    Page<UserTransaction> searchDynamicTransactions(Long userId,
                                                    String transactionNo,
                                                    Instant startDate,
                                                    Instant endDate,
                                                    Pageable pageable);

    @Query("SELECT new UserTransaction(ut.id, ut.userId, ut.transactionNo, ut.amount, ut.status, ut.paymentMethod,"
        + "                            ut.paymentType, ut.balance, tpp.paymentId)"
        + " FROM UserTransaction ut"
        + " JOIN SystemTransaction st ON st.senderTransactionId = ut.id"
        + " JOIN ThirdPartyTransaction tpp ON tpp.id = st.thirdPartyTransactionId"
        + " WHERE ut.status IN :userTransactionStatuses")
    List<UserTransaction> fetchListUserTransactionByStatusIn(List<UserTransactionStatus> userTransactionStatuses);
}
