package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TagListBloc {

    private final TagService tagService;

    public TagListBloc(final TagService tagService) {
        this.tagService = tagService;
    }

    public List<Tag> fetchAllTags() {
        log.info("Fetch all tags");

        // TODO: fetch tag with pagination and search for auto-complete
        return tagService.fetchAllTags();
    }
}
