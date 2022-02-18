package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

    private final TagRepository repository;

    public TagService(final TagRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Tag> fetchAllTags() {
        log.info("Fetch all tags");

        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Tag> searchTag(final String tagName, final Pageable pageable) {
        log.info("Search tag by name #{}", tagName);

        return repository.fetchByNameLike(tagName, pageable);
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

    @Transactional(readOnly = true)
    public List<Tag> fetchExistAndNotByListTagNames(final List<String> tagNames) {
        log.info("Filter tags that not exists in database");

        List<Tag> tags = repository.getTagNameByNameIn(tagNames);
        Map<String, Tag> tagNamesMap = tags.stream()
                                           .collect(Collectors.toMap(Tag::getName, tag -> tag));

        return tagNames.stream()
                       .map(name -> {
                           if (tagNamesMap.containsKey(name))
                               return tagNamesMap.get(name);
                           else
                               return Tag.builder()
                                         .name(name)
                                         .build();
                       })
                       .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Tag> fetchByPostId(Long postId) {
        log.info("Fetch by postId #{}", postId);

        return repository.fetchByPostId(postId);
    }

    @Transactional
    public void saveAll(List<Tag> tags) {
        log.info("Save all tags");

        repository.saveAll(tags);
    }

    @Transactional
    public void deleteTag(final Long tagId) {
        log.info("delete tag by tagId#{}", tagId);
        repository.findById(tagId)
                  .orElseThrow(() -> new ObjectNotFoundException("tag not found"));
        repository.deleteById(tagId);
    }
}
