package com.example.online_store_1.models.dto;

import com.example.online_store_1.models.Good;
import com.example.online_store_1.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RequestDto {
    private User user;
    private List<Good> good;
    private boolean isBought;
}
