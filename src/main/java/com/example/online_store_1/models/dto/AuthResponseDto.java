package com.example.online_store_1.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthResponseDto {
    private String username;
    private String token;
    private Date expires;
}
