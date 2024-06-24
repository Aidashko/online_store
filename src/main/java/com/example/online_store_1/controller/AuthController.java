package com.example.online_store_1.controller;

import com.example.online_store_1.models.dto.ActivationRequestDto;
import com.example.online_store_1.models.dto.AuthRequestDto;
import com.example.online_store_1.models.dto.AuthResponseDto;
import com.example.online_store_1.models.dto.RegistrationRequestDto;
import com.example.online_store_1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto requestDto) {
        return authService.registrateNewUser(requestDto);
    }
    @PostMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestBody ActivationRequestDto activationRequestDto) {
        return authService.activateAccount(activationRequestDto.getEmail(), activationRequestDto.getCode());
    }
}
