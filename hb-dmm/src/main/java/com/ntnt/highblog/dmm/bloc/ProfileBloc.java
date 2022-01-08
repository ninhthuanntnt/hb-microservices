package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.response.UserRes;
import com.ntnt.highblog.dmm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileBloc {

    private final UserService userService;

    public ProfileBloc(final UserService userService) {
        this.userService = userService;
    }

    public UserRes getForCurrentUser() {
        Long userId = SecurityHelper.getCurrentUserId();

        log.info("Get user profile for userId #{}", userId);

        User user = userService.getById(userId);

        return UserRes.builder()
                         .id(user.getId())
                         .nickName(user.getNickName())
                         .firstName(user.getFirstName())
                         .lastName(user.getLastName())
                         .imagePath(user.getImagePath())
                         .build();
    }

}
