package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.model.entity.PostVote;
import com.ntnt.highblog.dmm.model.request.PostVoteCreateReq;
import com.ntnt.highblog.dmm.model.request.PostVoteUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PostVoteMapper {

    PostVoteMapper INSTANCE = Mappers.getMapper(PostVoteMapper.class);

    PostVote toPostVote(PostVoteCreateReq postVoteCreateReq);

    PostVote toPostVote(PostVoteUpdateReq postVoteUpdateReq, @MappingTarget PostVote postVote);
}
