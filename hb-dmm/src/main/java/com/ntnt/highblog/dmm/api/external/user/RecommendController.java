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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/recommendation")
public class RecommendController {

    private final PostListBloc postListBloc;

    public RecommendController(final PostListBloc postListBloc) {
        this.postListBloc = postListBloc;
    }

    @GetMapping("/posts")
    public ResponseEntity<BasePaginationRes> fetchListRecommendedPosts(BasePaginationReq basePaginationReq) {
        Page<Post> posts = postListBloc.fetchRecommendedPosts(basePaginationReq);

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
}
