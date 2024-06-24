package com.example.online_store_1.service.impl;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.User;
import com.example.online_store_1.repository.BasketRepo;
import com.example.online_store_1.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepo basketRepo;
    @Override
    public Basket save(Basket basket) {
        return basketRepo.save(basket);
    }

    @Override
    public Basket findByUser(User user) {
        return basketRepo.findByUser(user).orElse(null);
    }
}
