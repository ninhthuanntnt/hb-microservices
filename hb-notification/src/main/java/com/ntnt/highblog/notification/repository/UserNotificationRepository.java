package com.ntnt.highblog.notification.repository;

import com.ntnt.highblog.notification.model.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserNotificationRepository
    extends JpaRepository<UserNotification, Long> {
    Optional<UserNotification> findByNotificationIdAndUserId(Long id, Long userId);
}
