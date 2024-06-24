package com.example.online_store_1.models.dto;

import com.example.online_store_1.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String password;
    private String fio;
    private String email;
    private List<String> avatars;


    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fio = user.getFio();
        this.email = user.getEmail();
    }

    public UserDto() {
    }
}
