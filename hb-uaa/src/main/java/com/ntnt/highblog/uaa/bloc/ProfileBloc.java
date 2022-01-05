package com.ntnt.highblog.uaa.bloc;

import com.ntnt.highblog.uaa.api.client.HbDmmClient;
import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.helper.SecurityHelper;
import com.ntnt.highblog.uaa.model.dto.response.ProfileRes;
import com.ntnt.highblog.uaa.model.dto.response.UserRes;
import com.ntnt.highblog.uaa.model.entity.Role;
import com.ntnt.highblog.uaa.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProfileBloc {

    private final HbDmmClient hbDmmClient;
    private final RoleService roleService;

    public ProfileBloc(final HbDmmClient hbDmmClient,
                       final RoleService roleService) {
        this.hbDmmClient = hbDmmClient;
        this.roleService = roleService;
    }

    @Transactional
    public ProfileRes getProfileResForCurrentUser() {
        Long userId = SecurityHelper.getCurrentUserId();

        log.info("Get user profile for userId #{}", userId);

        Long accountId = SecurityHelper.getCurrentAccountId();

        ResponseEntity<UserRes> userResResponseEntity = hbDmmClient.getProfileInfoForCurrentUser();

        if(HttpStatus.OK == userResResponseEntity.getStatusCode()) {
            UserRes userRes = userResResponseEntity.getBody();
            List<Role> roles = roleService.fetchByAccountId(accountId);

            return ProfileRes.builder()
                             .id(userRes.getId())
                             .nickName(userRes.getNickName())
                             .firstName(userRes.getFirstName())
                             .lastName(userRes.getLastName())
                             .imagePath(userRes.getImagePath())
                             .roleTypes(roles.stream().map(Role::getRoleType).collect(Collectors.toList()))
                             .build();
        } else {
            throw new ObjectNotFoundException("userRes");
        }
    }

}
