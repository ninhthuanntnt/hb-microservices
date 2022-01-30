package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.bloc.CommentCrudBloc;
import com.ntnt.highblog.dmm.model.request.CommentCreateReq;
import com.ntnt.highblog.dmm.model.request.CommentUpdateReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/comments")
public class CommentCrudController {

    private final CommentCrudBloc commentCrudBloc;

    public CommentCrudController(final CommentCrudBloc commentCrudBloc) {
        this.commentCrudBloc = commentCrudBloc;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody final CommentCreateReq commentCreateReq) {

        Long commentId = commentCrudBloc.createCommentForCurrentUser(commentCreateReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id,
                                           @RequestBody final CommentUpdateReq commentUpdateReq) {

        commentCrudBloc.updateCommentForCurrentUser(id, commentUpdateReq);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {

        commentCrudBloc.deleteCommentForCurrentUser(id);

        return ResponseEntity.noContent().build();
    }
}
