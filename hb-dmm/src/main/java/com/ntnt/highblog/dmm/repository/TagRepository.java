package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsTagByNameEquals(String tagName);
}
