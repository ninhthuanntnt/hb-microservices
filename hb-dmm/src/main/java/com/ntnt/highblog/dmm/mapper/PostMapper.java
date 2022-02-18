package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.helper.DateTimeHelper;
import com.ntnt.highblog.dmm.model.entity.Post;
import com.ntnt.highblog.dmm.model.request.PostCreateReq;
import com.ntnt.highblog.dmm.model.request.PostUpdateReq;
import com.ntnt.highblog.dmm.model.response.PostDetailRes;
import com.ntnt.highblog.dmm.model.response.PostDetailToUpdateRes;
import com.ntnt.highblog.dmm.model.response.PostRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PostTagMapper.class,
                                                               TagMapper.class})
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post toPost(PostCreateReq postCreateReq);

    @Mapping(target = "postTags", source = "tagCreateReqs")
    Post toPost(PostUpdateReq postUpdateReq, @MappingTarget Post post);

    @Mapping(target = "tagsRes", source = "postTags")
    PostDetailToUpdateRes toPostDetailToUpdateRes(Post post);

    @Mapping(target = "numberOfVotes", source = "postStatistic.numberOfVotes")
    @Mapping(target = "numberOfComments", source = "postStatistic.numberOfComments")
    @Mapping(target = "numberOfFavorites", source = "postStatistic.numberOfFavorites")
    @Mapping(target = "tagsRes", source = "postTags")
    @Mapping(target = "postVoteRes", source = "postVote")
    @Mapping(target = "userRes", source = "user")
    PostDetailRes toPostDetailRes(Post post);

    @Mapping(target = "numberOfVotes", source = "postStatistic.numberOfVotes")
    @Mapping(target = "numberOfComments", source = "postStatistic.numberOfComments")
    @Mapping(target = "numberOfFavorites", source = "postStatistic.numberOfFavorites")
    @Mapping(target = "tagsRes", source = "postTags")
    @Mapping(target = "userRes", source = "user")
    PostRes toPostRes(Post posts);

    default Long toLongFromInstant(Instant instant) {
        return DateTimeHelper.toMilli(instant);
    }
}
