package com.ntnt.highblog.dmm.api.external.common;

import com.ntnt.highblog.dmm.bloc.PostCrudBloc;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.response.PostDetailRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostCrudController {
    private final PostCrudBloc postCrudBloc;

    public PostCrudController(final PostCrudBloc postCrudBloc) {
        this.postCrudBloc = postCrudBloc;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailRes> getPostDetail(@PathVariable Long id) {

        Post post = postCrudBloc.getPostDetail(id);

        return ResponseEntity.ok(PostMapper.INSTANCE.toPostDetailRes(post));
    }
}
