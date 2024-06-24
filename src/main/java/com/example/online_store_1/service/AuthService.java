package com.example.online_store_1.service;

import com.example.online_store_1.models.dto.AuthRequestDto;
import com.example.online_store_1.models.dto.AuthResponseDto;
import com.example.online_store_1.models.dto.RegistrationRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(AuthRequestDto authRequestDto);
    ResponseEntity<?> registrateNewUser(RegistrationRequestDto registrationRequestDto);
    ResponseEntity<?> activateAccount(String email, Integer code);
}
