package com.ntnt.highblog.dmm.api.internal;

import com.ntnt.highblog.dmm.bloc.UserCrudBloc;
import com.ntnt.highblog.dmm.mapper.UserMapper;
import com.ntnt.highblog.dmm.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("internalUserCrudController")
@RequestMapping("/api/v1/internal/users")
public class UserCrudController {

    private final UserCrudBloc userCrudBloc;

    public UserCrudController(final UserCrudBloc userCrudBloc) {
        this.userCrudBloc = userCrudBloc;
    }

    @GetMapping("/{nickName}")
    public ResponseEntity<?> getUserDetail(@PathVariable String nickName) {
        User user = userCrudBloc.getUserDetail(nickName);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDetailRes(user));
    }
}