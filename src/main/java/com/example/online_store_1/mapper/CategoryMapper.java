package com.example.online_store_1.mapper;

import com.example.online_store_1.models.Category;
import com.example.online_store_1.models.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);

    @Mappings({
            @Mapping(source = "name", target = "categoryName"),
            @Mapping(source = "active", target = "exists")})
    CategoryDto toCategoryDto(Category category);

    List<Category> toCategoryList(List<CategoryDto> categoryDtoList);

    List<CategoryDto> toCategoryDtoList(List<Category> categoryList);
}
