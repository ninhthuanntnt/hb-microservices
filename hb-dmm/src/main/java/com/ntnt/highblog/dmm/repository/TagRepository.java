package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository
    extends JpaRepository<Tag, Long> {

    boolean existsTagByNameEquals(String tagName);

    @Query("SELECT t FROM Tag t"
        + " WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', CONCAT(:tagName, '%')))")
    Page<Tag> fetchByNameLike(String tagName, Pageable pageable);

    @Query("SELECT t FROM Tag t"
        + " WHERE t.name IN :tagNames")
    List<Tag> getTagNameByNameIn(List<String> tagNames);

    @Query("SELECT t FROM Tag t"
        + " JOIN PostTag pt ON pt.tagId = t.id"
        + " WHERE pt.postId = :postId")
    List<Tag> fetchByPostId(Long postId);
}
