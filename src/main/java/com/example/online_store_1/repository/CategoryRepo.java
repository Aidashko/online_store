package com.example.online_store_1.repository;

import com.example.online_store_1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
