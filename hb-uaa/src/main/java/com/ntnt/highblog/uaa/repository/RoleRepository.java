package com.ntnt.highblog.uaa.repository;

import com.ntnt.highblog.uaa.enums.RoleType;
import com.ntnt.highblog.uaa.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r"
         + " JOIN AccountRole ar ON ar.role_id = r.id"
         + " WHERE ar.accountId = :accountId")
    List<Role> fetchByAccountId(@Param("accountId") Long accountId);

    Optional<Role> findByRoleType(RoleType roleType);
}
