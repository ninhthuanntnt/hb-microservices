package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.bloc.PostListBloc;
import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.model.response.BasePaginationRes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userPostListController")
@RequestMapping("/api/v1/user/posts")
public class PostListController {

    private final PostListBloc postListBloc;

    public PostListController(final PostListBloc postListBloc) {
        this.postListBloc = postListBloc;
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<BasePaginationRes> fetchListSubscriptionPosts(final Long categoryId,
                                                                        final BasePaginationReq basePaginationReq) {
        Page<Post> posts = postListBloc.fetchSubscriptionPostsForCurrentUser(categoryId, basePaginationReq);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(posts.map(PostMapper.INSTANCE::toPostRes)));
    }
}
