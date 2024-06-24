package com.example.online_store_1.service.impl;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.BasketGoods;
import com.example.online_store_1.repository.BasketGoodsRepo;
import com.example.online_store_1.service.BasketGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketGoodsServiceImpl implements BasketGoodsService {
    private final BasketGoodsRepo basketGoodsRepo;
    @Override
    public void saveBasketGoods(BasketGoods basketGoods) {
        basketGoodsRepo.save(basketGoods);
    }


    @Override
    public List<BasketGoods> getAllByBasketAndPayed(Basket basket, boolean isBought) {
        return basketGoodsRepo.findAllByBasketAndPayed(basket,isBought);
    }
}
