package com.example.online_store_1.models.dto;

import lombok.Data;

//Запрос активации dto
@Data
public class ActivationRequestDto {
    private String email;
    private Integer code;
}
