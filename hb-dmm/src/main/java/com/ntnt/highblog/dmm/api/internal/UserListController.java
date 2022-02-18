package com.ntnt.highblog.dmm.api.internal;

import com.ntnt.highblog.dmm.mapper.UserMapper;
import com.ntnt.highblog.dmm.model.response.UserRes;
import com.ntnt.highblog.dmm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("internalUserListController")
@RequestMapping("/api/v1/internal/users")
public class UserListController {

    private final UserService userService;

    public UserListController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = "ids")
    public ResponseEntity<List<UserRes>> fetchUsersByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toListUserRes(new ArrayList<>(userService.fetchByIdIn(ids))));
    }
}
