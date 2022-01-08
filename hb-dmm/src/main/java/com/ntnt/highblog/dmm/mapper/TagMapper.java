package com.ntnt.highblog.dmm.mapper;


import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.entity.Tag;
import com.ntnt.highblog.dmm.model.response.TagRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagRes toTagRes(Tag tag);

    List<TagRes> toListTagRes(List<Tag> tags);

    @Mapping(target = "id", source = "tagId")
    TagRes toTagRes(PostTag postTag);
}
