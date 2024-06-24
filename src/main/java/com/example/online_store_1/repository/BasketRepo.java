package com.example.online_store_1.repository;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUserId(Long userId);
    Optional<Basket>findByUser(User user);
}
