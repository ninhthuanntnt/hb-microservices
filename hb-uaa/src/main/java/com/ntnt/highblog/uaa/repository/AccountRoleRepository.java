package com.ntnt.highblog.uaa.repository;

import com.ntnt.highblog.uaa.model.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {

    List<AccountRole> findByAccountId(Long accountId);
}
