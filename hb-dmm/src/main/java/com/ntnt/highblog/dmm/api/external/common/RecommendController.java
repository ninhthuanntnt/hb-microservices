package com.ntnt.highblog.dmm.api.external.common;

import com.ntnt.highblog.dmm.bloc.PostListBloc;
import com.ntnt.highblog.dmm.bloc.UserListBloc;
import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.mapper.UserMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.model.response.BasePaginationRes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendation")
public class RecommendController {

    private final PostListBloc postListBloc;
    private final UserListBloc userListBloc;

    public RecommendController(final PostListBloc postListBloc, final UserListBloc userListBloc) {
        this.postListBloc = postListBloc;
        this.userListBloc = userListBloc;
    }

    @GetMapping("/posts")
    public ResponseEntity<BasePaginationRes> fetchListRecommendedPosts(BasePaginationReq basePaginationReq) {
        Page<Post> posts = postListBloc.fetchRecommendedPosts(basePaginationReq);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

    @GetMapping("/newsfeed")
    public ResponseEntity<BasePaginationRes> fetchForNewsfeed(BasePaginationReq basePaginationReq) {
        Page<Post> posts = postListBloc.fetchNewsfeed(basePaginationReq);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<BasePaginationRes> getPostDetail(@PathVariable Long id,
                                                           BasePaginationReq basePaginationReq) {
        Page<Post> posts = postListBloc.fetchRecommendedPostsById(id, basePaginationReq);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

    @GetMapping("/users/{nickName}")
    public ResponseEntity<BasePaginationRes> fetchRelatedUsers(@PathVariable String nickName,
                                                               BasePaginationReq basePaginationReq) {
        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            userListBloc.fetchRelatedUsers(nickName, basePaginationReq).map(UserMapper.INSTANCE::toUserRes)
        ));
    }
}
