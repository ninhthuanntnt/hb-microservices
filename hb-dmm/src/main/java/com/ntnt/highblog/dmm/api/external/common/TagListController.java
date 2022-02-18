package com.ntnt.highblog.dmm.api.external.common;

import com.ntnt.highblog.dmm.bloc.TagListBloc;
import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.mapper.TagMapper;
import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.model.response.BasePaginationRes;
import com.ntnt.highblog.dmm.model.response.TagRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagListController {

    private final TagListBloc tagListBloc;

    public TagListController(final TagListBloc tagListBloc) {
        this.tagListBloc = tagListBloc;
    }

    @GetMapping
    public ResponseEntity<List<TagRes>> fetchTags() {
        List<Tag> tags = tagListBloc.fetchAllTags();
        return ResponseEntity.ok(TagMapper.INSTANCE.toListTagRes(tags));
    }

    @GetMapping("/search")
    public ResponseEntity<BasePaginationRes> searchTag(final BasePaginationReq basePaginationReq,
                                                       final String tagName) {
        return ResponseEntity.ok(PaginationHelper
                                     .buildBasePaginationRes(tagListBloc.searchTag(basePaginationReq, tagName)
                                                                        .map(TagMapper.INSTANCE::toTagRes)));
    }
}
