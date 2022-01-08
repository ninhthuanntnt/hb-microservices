package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.request.FavoritePostCreateReq;
import com.ntnt.highblog.dmm.service.FavoritePostService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class FavoritePostCrudBloc {
    private final FavoritePostService favoritePostService;
    private final PostStatisticService postStatisticService;

    public FavoritePostCrudBloc(final FavoritePostService favoritePostService,
                                final PostStatisticService postStatisticService) {
        this.favoritePostService = favoritePostService;
        this.postStatisticService = postStatisticService;
    }

    @Transactional
    public void createFavoritePostForCurrentUser(final FavoritePostCreateReq req) {
        log.info("Create favorite post for current user with postId #{}", req.getPostId());
        validateFavoritePostCreateReq(req);
        favoritePostService.saveNew(req.getPostId(), SecurityHelper.getCurrentUserId());
        postStatisticService.increaseNumberOfFavorite(req.getPostId());
    }

    @Transactional
    public void deleteFavoritePostForCurrentUser(final Long postId) {
        log.info("Delete favorite post for current user with postId #{}", postId);

        favoritePostService.delete(postId, SecurityHelper.getCurrentUserId());
        postStatisticService.decreaseNumberOfFavorite(postId);
    }

    private void validateFavoritePostCreateReq(final FavoritePostCreateReq req) {
        if (favoritePostService.existsByPostIdAndUserId(req.getPostId(), SecurityHelper.getCurrentUserId())){
            throw new ValidatorException("Already added to favorite", "favoritePost");
        }
    }
}
