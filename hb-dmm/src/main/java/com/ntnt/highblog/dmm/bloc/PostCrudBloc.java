package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.api.client.HbNotificationClient;
import com.ntnt.highblog.dmm.enums.NotificationType;
import com.ntnt.highblog.dmm.enums.PostType;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.entity.Subscription;
import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import com.ntnt.highblog.dmm.model.entity.neo4j.TagNode;
import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import com.ntnt.highblog.dmm.model.request.NotificationCreateReq;
import com.ntnt.highblog.dmm.model.request.NotificationSenderReq;
import com.ntnt.highblog.dmm.model.request.PostCreateReq;
import com.ntnt.highblog.dmm.model.request.PostUpdateReq;
import com.ntnt.highblog.dmm.model.request.TagCreateReq;
import com.ntnt.highblog.dmm.service.FavoritePostService;
import com.ntnt.highblog.dmm.service.PostService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.PostTagService;
import com.ntnt.highblog.dmm.service.PostVoteService;
import com.ntnt.highblog.dmm.service.SubscriptionService;
import com.ntnt.highblog.dmm.service.TagService;
import com.ntnt.highblog.dmm.service.UserService;
import com.ntnt.highblog.dmm.service.neo4j.UserNodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostCrudBloc {

    private final PostService postService;
    private final PostTagService postTagService;
    private final TagService tagService;
    private final PostStatisticService postStatisticService;
    private final PostVoteService postVoteService;
    private final UserService userService;
    private final FavoritePostService favoritePostService;
    private final SubscriptionService subscriptionService;

    private final UserNodeService userNodeService;

    private final HbNotificationClient hbNotificationClient;

//    private final NotificationBloc notificationBloc;

    public PostCrudBloc(final PostService postService,
                        final PostTagService postTagService,
                        final TagService tagService,
                        final PostStatisticService postStatisticService,
                        final PostVoteService postVoteService,
                        final UserService userService,
                        final FavoritePostService favoritePostService,
                        final SubscriptionService subscriptionService,
                        final UserNodeService userNodeService,
                        final HbNotificationClient hbNotificationClient) {
        this.postService = postService;
        this.postTagService = postTagService;
        this.tagService = tagService;
        this.postStatisticService = postStatisticService;
        this.postVoteService = postVoteService;
        this.userService = userService;
        this.favoritePostService = favoritePostService;
        this.subscriptionService = subscriptionService;

//        this.notificationBloc = notificationBloc;
        this.userNodeService = userNodeService;
        this.hbNotificationClient = hbNotificationClient;
    }

    @Transactional
    public Long createPost(final PostCreateReq postCreateReq) {
        log.info("Create new post with data #{}", postCreateReq);
        Long userId = SecurityHelper.getCurrentUserId();
        Post post = PostMapper.INSTANCE.toPost(postCreateReq);
        post.setUserId(userId);
        postService.saveNew(post);

        List<Tag> tags = tagService.fetchExistAndNotByListTagNames(postCreateReq.getTagCreateReqs()
                                                                                .stream()
                                                                                .map(TagCreateReq::getName)
                                                                                .collect(Collectors.toList()));

        tagService.saveAll(tags);

        List<PostTag> postTags = tags.stream()
                                     .map(tag -> PostTag.builder()
                                                        .postId(post.getId())
                                                        .tagId(tag.getId())
                                                        .build())
                                     .collect(Collectors.toList());

        postTagService.saveNew(postTags);

        postStatisticService.saveNew(PostStatistic.builder()
                                                  .postId(post.getId())
                                                  .build());

        User currentUser = userService.getById(userId);
        pushNewPostNotification(currentUser, post);

        syncDataToRecommendSystem(currentUser, post, tags);

        return post.getId();
    }

    @Transactional(readOnly = true)
    public Post getPostDetailForCurrentUser(final Long id) {
        log.info("Get post detail for user by id #{}", id);

        Post post = postService.getByIdAndUserId(id, SecurityHelper.getCurrentUserId());

        List<PostTag> postTags = postTagService.fetchByPostId(post.getId());

        post.setPostTags(postTags);

        return post;
    }

    @Transactional(readOnly = true)
    public Post getPostDetail(final Long id) {
        log.info("Get post detail by id #{}", id);

        Post post = postService.getById(id);

        if (!post.getUserId().equals(SecurityHelper.getNullableCurrentUserId()) && post
            .getPostType() == PostType.DRAFT) {
            throw new ValidatorException("Invalid", "post");
        }

        post.setPostTags(postTagService.fetchByPostId(post.getId()));
        post.setUser(userService.getById(post.getUserId()));
        post.setPostStatistic(postStatisticService.getByPostId(post.getId()));

        includeExtraInfoForPostDetailIfUserLogined(post);

        return post;
    }

    @Transactional
    public void updatePostForCurrentUser(final Long id, final PostUpdateReq postUpdateReq) {

        Post dbPost = postService.getByIdAndUserId(id, SecurityHelper.getCurrentUserId());
        Post newPost = PostMapper.INSTANCE.toPost(postUpdateReq, dbPost);
        postService.save(newPost);

        List<Tag> tags = tagService.fetchExistAndNotByListTagNames(postUpdateReq.getTagCreateReqs()
                                                                                .stream()
                                                                                .map(TagCreateReq::getName)
                                                                                .collect(Collectors.toList()));

        tagService.saveAll(tags);

        List<PostTag> postTags = tags.stream()
                                     .map(tag -> PostTag.builder()
                                                        .postId(id)
                                                        .tagId(tag.getId())
                                                        .build())
                                     .collect(Collectors.toList());

        postTagService.deleteOldAndSaveNew(newPost.getId(), postTags);
        syncDataToRecommendSystem(userService.getById(SecurityHelper.getCurrentUserId()), newPost, tags);

        postTags.forEach(postTag -> postTag.setPostId(id));
    }

    @Transactional
    public void deletePostForCurrentUser(final Long id) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Delete post by id #{}", id);

        postService.softDelete(id);

//        notificationBloc.deleteNotificationToFollowers(id);
    }

    private void includeExtraInfoForPostDetailIfUserLogined(final Post post) {
        try {
            Long userId = SecurityHelper.getCurrentUserId();
            if (ObjectUtils.isNotEmpty(userId)) {
                post.setPostVote(postVoteService.getNullableByPostIdAndUserId(post.getId(), userId));

                post.setAddedToFavorite(favoritePostService.existsByPostIdAndUserId(post.getId(), userId));

                User postOwner = post.getUser();
                Subscription subscription = subscriptionService
                    .findNullableByUserIdAndFollowerId(postOwner.getId(), userId);
                postOwner.setFollowed(ObjectUtils.isNotEmpty(subscription));

                if (ObjectUtils.isNotEmpty(subscription)) {
                    postOwner.setNotified(subscription.isNotified());
                }
            }
        } catch (Exception ex) {
            log.error("Extra info of post detail is not set");
            log.error(ex.getMessage());
        }
    }

    private void syncDataToRecommendSystem(User user, Post post, List<Tag> tags) {
        List<TagNode> tagNodes = tags.stream()
                                     .map(tag -> new TagNode(tag.getId(), tag.getName()))
                                     .collect(Collectors.toList());
        PostNode postNode = new PostNode(post.getId(),
                                         user.getId(),
                                         post.getTitle(),
                                         0L,
                                         0L,
                                         0L,
                                         tagNodes);

        UserNode userNode = new UserNode(user.getId(),
                                         user.getNickName(),
                                         Collections.singletonList(postNode),
                                         Collections.emptyList(),
                                         Collections.emptyList());

        userNodeService.saveAll(Collections.singletonList(userNode));
    }

    private void pushNewPostNotification(User notificationSender, Post post) {
        List<Long> receiverIds = subscriptionService.fetchFollowerIdsByUserId(notificationSender.getId())
                                                    .stream()
                                                    .filter(Subscription::isNotified)
                                                    .map(Subscription::getFollowerId)
                                                    .collect(Collectors.toList());
        try {
            hbNotificationClient.createNotification(
                NotificationCreateReq.builder()
                                     .content(post.getTitle())
                                     .sourceId(post.getId())
                                     .notificationType(NotificationType.POST)
                                     .receiverIds(receiverIds)
                                     .notificationSenderReq(NotificationSenderReq
                                                                .builder()
                                                                .nickName(notificationSender.getNickName())
                                                                .imagePath(notificationSender.getImagePath())
                                                                .build())
                                     .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
