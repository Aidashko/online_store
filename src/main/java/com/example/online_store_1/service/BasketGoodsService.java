package com.example.online_store_1.service;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.BasketGoods;

import java.util.List;

public interface BasketGoodsService {
    void saveBasketGoods(BasketGoods basketGoods);
    List<BasketGoods> getAllByBasketAndPayed(Basket basket, boolean isBought);
}
