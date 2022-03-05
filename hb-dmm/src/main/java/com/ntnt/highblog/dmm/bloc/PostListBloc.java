package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.model.request.PostSearchReq;
import com.ntnt.highblog.dmm.service.PostService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.PostTagService;
import com.ntnt.highblog.dmm.service.UserService;
import com.ntnt.highblog.dmm.service.neo4j.PostNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostListBloc {

    private final PostService postService;
    private final PostTagService postTagService;
    private final UserService userService;
    private final PostStatisticService postStatisticService;
    private final PostNodeService postNodeService;

    public PostListBloc(final PostService postService,
                        final PostTagService postTagService,
                        final UserService userService,
                        final PostStatisticService postStatisticService,
                        final PostNodeService postNodeService) {
        this.postService = postService;
        this.postTagService = postTagService;
        this.userService = userService;
        this.postStatisticService = postStatisticService;
        this.postNodeService = postNodeService;
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchPosts(Long categoryId, final BasePaginationReq req) {
        // TODO: add binding data to convert string of default sort to an object
        PageRequest pageRequest = PaginationHelper.generatePageRequestWithoutSort(req);

        Page<Post> posts = postService.fetchTrendingPostsWithPageRequest(categoryId, pageRequest);

        // Should use include to make better performance
        includePostTagsToPosts(posts);
        includeUserToPosts(posts);

        return posts;
    }

    public Page<Post> fetchRecommendedPosts(BasePaginationReq basePaginationReq) {
        Optional<Long> currentUserId = SecurityHelper.getNullableCurrentUserId();
        log.info("Fetch recommended post for userId #{}", currentUserId.orElse(null));

        PageRequest pageRequest = PaginationHelper.generatePageRequest(basePaginationReq);
        if (currentUserId.isPresent()) {
            Page<PostNode> postNodePage = postNodeService.fetchRecommendedPostByUserId(currentUserId.get(),
                                                                                       pageRequest);
            return getPostsFromPostNodes(postNodePage);
        } else {
            Page<Post> posts =
                postService.fetchPosts(PaginationHelper.generatePageRequestWithDefaultSort(basePaginationReq, "-id"));

            includeExtraInfo(posts);
            return posts;
        }
    }

    public Page<Post> fetchNewsfeed(BasePaginationReq basePaginationReq) {
        Optional<Long> currentUserId = SecurityHelper.getNullableCurrentUserId();
        log.info("Fetch newsfeed post for userId #{}", currentUserId.orElse(null));

        PageRequest pageRequest = PaginationHelper.generatePageRequest(basePaginationReq);

        if (currentUserId.isPresent()) {
            Page<PostNode> postNodePage = postNodeService.fetchNewsfeedByUserId(currentUserId.get(), pageRequest);
            return getPostsFromPostNodes(postNodePage);
        } else {
            Page<Post> posts =
                postService.fetchPosts(PaginationHelper.generatePageRequestWithDefaultSort(basePaginationReq, "-id"));

            includeExtraInfo(posts);
            return posts;
        }
    }

    public Page<Post> fetchRecommendedPostsById(Long id, BasePaginationReq basePaginationReq) {
        Optional<Long> currentUserId = SecurityHelper.getNullableCurrentUserId();
        log.info("Fetch recommended posts by id #{} for userId #{}", id, currentUserId.orElse(null));

        PageRequest pageRequest = PaginationHelper.generatePageRequest(basePaginationReq);

        Page<PostNode> postNodePage;
        if (currentUserId.isPresent()) {
            postNodePage = postNodeService.fetchRecommendedPostByIdAndUserId(id,
                                                                             currentUserId.get(),
                                                                             pageRequest);
        } else {
            postNodePage = postNodeService.fetchRecommendedPostById(id,
                                                                    pageRequest);

        }
        return getPostsFromPostNodes(postNodePage);
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchPostsByNickName(final String nickName, Long categoryId, final BasePaginationReq req) {
        log.info("Fetch list posts by nickName #{} with req #{}", nickName, req);
        PageRequest pageRequest = PaginationHelper.generatePageRequestWithDefaultSort(req,
                                                                                      "-ps.id");
        Long currentUserId = SecurityHelper.getCurrentUserId();
        Page<Post> posts = null;
        if (currentUserId == userService.getByNickName(nickName).getId()) {
            posts = postService.fetchPostsOfCurrentUser(nickName, categoryId, pageRequest);
        } else posts = postService.fetchPostsByNickNameWithPageRequest(nickName, categoryId, pageRequest);

        includePostTagsToPosts(posts);
        includeUserToPosts(posts);
        return posts;
    }

    public Page<Post> fetchPostsByTagId(final Long tagId, final Long categoryId, final BasePaginationReq req) {
        log.info("Fetch list posts by tagId #{} with req #{}", tagId, req);
        PageRequest pageRequest = PaginationHelper.generatePageRequestWithDefaultSort(req,
                                                                                      "-ps.numberOfVotes");

        Page<Post> posts = postService.fetchPostsByTagIdWithPageRequest(tagId, categoryId, pageRequest);

        includePostTagsToPosts(posts);
        includeUserToPosts(posts);
        return posts;
    }

    @Transactional(readOnly = true)
    public Page<Post> fetchSubscriptionPostsForCurrentUser(Long categoryId, final BasePaginationReq req) {
        log.info("Fetch list subscription posts for current user");

        PageRequest pageRequest = PaginationHelper.generatePageRequest(req);

        Page<Post> posts = postService
            .fetchPostsByFollowerIdWithPageRequest(SecurityHelper.getCurrentUserId(), categoryId, pageRequest);

        includeExtraInfo(posts);

        return posts;
    }

    @Transactional(readOnly = true)
    public Page<Post> searchPosts(final PostSearchReq req) {
        log.info("Search post with keyword #{}", req);

        // TODO: add binding data to convert string of default sort to an object
        Page<Post> posts;
        if (req.getKeyword().length() <= 2) {
            PageRequest pageRequest = PaginationHelper.generatePageRequestWithDefaultSort(req,
                                                                                          "-ps.numberOfVotes");
            posts = postService.searchPostsByKeywordLikeWithPageRequest(req.getKeyword(), pageRequest);
        } else {
            PageRequest pageRequest = PaginationHelper.generatePageRequestWithoutSort(req);
            String keyword = req.getKeyword().replace("( +)", " ")
                                .trim();
            posts = postService.searchFullTextPostsByKeywordWithPageRequest(keyword, pageRequest);
            if (posts.getContent().size() == 0) {
                keyword = keyword.replace(" ", "|");
                posts = postService.searchPostsByKeywordRegexpWithPageRequest(keyword, pageRequest);
            }
        }

        includeExtraInfo(posts);

        return posts;
    }

    private void includePostTagsToPosts(Page<Post> posts) {
        Map<Long, List<PostTag>> postIdPostTagsMap =
            postTagService
                .fetchByPostIdIn(posts.map(Post::getId).toSet())
                .stream()
                .collect(Collectors.groupingBy(PostTag::getPostId));

        posts.forEach(post -> post.setPostTags(postIdPostTagsMap.get(post.getId())));
    }

    private void includeUserToPosts(Page<Post> posts) {
        Map<Long, User> userIdUserMap = userService.fetchByIdIn(posts.map(Post::getUserId).toSet())
                                                   .stream()
                                                   .collect(Collectors.toMap(User::getId, user -> user));

        posts.forEach(post -> post.setUser(userIdUserMap.get(post.getUserId())));
    }

    private void includePostStatisticToPosts(Page<Post> posts) {
        Map<Long, PostStatistic> postIdPostStatisticMap =
            postStatisticService.fetchByPostIdIn(posts.map(Post::getId).toSet())
                                .stream()
                                .collect(Collectors.toMap(PostStatistic::getPostId,
                                                          postStatistic -> postStatistic));

        posts.forEach(post -> post.setPostStatistic(postIdPostStatisticMap.get(post.getId())));
    }

    private Page<Post> getPostsFromPostNodes(final Page<PostNode> postNodePage) {
        Map<Long, Post> postIdPostMap = postService.getByIdIn(postNodePage.getContent()
                                                                          .stream()
                                                                          .map(PostNode::getId)
                                                                          .collect(Collectors.toList()))
                                                   .stream().collect(Collectors.toMap(Post::getId,
                                                                                      post -> post));

        Page<Post> postPage = postNodePage.map(postNode -> postIdPostMap.get(postNode.getId()));

        includeExtraInfo(postPage);

        return postPage;
    }

    private void includeExtraInfo(final Page<Post> postPage) {
        includePostTagsToPosts(postPage);
        includeUserToPosts(postPage);
        includePostStatisticToPosts(postPage);
    }
}
