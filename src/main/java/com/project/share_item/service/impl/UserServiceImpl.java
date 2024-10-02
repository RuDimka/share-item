package com.project.share_item.service.impl;

import com.project.share_item.dao.User;
import com.project.share_item.dto.UserDto;
import com.project.share_item.mapper.UserMapper;
import com.project.share_item.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final User user;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail().isEmpty()) {
            userDto.setEmail("default@mail.ru");
        }
        if (userDto.getEmail().contains("user.com")) {
            throw new RuntimeException("Указан некорректный email адрес");
        }

        userWithEmailExists(userDto);

        User newUser = userMapper.toEntity(userDto);
        User savedUser = user.saveUser(newUser);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = user.getAllUsers();
        return userList.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User getUser = user.findById(id);
        return userMapper.toResponseDto(getUser);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User updateUser = user.findById(id);
        userWithEmailExists(userDto);
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            updateUser.setName(userDto.getName());
        }
        User userUpdate = user.updateUser(updateUser);
        return userMapper.toResponseDto(userUpdate);
    }

    @Override
    public User deleteUser(Long id) {
        return user.deleteUser(id);
    }

    public void userWithEmailExists(UserDto userDto) {
        User existingUser = user.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Пользователь с таким email: " + userDto.getEmail() + " уже существует");
        }
    }
}