package com.ntnt.highblog.dmm.api.external.common;

import com.ntnt.highblog.dmm.bloc.CommentListBloc;
import com.ntnt.highblog.dmm.mapper.CommentMapper;
import com.ntnt.highblog.dmm.model.entity.Comment;
import com.ntnt.highblog.dmm.model.request.CommentListReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentListController {

    private final CommentListBloc commentListBloc;

    public CommentListController(final CommentListBloc commentListBloc) {
        this.commentListBloc = commentListBloc;
    }

    // TODO: Return number of child comment
    @GetMapping
    public ResponseEntity<?> fetchListCommentForPost(final CommentListReq commentListReq) {

        List<Comment> comments = commentListBloc.fetchCommentsByPostId(commentListReq);
        return ResponseEntity.ok(CommentMapper.INSTANCE.toListCommentsRes(comments));
    }
    // TODO: Create api to fetch child comment by parentId
}
