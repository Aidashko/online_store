package com.example.online_store_1.controller;

import com.example.online_store_1.models.dto.RequestDto;
import com.example.online_store_1.models.dto.ResponseDto;
import com.example.online_store_1.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;
    @PostMapping("/add_to_cart")
    public void addToCart(@RequestBody RequestDto requestDto) {
        operationService.addToUserBasket(requestDto);
    }
    @GetMapping("/get_cart_data")
    public ResponseDto getUserCartData(@RequestParam Long userId, @RequestParam boolean isBought) {
        return operationService.getUserBasketData(userId, isBought);
    }
    @PostMapping("/buy")
    public void buy(@RequestParam Long userId,@RequestParam Long goodId) {
        operationService.buy(userId, goodId);
    }
}
