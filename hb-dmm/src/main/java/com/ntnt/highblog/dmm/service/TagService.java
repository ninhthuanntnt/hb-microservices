package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TagService {

    private final TagRepository repository;

    public TagService(final TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> fetchAllTags() {
        log.info("Fetch all tags");

        return repository.findAll();
    }

    @Transactional
    public void addNewTag(final Tag newTag) {
        validatePostBeforeSave(newTag.getName());
        log.info("add new tag");
        repository.save(newTag);
    }

    private void validatePostBeforeSave(final String tagName) {
        if (repository.existsTagByNameEquals(tagName))
            throw new ValidatorException("TagName already exists", "tagName");
    }

    @Transactional
    public void updateTag(final Long tagId, final String tagName) {
        Tag tag = repository.findById(tagId)
                .orElseThrow(() -> new ObjectNotFoundException("tag not found"));
        log.info("update tag #{} to #{}", tag.getName(), tagName);
        tag.setName(tagName);
        repository.save(tag);
    }

    @Transactional
    public void deleteTag(final Long tagId) {
        log.info("delete tag by tagId#{}", tagId);
        repository.findById(tagId)
                .orElseThrow(() -> new ObjectNotFoundException("tag not found"));
        repository.deleteById(tagId);
    }
}
