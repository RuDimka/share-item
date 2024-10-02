package com.project.share_item.controller;

import com.project.share_item.dao.User;
import com.project.share_item.dto.UserDto;
import com.project.share_item.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //добавление нового юзера
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    //получение всех юзеров
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    //запрос юзера по id
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //обновление юзера по id
    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUserById(id, userDto);
    }

    //удаление юзера по id
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}