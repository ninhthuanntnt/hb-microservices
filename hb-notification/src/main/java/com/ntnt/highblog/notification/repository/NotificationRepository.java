package com.ntnt.highblog.notification.repository;

import com.ntnt.highblog.notification.enums.NotificationType;
import com.ntnt.highblog.notification.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository
    extends JpaRepository<Notification, Long> {
    Optional<Notification> findBySourceId(Long sourceId);

    @Query("SELECT n FROM Notification n"
        + " JOIN UserNotification un ON un.notificationId = n.id"
        + " WHERE n.deleted = false AND un.userId = :receiverId")
    Page<Notification> findByReceiverId(@Param("receiverId") Long receiverId, Pageable pageable);

    @Query("SELECT n FROM Notification n"
        + " JOIN UserNotification un ON un.notificationId = n.id"
        + " WHERE n.deleted = false AND un.userId = :userId AND un.seen = :seen AND un.sent = false")
    List<Notification> findByUserIdAndSeen(@Param("userId") Long userId, @Param("seen") boolean seen);

    Page<Notification> findNotificationsByType(NotificationType type, Pageable pageable);

}
