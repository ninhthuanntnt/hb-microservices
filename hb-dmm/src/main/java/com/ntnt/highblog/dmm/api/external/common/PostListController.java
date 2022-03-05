package com.ntnt.highblog.dmm.api.external.common;

import com.ntnt.highblog.dmm.bloc.PostListBloc;
import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.model.request.PostSearchReq;
import com.ntnt.highblog.dmm.model.response.BasePaginationRes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostListController {
    private final PostListBloc postListBloc;

    public PostListController(final PostListBloc postListBloc) {
        this.postListBloc = postListBloc;
    }

    // TODO: bind array of String to array of Objects fot sorts field
    @GetMapping
    public ResponseEntity<BasePaginationRes> fetchListPost(final Long categoryId, BasePaginationReq req) {
        Page<Post> posts = postListBloc.fetchPosts(categoryId, req);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

    @GetMapping(params = "nickName")
    public ResponseEntity<?> fetchListPost(@RequestParam final String nickName, final Long categoryId,
                                           final BasePaginationReq req) {

        Page<Post> posts = postListBloc.fetchPostsByNickName(nickName, categoryId, req);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

    @GetMapping(params = "tagId")
    public ResponseEntity<?> fetchListPostsByTagId(@RequestParam final Long tagId,
                                                   final long categoryId,
                                                   final BasePaginationReq req) {
        Page<Post> posts = postListBloc.fetchPostsByTagId(tagId, categoryId, req);
        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPosts(final PostSearchReq req) {
        Page<Post> posts = postListBloc.searchPosts(req);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(
            posts.map(PostMapper.INSTANCE::toPostRes)
        ));
    }

}
