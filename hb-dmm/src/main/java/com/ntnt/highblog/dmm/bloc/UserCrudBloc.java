package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.UserMapper;
import com.ntnt.highblog.dmm.model.entity.Subscription;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.UserUpdateReq;
import com.ntnt.highblog.dmm.service.FileService;
import com.ntnt.highblog.dmm.service.SubscriptionService;
import com.ntnt.highblog.dmm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Component
public class UserCrudBloc {
    private final UserService userService;
    private final FileService fileService;
    private final SubscriptionService subscriptionService;

    public UserCrudBloc(final UserService userService,
                        final FileService fileService,
                        final SubscriptionService subscriptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.subscriptionService = subscriptionService;
    }

    @Transactional(readOnly = true)
    public User getUserDetail(String nickName) {
        log.info("get user detail by nick name #{}", nickName);

        User user = userService.getByNickName(nickName);
        user.setNumberOfFollowers(subscriptionService.getNumberOfFollowersByUserId(user.getId()));
        includeExtraInfoIfUserLogined(user);

        return user;
    }

    @Transactional
    public void updateUser(final UserUpdateReq userUpdateReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        validateUserUpdateReq(userUpdateReq, userId);

        log.info("Update profile of user detail by id #{}", userId);
        User dbUser = userService.getById(userId);
        User newUser = UserMapper.INSTANCE.toUser(userUpdateReq, dbUser);
        userService.save(newUser);
    }

    private void validateUserUpdateReq(final UserUpdateReq userUpdateReq, Long currentUserId) {
        String newNickname = userUpdateReq.getNickName();
        User currentUser = userService.getById(currentUserId);
        if (newNickname.equals(currentUser.getNickName())) return;
        if (userService.existsByNickName(newNickname))
            throw new ValidatorException("NickName already exists", "nickName");
    }

    @Transactional
    public String uploadAvatar(MultipartFile avatarReq) {
        Long currentUserId = SecurityHelper.getCurrentUserId();
        log.info("Save new avatar for user #{}", currentUserId);

        User currentUser = userService.getById(currentUserId);
        String path = fileService.saveNewImageToStorage(avatarReq);

        try {
            if (currentUser.getImagePath() != null) {
                fileService.deleteImageFromStorageByPath(currentUser.getImagePath());
            }
        } catch (Exception ex) {
            log.info("Can't delete image but still save new avatar");
        } finally {
            userService.saveAvatar(currentUserId, path);
        }

        return path;
    }

    @Transactional
    public String updateBackground(MultipartFile backgroundReq) {
        Long currentUserId = SecurityHelper.getCurrentUserId();
        User currentUser = userService.getById(currentUserId);
        String path = fileService.saveNewImageToStorage(backgroundReq);

        try {
            if (currentUser.getBackgroundPath() != null) {
                fileService.deleteImageFromStorageByPath(currentUser.getBackgroundPath());
            }
        } catch (Exception ex) {
            log.info("Can't delete image but still save new background");
        } finally {
            userService.saveBackground(currentUserId, path);
        }

        return path;
    }

    private void includeExtraInfoIfUserLogined(final User user) {
        try {
            Long currentUserId = SecurityHelper.getCurrentUserId();
            if (currentUserId != user.getId()) {
                Subscription subscription = subscriptionService.findNullableByUserIdAndFollowerId(user.getId(),
                                                                                                  currentUserId);

                if (ObjectUtils.isNotEmpty(subscription)) {
                    user.setFollowed(ObjectUtils.isNotEmpty(subscription));
                    user.setNotified(subscription.isNotified());
                }
            }

        } catch (Exception ex) {
            log.error("Extra info of post detail is not set");
            log.error(ex.getMessage());
        }
    }

}
