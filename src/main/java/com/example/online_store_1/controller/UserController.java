package com.example.online_store_1.controller;

import com.example.online_store_1.models.User;
import com.example.online_store_1.models.dto.UserDto;
import com.example.online_store_1.service.FileStorageService;
import com.example.online_store_1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Работа с пользователем", description = "Эндпоинты для работы с пользователем")
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileStorageService storageService;

//    public UserController(UserService userService) {
//
//        this.userService = userService;
//    }

    //CRUD
    @Operation(description = "Создание нового пользователя", summary = "Создать ползователя")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан")
    })
    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @Operation(description = "Получение пользователя по ID", summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно получен!")
    })
    @GetMapping("/getById")
    public UserDto getById(@RequestParam Long id) {
        return userService.findById(id);
    }

    @Operation(description = "Получить всех пользователей", summary = "Создать всех ползователя")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Пользователи успешно созданы")
    })
    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {

        return userService.delete(user);
    }
    @PostMapping("/add_avatar")
    public void addImage(@RequestParam("file") MultipartFile file, @RequestParam Long id){
        storageService.addUserAvatar(file,id);
    }
}
