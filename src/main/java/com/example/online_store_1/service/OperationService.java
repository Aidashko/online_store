package com.example.online_store_1.service;

import com.example.online_store_1.models.Operation;
import com.example.online_store_1.models.dto.RequestDto;
import com.example.online_store_1.models.dto.ResponseDto;

public interface OperationService {
    Operation createOperation(Operation operation);
    void addToUserBasket(RequestDto requestDto);
    ResponseDto getUserBasketData(Long userId, boolean isBought);
    void buy(Long userId, Long productId);
}
