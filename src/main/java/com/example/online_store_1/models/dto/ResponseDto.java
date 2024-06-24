package com.example.online_store_1.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private UserDto user;
    private List<GoodDto> good;
    private boolean isBought;
}
