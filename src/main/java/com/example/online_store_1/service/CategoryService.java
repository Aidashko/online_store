package com.example.online_store_1.service;

import com.example.online_store_1.models.Category;
import com.example.online_store_1.models.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    List<CategoryDto> findAll();
}
