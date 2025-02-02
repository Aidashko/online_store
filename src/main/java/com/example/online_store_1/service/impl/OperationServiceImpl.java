package com.example.online_store_1.service.impl;

import com.example.online_store_1.enums.OperationType;
import com.example.online_store_1.mapper.GoodMapper;
import com.example.online_store_1.mapper.UserMapper;
import com.example.online_store_1.models.*;
import com.example.online_store_1.models.dto.RequestDto;
import com.example.online_store_1.models.dto.ResponseDto;
import com.example.online_store_1.repository.BasketGoodsRepo;
import com.example.online_store_1.repository.OperationRepo;
import com.example.online_store_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final UserService userService;
    private final GoodService goodService;
    private final BasketService basketService;
    private final BasketGoodsService basketGoodsService;
    private final UserMapper userMapper;
    private final GoodMapper goodMapper;
    private final BasketGoodsRepo basketGoodsRepo;

    @Override
    public Operation createOperation(Operation operation) {

        return null;
    }

    @Override
    public void addToUserBasket(RequestDto requestDto) {
        Basket basket = basketService.findByUser(requestDto.getUser());
        for(Good g:requestDto.getGood()){
            basketGoodsService.saveBasketGoods(new BasketGoods(basket, g));
        }
        Operation operation = new Operation();
        operation.setUser(requestDto.getUser());
        operation.setCreateDate(LocalDateTime.now());
        operation.setOperationType(OperationType.ADD_TO_CART);
        operationRepo.save(operation);
//        responseDto.getGood().forEach(good -> basketGoodsService.saveBasketGoods(new BasketGoods(basket,good)));
    }

    @Override
    public ResponseDto getUserBasketData(Long userId, boolean isBought) {
        User user = userService.getById(userId);
        Basket basket = basketService.findByUser(user);
        List<BasketGoods> basketGoodsList = basketGoodsService.getAllByBasketAndPayed(basket,isBought);
        return new ResponseDto(userMapper.toDto(user),goodMapper.toDtoList(basketGoodsList.stream().map(BasketGoods::getGood).toList()),isBought);
    }

    @Override
    public void buy(Long userId, Long productId) {
        User user = userService.getById(userId);
        Basket basket = basketService.findByUser(user);
        List<BasketGoods> basketGoodsList = basketGoodsService.getAllByBasketAndPayed(basket,false);
        for (BasketGoods basketGoods : basketGoodsList) {
            if (Objects.equals(basketGoods.getGood().getId(), productId)) {
                basketGoods.setPayed(true);
                basketGoodsRepo.save(basketGoods);
            }

        }

    }
}
