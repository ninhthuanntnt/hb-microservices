package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.response.admin.CountPostsRes;
import com.ntnt.highblog.dmm.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PostService {
    private final PostRepository repository;

    public PostService(final PostRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveNew(final Post post) {
        log.info("Save new post with data #{}", post);
        validatePostBeforeSaveNew(post);

        repository.save(post);
    }

    @Transactional
    public void save(final Post post) {
        log.info("Save post with id #{}", post.getId());
        validatePostBeforeSave(post);

        repository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getByIdAndUserId(final Long id, final Long userId) {
        log.info("Get post by id #{} and userId #{}", id, userId);

        return repository.findByIdAndUserId(id, userId)
                         .orElseThrow(() -> new ObjectNotFoundException("post"));
    }

    @Transactional(readOnly = true)
    public Post getById(final Long id) {
        log.info("Get post by id #{}", id);

        return repository.findById(id)
                         .orElseThrow(() -> new ObjectNotFoundException("post"));
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchTrendingPostsWithPageRequest(Long categoryId, final PageRequest pageRequest) {
        log.info("Fetch posts with pageRequest");

        return repository.fetchTrendingByCategoryId(categoryId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchPostsByNickNameWithPageRequest(final String nickName, Long categoryId, final PageRequest pageRequest) {
        log.info("Fetch posts by nickName #{} with pageRequest #{}", nickName, pageRequest);

        return repository.fetchListPostsByNickName(nickName, categoryId, pageRequest);
    }
    @Transactional(readOnly = true)
    public Page<Post> fetchPostsOfCurrentUser(final String nickName, Long categoryId, final PageRequest pageRequest) {
        log.info("Fetch posts by nickName #{} with pageRequest #{}", nickName, pageRequest);

        return repository.fetchPostsOfCurrentUser(nickName, categoryId, pageRequest);
    }
    @Transactional(readOnly = true)
    public Page<Post> fetchPostsByFollowerIdWithPageRequest(final Long followerId, Long categoryId, final PageRequest pageRequest) {
        log.info("Fetch posts by followerId #{} with pageRequest #{}", followerId, pageRequest);

        return repository.fetchListPostsByFollowerId(followerId, categoryId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> searchFullTextPostsByKeywordWithPageRequest(final String keyword,
                                                                  final PageRequest pageRequest) {
        log.info("Search full text post with keyword #{}", keyword);

        return repository.searchFullTextPosts(keyword, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> searchPostsByKeywordLikeWithPageRequest(final String keyword,
                                                              final PageRequest pageRequest) {
        log.info("Search post with keyword #{}", keyword);

        return repository.searchPosts(keyword, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> searchPostsByKeywordRegexpWithPageRequest(final String keyword,
                                                                final PageRequest pageRequest) {
        log.info("Search post with keyword #{}", keyword);

        return repository.searchPostsRegexp(keyword, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchFavoritePostByUserIdWithPageRequest(final Long userId, final PageRequest pageRequests) {
        log.info("Fetch favorite posts with page request #{}", pageRequests);

        return repository.fetchListFavoritePostsByUserId(userId, pageRequests);
    }

    @Transactional
    public void softDelete(final Long id) {
        log.info("soft delete post by id #{}", id);

        Post post = repository.findById(id)
                              .orElseThrow(() -> new ObjectNotFoundException("post"));

        post.setDeleted(true);

    }

    @Transactional
    public void delete(final Long id) {
        log.info("Delete post by id #{}", id);

        Post post = repository.findById(id)
                              .orElseThrow(() -> new ObjectNotFoundException("post"));

        repository.delete(post);
    }

    private void validatePostBeforeSaveNew(final Post post) {
        if (ObjectUtils.isNotEmpty(post.getId()))
            throw new ValidatorException("Invalid post id", "id");
        if (!post.getUserId().equals(SecurityHelper.getCurrentUserId()))
            throw new ValidatorException("Invalid user id", "userId");
    }

    private void validatePostBeforeSave(final Post post) {
        if (ObjectUtils.isEmpty(post.getId()))
            throw new ValidatorException("Invalid post id", "id");
        if (!post.getUserId().equals(SecurityHelper.getCurrentUserId()))
            throw new ValidatorException("Invalid user id", "userId");
    }

    public Page<Post> fetchPostsByTagIdWithPageRequest(final Long tagId, final Long categoryId, PageRequest pageRequest) {
        log.info("Fetch posts by tagId with page request #{}", pageRequest);

        return repository.fetchByTagIdAndCategoryId(tagId, categoryId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchPosts(final PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> searchDynamicPosts(Long categoryId, Long userId, List<Long> tagIds, PageRequest pageRequest, String keyword) {
        log.info("Search dynamic posts ");
        return repository.searchDynamicPosts(categoryId, userId, tagIds, keyword, pageRequest);
    }

    @Transactional(readOnly = true)
    public List<Post> getByIdIn(List<Long> ids) {
        log.info("Get by id in #{}", ids);

        return repository.findByIdIn(ids);
    }

    @Transactional
    public void restorePost(final Long id) {
        log.info("restore post by id #{}", id);

        Post post = repository.findById(id)
                              .orElseThrow(() -> new ObjectNotFoundException("post"));

        post.setDeleted(false);
    }

    @Transactional(readOnly = true)
    public CountPostsRes countPosts() {
        CountPostsRes posts = new CountPostsRes();
        posts.setTotal(repository.count());
        posts.setPosts(repository.countPostsByCategoryId(1L));
        posts.setQuestions(repository.countPostsByCategoryId(2L));
        return posts;
    }
}
