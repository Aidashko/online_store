package com.example.online_store_1.service.impl;

import com.example.online_store_1.exception.UserNotFoundException;
import com.example.online_store_1.models.User;
import com.example.online_store_1.models.dto.AuthRequestDto;
import com.example.online_store_1.models.dto.AuthResponseDto;
import com.example.online_store_1.models.dto.RegistrationRequestDto;
import com.example.online_store_1.repository.BasketRepo;
import com.example.online_store_1.service.AuthService;
import com.example.online_store_1.service.MailService;
import com.example.online_store_1.service.UserService;
import com.example.online_store_1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserService userService; //сервис для управления пользователями, корзиной
    private final BasketRepo basketRepo;
    private final PasswordEncoder passwordEncoder; // для кодирования полей
    private final MailService mailService; //отправка почтовых сообщ

    @Override
    public ResponseEntity<?> authenticate(AuthRequestDto authRequestDto) {
        log.info("Auth attempt with data {}", authRequestDto); // аутентиф с данными аузреквестдто
        try {// ищет пользователя по имени пользователя
            userService.findByUsername(authRequestDto.getUsername())
                    .orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с логином '%s' не существует!", authRequestDto.getUsername())));
            //если не найден, срабатывает исключение
        } catch (UserNotFoundException e) {
            log.error("There is no user with username '{}'.", authRequestDto.getUsername());
            return ResponseEntity.status(458).body(String.format("Пользователя с логином '%s' не существует!", authRequestDto.getUsername()));
        }
        //проверяет пароль с пассвординкодэр. если совпадают, логируется возвращ токен
        if (passwordEncoder.matches(authRequestDto.getPassword(), userService.findByUsername(authRequestDto.getUsername()).get().getPassword())) {
            log.info("Authentication successful for user '{}'", authRequestDto.getUsername());
            return ResponseEntity.ok(jwtUtil.getTokenAndData(jwtUtil.generateToken(userService.loadUserByUsername(authRequestDto.getUsername()))));
        }
        //если не совпадает возвращает ответ с ошибкой
        log.warn("Invalid password attempt for user '{}'", authRequestDto.getUsername());
        return ResponseEntity.status(459).body("Неправильный пароль!");
    }

    @Override
    public ResponseEntity<?> registrateNewUser(RegistrationRequestDto registrationRequestDto) {
        if (!registrationRequestDto.getPassword().equals(registrationRequestDto.getConfirmPassword())) {
            log.warn("Passwords do not match for user '{}'", registrationRequestDto.getUsername());
            return ResponseEntity.status(400).body("Введенные пароли не совпадают!");
        } else if (userService.findByUsername(registrationRequestDto.getUsername()).isPresent()) {
            log.warn("User with username '{}' already exists", registrationRequestDto.getUsername());
            return ResponseEntity.status(400).body(String.format("Пользователя с логином '%s' уже существует!", registrationRequestDto.getUsername()));
        }
        User user = new User(registrationRequestDto);
        user.setActivationCode(generateRandomActivationCode());
        userService.saveUser(user);
        log.info("User '{}' registered successfully. Activation code sent to '{}'", user.getUsername(), user.getEmail());
        mailService.sendSimpleMessage(user.getEmail(), "Код активации аккаунта ", "Ваш код активации аккаунта " + user.getActivationCode());

        return authenticate(new AuthRequestDto(new User(registrationRequestDto)));
    }

    private Integer generateRandomActivationCode() {
        return (int) (Math.random() * 10000);
    }

    @Override
    public ResponseEntity<?> activateAccount(String email, Integer code) {
        log.info("Attempt to activate account with email '{}'", email); // активация с email
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getActivationCode().equals(code)) { // проверяет совпадения кода активации. если совпало возвращ успешное сообщение
                user.setIsActive(true);
                userService.saveUser(user);
                log.info("Account with email '{}' activated successfully", email);
                return ResponseEntity.ok("Аккаунт успешно активирован!");
            } else {
                //если не совпадает возвращает ошибку
                log.warn("Invalid activation code '{}' for email '{}'", code, email);
                return ResponseEntity.status(400).body("Неверный код активации!");
            }
        } else {
            // если не нашелся юзер приходит ошибка
            log.error("No user found with email '{}'", email);
            return ResponseEntity.status(404).body("Пользователь не найден!");
        }
    }
}