package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.bloc.FileCrudBloc;
import com.ntnt.highblog.dmm.helper.FileHelper;
import com.ntnt.highblog.dmm.mapper.FileMapper;
import com.ntnt.highblog.dmm.model.entity.File;
import com.ntnt.highblog.dmm.model.request.FileReq;
import com.ntnt.highblog.dmm.model.response.CkImageUploadRes;
import com.ntnt.highblog.dmm.model.response.FileRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user/files")
public class FileCrudController {

    private final FileCrudBloc fileCrudBloc;

    public FileCrudController(final FileCrudBloc fileCrudBloc) {
        this.fileCrudBloc = fileCrudBloc;
    }

    @PostMapping("/images")
    public ResponseEntity<FileRes> uploadImage(final FileReq fileReqs) {
        File image = fileCrudBloc.uploadImage(fileReqs);
        return ResponseEntity.ok(FileMapper.INSTANCE.toFileRes(image));
    }

    @PostMapping("/ck/images")
    public ResponseEntity<CkImageUploadRes> ckUploadImage(@RequestParam("upload") MultipartFile multipartFile) {
        File image = fileCrudBloc.ckUploadImage(multipartFile);
        return ResponseEntity.ok(new CkImageUploadRes(image.getPath()));
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable final Long id) {
        fileCrudBloc.deleteImageForCurrentUser(id);
        return ResponseEntity.noContent().build();
    }
}
