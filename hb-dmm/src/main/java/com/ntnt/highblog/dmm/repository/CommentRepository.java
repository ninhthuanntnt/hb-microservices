package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId, Pageable pageable);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);
}
