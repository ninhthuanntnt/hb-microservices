package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostTagRepository
        extends JpaRepository<PostTag, Long> {

    @Query("SELECT new PostTag(pt.tagId, t.name) FROM PostTag pt"
            + " JOIN Tag t ON t.id = pt.tagId"
            + " WHERE pt.postId = :postId")
    List<PostTag> fetchByPostId(@Param("postId") Long postId);

    List<PostTag> findByPostId(Long postId);

    @Query("SELECT new PostTag(pt.tagId, pt.postId, t.name) FROM PostTag pt"
            + " JOIN Tag t ON t.id = pt.tagId"
            + " WHERE pt.postId IN (:postId)")
    List<PostTag> fetchByPostIdIn(Collection<Long> postId);

    void deleteAllByTagId(Long tagId);
}
