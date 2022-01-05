package com.ntnt.highblog.uaa.repository;

import com.ntnt.highblog.uaa.enums.CodeType;
import com.ntnt.highblog.uaa.model.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository
        extends JpaRepository<ConfirmationCode, Long> {

    Optional<ConfirmationCode> findByIdAndCodeAndCodeType(Long id, String code, CodeType codeType);

    Optional<ConfirmationCode> findByIdAndCodeType(Long id, CodeType codeType);

    List<ConfirmationCode> findByAccountIdAndCodeType(Long accountId, CodeType codeType);
}
