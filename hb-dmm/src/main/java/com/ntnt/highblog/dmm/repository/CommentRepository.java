package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository
    extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId, Pageable pageable);

    @Query("SELECT new Comment(c.id, c.parentId, c.userId, c.postId, c.content, COUNT(cc.id)) FROM Comment c"
        + " LEFT JOIN Comment cc ON cc.parentId = c.id"
        + " WHERE c.postId = :postId AND ((:parentId IS NULL AND c.parentId IS NULL) "
        + "                                 OR (:parentId IS NOT NULL AND c.parentId = :parentId))"
        + " GROUP BY c.id")
    Page<Comment> fetchByPostIdAndParentId(Long postId, Long parentId, Pageable pageable);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);
}
