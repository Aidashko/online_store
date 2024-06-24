package com.example.online_store_1.controller;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    @PostMapping("/save")
    public void saveBasket(@RequestBody Basket basket){
        basketService.save(basket);
    }
}
