package com.project.share_item.controller;

import com.project.share_item.dao.User;
import com.project.share_item.dto.UserDto;
import com.project.share_item.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.info("Получен запрос на добавление нового пользователя");
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получен запрос на вывод информации обо всех пользователях");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.info("Получен запрос на вывод информации о пользователе {}", id);
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("Получен запрос на редактирование пользователя {}", id);
        return userService.updateUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя {}", id);
        return userService.deleteUser(id);
    }
}