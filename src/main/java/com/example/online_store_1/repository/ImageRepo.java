package com.example.online_store_1.repository;

import com.example.online_store_1.models.Image;
import com.example.online_store_1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findByUser(User user);
}
