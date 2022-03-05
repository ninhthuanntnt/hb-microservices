package com.ntnt.highblog.dmm.service.neo4j;

import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import com.ntnt.highblog.dmm.neo4j.repository.PostNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PostNodeService {
    private final PostNodeRepository postNodeRepository;

    public PostNodeService(final PostNodeRepository postNodeRepository) {
        this.postNodeRepository = postNodeRepository;
    }

    public Page<PostNode> fetchNewsfeedByUserId(Long userId, PageRequest pageRequest) {
        log.info("Fetch recommended posts by userId #{}", userId);
        return new PageImpl<>(postNodeRepository.fetchForNewsfeed(userId,
                                                                  pageRequest.getOffset(),
                                                                  pageRequest.getPageSize()),
                              pageRequest,
                              postNodeRepository.countForNewsfeed(userId));
    }

    public Page<PostNode> fetchRecommendedPostByUserId(Long userId, PageRequest pageRequest) {
        log.info("Fetch recommended posts by userId #{}", userId);
        return new PageImpl<>(postNodeRepository.fetchRecommendPostsByUserId(userId,
                                                                             pageRequest.getOffset(),
                                                                             pageRequest.getPageSize()),
                              pageRequest,
                              postNodeRepository.countRecommendPostsByUserId(userId));
    }

    public Page<PostNode> fetchRecommendedPostById(Long id, PageRequest pageRequest) {
        log.info("Fetch recommended posts by postId #{}", id);
        return new PageImpl<>(postNodeRepository.fetchRecommendPostsById(id,
                                                                         pageRequest.getOffset(),
                                                                         pageRequest.getPageSize()),
                              pageRequest,
                              postNodeRepository.countRecommendPostsById(id));
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

    @Async
    public void updateNumberOfComments(Long id, Long numberOfComment) {
        log.info("Update number of comments #{} by postId #{}", numberOfComment, id);

        postNodeRepository.findById(id)
                          .ifPresent(postNode -> {
                              postNode.setNumberOfComments(numberOfComment);
                              postNodeRepository.save(postNode);
                          });
    }

    @Async
    public void updateNumberOfFavorites(Long id, Long numberOfFavorites) {
        log.info("Update number of comments #{} by postId #{}", numberOfFavorites, id);

        postNodeRepository.findById(id)
                          .ifPresent(postNode -> {
                              postNode.setNumberOfFavorites(numberOfFavorites);
                              postNodeRepository.save(postNode);
                          });
    }

    @Async
    public void updateNumberOfVotes(Long id, Long numberOfVotes) {
        log.info("Update number of comments #{} by postId #{}", numberOfVotes, id);

        postNodeRepository.findById(id)
                          .ifPresent(postNode -> {
                              postNode.setNumberOfVotes(numberOfVotes);
                              postNodeRepository.save(postNode);
                          });
    }

    @Transactional("neo4jTransactionManager")
    public void saveAll(List<PostNode> postNodes) {
        log.info("Save all postNodes #{}", postNodes);

        postNodeRepository.saveAll(postNodes);
    }
}
