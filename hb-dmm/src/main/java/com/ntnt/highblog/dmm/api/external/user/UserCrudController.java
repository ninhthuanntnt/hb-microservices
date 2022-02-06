package com.ntnt.highblog.dmm.api.external.user;


import com.ntnt.highblog.dmm.bloc.UserCrudBloc;
import com.ntnt.highblog.dmm.model.request.UserUpdateReq;
import com.ntnt.highblog.dmm.model.response.ImageUploadRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("userUserCrudBloc")
@RequestMapping("/api/v1/user")
public class UserCrudController {

    private final UserCrudBloc userCrudBloc;

    public UserCrudController(final UserCrudBloc userCrudBloc) {
        this.userCrudBloc = userCrudBloc;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody final UserUpdateReq userUpdateReq) {
        userCrudBloc.updateUser(userUpdateReq);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/avatar")
    public ResponseEntity<ImageUploadRes> updateAvatar(@RequestParam("upload") MultipartFile avatarReq) {
        String path = userCrudBloc.uploadAvatar(avatarReq);
        return ResponseEntity.ok(new ImageUploadRes(path));
    }

    @PutMapping("/background")
    public ResponseEntity<ImageUploadRes> updateBackground(@RequestParam("upload") MultipartFile backgroundReq) {
        String path = userCrudBloc.updateBackground(backgroundReq);
        return ResponseEntity.ok(new ImageUploadRes(path));
    }
}