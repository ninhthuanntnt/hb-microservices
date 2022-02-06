package com.ntnt.highblog.payment.mapper;

import com.ntnt.highblog.payment.helper.DateTimeHelper;
import com.ntnt.highblog.payment.model.dto.response.UserTransactionRes;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface UserTransactionMapper {
    UserTransactionMapper INSTANCE = Mappers.getMapper(UserTransactionMapper.class);

    UserTransactionRes toUserTransactionRes(UserTransaction userTransaction);

    default Long toLongFromInstant(Instant instant) {
        return DateTimeHelper.toMilli(instant);
    }
}
