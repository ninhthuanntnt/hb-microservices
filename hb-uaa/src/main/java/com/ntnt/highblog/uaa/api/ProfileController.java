package com.ntnt.highblog.uaa.api;

import com.ntnt.highblog.uaa.bloc.ProfileBloc;
import com.ntnt.highblog.uaa.model.dto.response.ProfileRes;
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
    public ResponseEntity<ProfileRes> profile() {
        return ResponseEntity.ok(profileBloc.getProfileResForCurrentUser());
    }
}
