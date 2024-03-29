package com.ntnt.highblog.dmm.repository;

import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostStatisticRepository extends JpaRepository<PostStatistic, Long> {

    List<PostStatistic> findByPostIdIn(Collection<Long> postIds);
    Optional<PostStatistic> findByPostId(Long postId);


}
