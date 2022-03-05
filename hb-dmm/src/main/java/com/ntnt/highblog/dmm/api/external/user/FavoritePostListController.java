package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.bloc.FavoritePostListBloc;
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

@RestController
@RequestMapping("/api/v1/user/favorite-posts")
public class FavoritePostListController {

    private final FavoritePostListBloc favoritePostListBloc;

    public FavoritePostListController(final FavoritePostListBloc favoritePostListBloc) {
        this.favoritePostListBloc = favoritePostListBloc;
    }

    @GetMapping
    public ResponseEntity<BasePaginationRes> fetchListFavoritePost(final BasePaginationReq basePaginationReq) {

        Page<Post> posts = favoritePostListBloc.fetchFavoritePostForCurrentUser(basePaginationReq);

        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(posts.map(PostMapper.INSTANCE::toPostRes)));
    }
}
