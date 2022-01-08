package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository
        extends JpaRepository<File, Long> {

    Page<File> findByUserId(Long userId, Pageable pageable);

    Optional<File> findByIdAndUserId(Long id, Long userId);
}
