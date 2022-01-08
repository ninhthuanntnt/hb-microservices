package com.ntnt.highblog.dmm.api.internal;

import com.ntnt.highblog.dmm.bloc.ProfileBloc;
import com.ntnt.highblog.dmm.model.response.UserRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileBloc profileBloc;

    public ProfileController(final ProfileBloc profileBloc) {
        this.profileBloc = profileBloc;
    }

    @GetMapping
    public ResponseEntity<UserRes> getProfile() {
        return ResponseEntity.ok(profileBloc.getForCurrentUser());
    }
}
