package com.ntnt.highblog.dmm.api.internal;

import com.ntnt.highblog.dmm.bloc.UserCrudBloc;
import com.ntnt.highblog.dmm.mapper.UserMapper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.RegisterReq;
import com.ntnt.highblog.dmm.model.response.UserRes;
import com.ntnt.highblog.dmm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController("internalUserCrudController")
@RequestMapping("/api/v1/internal/users")
public class UserCrudController {

    private final UserCrudBloc userCrudBloc;
    private final UserService userService;

    public UserCrudController(final UserCrudBloc userCrudBloc, final UserService userService) {
        this.userCrudBloc = userCrudBloc;
        this.userService = userService;
    }

    @GetMapping("/{nickName}")
    public ResponseEntity<?> getUserDetail(@PathVariable String nickName) {
        User user = userCrudBloc.getUserDetail(nickName);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDetailRes(user));
    }
//
//    @GetMapping(params = "accountId")
//    public ResponseEntity<?> getUserDetailByAccountId(@RequestParam Long accountId) {
//        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDetailRes(userService.getByAccountId(accountId)));
//    }

    @PostMapping
    public ResponseEntity<UserRes> createUser(@RequestBody RegisterReq registerReq) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserRes(userCrudBloc.create(registerReq)));
    }
}