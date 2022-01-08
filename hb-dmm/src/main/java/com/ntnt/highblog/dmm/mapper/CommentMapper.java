package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.helper.DateTimeHelper;
import com.ntnt.highblog.dmm.model.entity.Comment;
import com.ntnt.highblog.dmm.model.request.CommentCreateReq;
import com.ntnt.highblog.dmm.model.response.CommentRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({
            @Mapping(target = "childCommentsRes", source = "childComments"),
            @Mapping(target = "userRes", source = "user")
    })
    CommentRes toCommentRes(Comment comment);

    Comment toComment(CommentCreateReq commentCreateReq);

    List<CommentRes> toListCommentsRes(List<Comment> comments);

    default Long toLongFromInstant(Instant instant) {
        return DateTimeHelper.toMilli(instant);
    }
}
