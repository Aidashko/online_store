package com.example.online_store_1.controller;

import com.example.online_store_1.models.Category;
import com.example.online_store_1.models.dto.CategoryDto;
import com.example.online_store_1.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/save")
    public void saveCategory(@RequestBody Category category) {
        categoryService.save(category);
    }
    @GetMapping("/get_all")
    public List<CategoryDto> getAll(){
        return   categoryService.findAll();
    }
}
