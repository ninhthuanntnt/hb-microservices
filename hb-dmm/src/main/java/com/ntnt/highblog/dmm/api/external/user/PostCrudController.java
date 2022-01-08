package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.bloc.PostCrudBloc;
import com.ntnt.highblog.dmm.mapper.PostMapper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.request.PostCreateReq;
import com.ntnt.highblog.dmm.model.request.PostUpdateReq;
import com.ntnt.highblog.dmm.model.response.PostDetailToUpdateRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userPostCrudController")
@RequestMapping("/api/v1/user/posts")
public class PostCrudController {

    private final PostCrudBloc postCrudBloc;

    public PostCrudController(final PostCrudBloc postCrudBloc) {
        this.postCrudBloc = postCrudBloc;
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody final PostCreateReq postCreateReq) {

        Long id = postCrudBloc.createPost(postCreateReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailToUpdateRes> getPostDetailForCurrentUser(@PathVariable Long id) {

        Post post = postCrudBloc.getPostDetailForCurrentUser(id);

        return ResponseEntity.ok(PostMapper.INSTANCE.toPostDetailToUpdateRes(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable final Long id,
                                        @RequestBody final PostUpdateReq postUpdateReq) {
        postCrudBloc.updatePostForCurrentUser(id, postUpdateReq);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable final Long id) {

        postCrudBloc.deletePostForCurrentUser(id);

        return ResponseEntity.noContent().build();
    }
}
