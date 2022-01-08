package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.enums.PostType;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.entity.Subscription;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.PostCreateReq;
import com.ntnt.highblog.dmm.model.request.PostUpdateReq;
import com.ntnt.highblog.dmm.service.FavoritePostService;
import com.ntnt.highblog.dmm.service.PostService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.PostTagService;
import com.ntnt.highblog.dmm.service.PostVoteService;
import com.ntnt.highblog.dmm.service.SubscriptionService;
import com.ntnt.highblog.dmm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class PostCrudBloc {

    private final PostService postService;
    private final PostTagService postTagService;
    private final PostStatisticService postStatisticService;
    private final PostVoteService postVoteService;
    private final UserService userService;
    private final FavoritePostService favoritePostService;
    private final SubscriptionService subscriptionService;

//    private final NotificationBloc notificationBloc;

    public PostCrudBloc(final PostService postService,
                        final PostTagService postTagService,
                        final PostStatisticService postStatisticService,
                        final PostVoteService postVoteService,
                        final UserService userService,
                        final FavoritePostService favoritePostService,
                        final SubscriptionService subscriptionService) {
        this.postService = postService;
        this.postTagService = postTagService;
        this.postStatisticService = postStatisticService;
        this.postVoteService = postVoteService;
        this.userService = userService;
        this.favoritePostService = favoritePostService;
        this.subscriptionService = subscriptionService;

//        this.notificationBloc = notificationBloc;
    }

    @Transactional
    public Long createPost(final PostCreateReq postCreateReq) {
        log.info("Create new post with data #{}", postCreateReq);
        Long userId = SecurityHelper.getCurrentUserId();
        Post post = PostMapper.INSTANCE.toPost(postCreateReq);
        post.setUserId(userId);
        postService.saveNew(post);

        List<PostTag> postTags = (List<PostTag>) CollectionUtils.emptyIfNull(post.getPostTags());

        postTags.forEach(postTag -> postTag.setPostId(post.getId()));

        postTagService.saveNew(postTags);

        postStatisticService.saveNew(PostStatistic.builder()
                                                  .postId(post.getId())
                                                  .build());

//        User notificationSender = userService.getById(userId);
//        notificationBloc.pushNotificationToFollowers(userId,
//                                                     Notification.builder()
//                                                                 .senderId(userId)
//                                                                 .content(post.getTitle())
//                                                                 .sourceId(post.getId())
//                                                                 .type(NotificationType.POST)
//                                                                 .sender(notificationSender)
//                                                                 .build());
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

        if(!post.getUserId().equals(SecurityHelper.getNullableCurrentUserId()) && post.getPostType() == PostType.DRAFT) {
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

        List<PostTag> postTags = (List<PostTag>) CollectionUtils.emptyIfNull(newPost.getPostTags());

        postTags.forEach(postTag -> postTag.setPostId(id));

        postTagService.deleteOldAndSaveNew(newPost.getId(), postTags);
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
                Subscription subscription = subscriptionService.findNullableByUserIdAndFollowerId(postOwner.getId(), userId);
                postOwner.setFollowed(ObjectUtils.isNotEmpty(subscription));

                if(ObjectUtils.isNotEmpty(subscription)){
                    postOwner.setNotified(subscription.isNotified());
                }
            }
        } catch (Exception ex) {
            log.error("Extra info of post detail is not set");
            log.error(ex.getMessage());
        }
    }
}
