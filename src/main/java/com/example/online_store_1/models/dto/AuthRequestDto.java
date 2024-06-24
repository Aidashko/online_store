package com.example.online_store_1.models.dto;

import com.example.online_store_1.models.User;
import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;

    public AuthRequestDto() {
    }

    public AuthRequestDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
