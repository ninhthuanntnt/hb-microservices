package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Comment;
import com.ntnt.highblog.dmm.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(final CommentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Comment> fetchByPostIdWithPageRequest(final Long postId, final Long parentId, final PageRequest pageRequest) {
        log.info("Fetch comments by post id #{} with pageRequest #{}", postId, pageRequest);

        return repository.fetchByPostIdAndParentId(postId, parentId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Comment getByIdAndUserId(final Long id, final Long userId) {
        log.info("Get comment by id #{} and userId #{}", id, userId);

        return repository.findByIdAndUserId(id, userId)
                         .orElseThrow(() -> new ObjectNotFoundException("comment"));
    }

    @Transactional(readOnly = true)
    public Comment getById(final Long id) {
        log.info("Get comment by id #{}", id);

        return repository.findById(id)
                         .orElseThrow(() -> new ObjectNotFoundException("comment"));
    }

    @Transactional
    public void saveNew(final Comment comment) {
        log.info("Save new comment with data #{}", comment);
        validateCommentBeforeSaveNew(comment);

        repository.save(comment);
    }

    @Transactional
    public void save(final Comment comment) {
        log.info("Save comment with data #{}", comment);
        validateCommentBeforeSave(comment);

        repository.save(comment);
    }

    @Transactional
    public void delete(final Long id, final Long userId) {
        log.info("Delete comment by id #{} with userId #{}", id, userId);

        Comment comment = repository.findByIdAndUserId(id, userId)
                                    .orElseThrow(() -> new ObjectNotFoundException("comment"));

        repository.delete(comment);
    }

    private void validateCommentBeforeSaveNew(final Comment comment) {
        if (ObjectUtils.isNotEmpty(comment.getId()))
            throw new ValidatorException("Invalid comment id", "id");
        if (ObjectUtils.isEmpty(comment.getUserId()) && comment.getUserId() != SecurityHelper.getCurrentUserId())
            throw new ValidatorException("Invalid user id", "userId");
    }

    private void validateCommentBeforeSave(final Comment comment) {
        if (ObjectUtils.isEmpty(comment.getId()))
            throw new ValidatorException("Invalid comment id", "id");
        if (ObjectUtils.isEmpty(comment.getUserId()) && comment.getUserId() != SecurityHelper.getCurrentUserId())
            throw new ValidatorException("Invalid user id", "userId");
    }
}
