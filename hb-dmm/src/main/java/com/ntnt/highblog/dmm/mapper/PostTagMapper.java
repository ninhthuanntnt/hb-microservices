package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.model.entity.PostTag;
import com.ntnt.highblog.dmm.model.request.TagCreateReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PostTagMapper {
    PostTagMapper INSTANCE = Mappers.getMapper(PostTagMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    PostTag toPostTag(TagCreateReq tagCreateReq);
}
