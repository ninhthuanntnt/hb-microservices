package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.service.PostService;
import com.ntnt.highblog.dmm.service.PostTagService;
import com.ntnt.highblog.dmm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FavoritePostListBloc {

    private final PostService postService;
    private final UserService userService;
    private final PostTagService postTagService;

    public FavoritePostListBloc(final PostService postService,
                                final UserService userService,
                                final PostTagService postTagService) {
        this.postService = postService;
        this.userService = userService;
        this.postTagService = postTagService;
    }

    @Transactional
    public Page<Post> fetchFavoritePostForCurrentUser(final BasePaginationReq basePaginationReq){
        log.info("Fetch favorite post for current user");

        PageRequest pageRequest = PaginationHelper.generatePageRequest(basePaginationReq);

        Page<Post> posts = postService.fetchFavoritePostByUserIdWithPageRequest(SecurityHelper.getCurrentUserId(),
                                                                                pageRequest);
        includeUserToPosts(posts);
        includePostTagsToPosts(posts);

        return posts;
    }


    private void includeUserToPosts(Page<Post> posts) {
        Map<Long, User> userIdUserMap = userService.fetchByIdIn(posts.map(Post::getUserId).toSet())
                                                   .stream()
                                                   .collect(Collectors.toMap(User::getId, user -> user));

        posts.forEach(post -> post.setUser(userIdUserMap.get(post.getUserId())));
    }

    private void includePostTagsToPosts(Page<Post> posts) {
        Map<Long, List<PostTag>> postIdPostTagsMap =
            postTagService
                .fetchByPostIdIn(posts.map(Post::getId).toSet())
                .stream()
                .collect(Collectors.groupingBy(PostTag::getPostId));

        posts.forEach(post -> post.setPostTags(postIdPostTagsMap.get(post.getId())));
    }
}
