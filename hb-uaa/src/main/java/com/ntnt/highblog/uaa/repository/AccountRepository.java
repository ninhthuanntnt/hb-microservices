package com.ntnt.highblog.uaa.repository;

import com.ntnt.highblog.uaa.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository
        extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    List<Account> findByUserIdIn(List<Long> userIds);

    Account findByUserId(Long id);
}
