package com.ntnt.highblog.notification.mapper;

import com.ntnt.highblog.notification.model.dto.response.NotificationRes;
import com.ntnt.highblog.notification.model.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    //    @Mapping(target = "senderRes", source = "sender")
    NotificationRes toNotificationRes(Notification notification);

    List<NotificationRes> toListNotificationRes(List<Notification> notification);
}
