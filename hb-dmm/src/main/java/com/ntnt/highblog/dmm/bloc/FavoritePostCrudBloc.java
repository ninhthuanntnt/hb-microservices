package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.request.FavoritePostCreateReq;
import com.ntnt.highblog.dmm.service.FavoritePostService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.neo4j.PostNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class FavoritePostCrudBloc {
    private final FavoritePostService favoritePostService;
    private final PostStatisticService postStatisticService;
    private final PostNodeService postNodeService;

    public FavoritePostCrudBloc(final FavoritePostService favoritePostService,
                                final PostStatisticService postStatisticService,
                                final PostNodeService postNodeService) {
        this.favoritePostService = favoritePostService;
        this.postStatisticService = postStatisticService;
        this.postNodeService = postNodeService;
    }

    @Transactional
    public void createFavoritePostForCurrentUser(final FavoritePostCreateReq req) {
        log.info("Create favorite post for current user with postId #{}", req.getPostId());
        validateFavoritePostCreateReq(req);
        favoritePostService.saveNew(req.getPostId(), SecurityHelper.getCurrentUserId());
        PostStatistic postStatistic = postStatisticService.increaseNumberOfFavorite(req.getPostId());

        postNodeService.updateNumberOfFavorites(postStatistic.getPostId(),
                                                postStatistic.getNumberOfFavorites());
    }

    @Transactional
    public void deleteFavoritePostForCurrentUser(final Long postId) {
        log.info("Delete favorite post for current user with postId #{}", postId);

        favoritePostService.delete(postId, SecurityHelper.getCurrentUserId());
        PostStatistic postStatistic = postStatisticService.decreaseNumberOfFavorite(postId);

        postNodeService.updateNumberOfFavorites(postStatistic.getPostId(),
                                                postStatistic.getNumberOfFavorites());
    }

    private void validateFavoritePostCreateReq(final FavoritePostCreateReq req) {
        if (favoritePostService.existsByPostIdAndUserId(req.getPostId(), SecurityHelper.getCurrentUserId())){
            throw new ValidatorException("Already added to favorite", "favoritePost");
        }
    }
}
