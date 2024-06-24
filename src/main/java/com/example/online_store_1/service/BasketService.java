package com.example.online_store_1.service;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.User;

public interface BasketService {
    Basket save(Basket basket);
    Basket findByUser(User user);
}
