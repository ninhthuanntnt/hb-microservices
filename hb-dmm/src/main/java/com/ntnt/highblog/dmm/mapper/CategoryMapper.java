package com.ntnt.highblog.dmm.mapper;

import com.ntnt.highblog.dmm.model.entity.Category;
import com.ntnt.highblog.dmm.model.response.CategoryRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryRes> toListCategoryRes(List<Category> categories);

}
