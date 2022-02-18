package com.ntnt.highblog.dmm.service.neo4j;

import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import com.ntnt.highblog.dmm.neo4j.repository.PostNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostNodeService {
    private final PostNodeRepository postNodeRepository;

    public PostNodeService(final PostNodeRepository postNodeRepository) {
        this.postNodeRepository = postNodeRepository;
    }

    public Page<PostNode> fetchRecommendedPostByUserId(Long userId, PageRequest pageRequest) {
        log.info("Fetch recommended posts by userId #{}", userId);
        return new PageImpl<>(postNodeRepository.fetchRecommendPostsByUserId(userId,
                                                                             pageRequest.getOffset(),
                                                                             pageRequest.getPageSize()),
                              pageRequest,
                              postNodeRepository.countRecommendPostsByUserId(userId));
    }

    public Page<PostNode> fetchRecommendedPostByIdAndUserId(Long id, Long userId, PageRequest pageRequest) {
        log.info("Fetch recommended posts by id #{} and userId #{}", id, userId);
        return new PageImpl<>(postNodeRepository.fetchRecommendPostsByIdAndUserId(id,
                                                                                  userId,
                                                                                  pageRequest.getOffset(),
                                                                                  pageRequest.getPageSize()),
                              pageRequest,
                              postNodeRepository.countRecommendPostsByIdAndUserId(id, userId));
    }

    public void saveAll(List<PostNode> postNodes) {
        log.info("Save all postNodes #{}", postNodes);

        postNodeRepository.saveAll(postNodes);
    }
}
