package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.model.entity.Subscription;
import com.ntnt.highblog.dmm.model.response.SubscriptionRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    List<SubscriptionRes> toListSubscriptionRes(List<Subscription> subscriptions);

    SubscriptionRes toSubscriptionRes(Subscription subscription);
}
