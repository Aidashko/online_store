package com.example.online_store_1.repository;

import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.BasketGoods;
import com.example.online_store_1.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketGoodsRepo extends JpaRepository<BasketGoods, Long> {
    List<BasketGoods> findAllByBasketAndPayed(Basket basket, boolean payed);
    BasketGoods findByBasketAndGood(Basket basket, Good good);
}
