package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.helper.DateTimeHelper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.UserUpdateReq;
import com.ntnt.highblog.dmm.model.response.UserDetailRes;
import com.ntnt.highblog.dmm.model.response.UserRes;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRes toUserRes(User user);

    UserDetailRes toUserDetailRes(User user);

    List<UserRes> toListUserRes(List<User> users);

    User toUser(UserUpdateReq userUpdateReq, @MappingTarget User dbUser);

    default Long toLongFromInstant(Instant instant) {
        return DateTimeHelper.toMilli(instant);
    }
}
