package com.example.online_store_1.service.impl;

import com.example.online_store_1.mapper.CategoryMapper;
import com.example.online_store_1.models.Category;
import com.example.online_store_1.models.dto.CategoryDto;
import com.example.online_store_1.repository.CategoryRepo;
import com.example.online_store_1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        return CategoryMapper.INSTANCE.toCategoryDtoList(categoryRepo.findAll());
    }
}
