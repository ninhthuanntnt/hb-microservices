package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.model.entity.File;
import com.ntnt.highblog.dmm.model.response.FileRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileRes toFileRes(File file);
}
