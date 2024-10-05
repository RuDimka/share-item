package com.project.share_item.service.impl;

import com.project.share_item.dao.User;
import com.project.share_item.dao.UserStorageDao;
import com.project.share_item.dto.UserDto;
import com.project.share_item.mapper.UserMapper;
import com.project.share_item.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserStorageDao userStorageDao;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Добавлен пользователь {} ", userDto.getName());
        validateEmail(userDto);
        userWithEmailExists(userDto);

        User newUser = userMapper.toEntity(userDto);
        User savedUser = userStorageDao.saveUser(newUser);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Получен список всех пользователей");
        List<User> userList = userStorageDao.getAllUsers();
        return userList.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        log.info("Получен пользователь {}", id);
        User getUser = userStorageDao.findById(id);
        return userMapper.toResponseDto(getUser);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        log.info("Информации о пользователе {} обновлена", id);
        User updateUser = userStorageDao.findById(id);
        userWithEmailExists(userDto);
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            updateUser.setName(userDto.getName());
        }
        User userUpdate = userStorageDao.updateUser(updateUser);
        return userMapper.toResponseDto(userUpdate);
    }

    @Override
    public User deleteUser(Long id) {
        log.info("Пользователя {} удален", id);
        return userStorageDao.deleteUser(id);
    }

    public void userWithEmailExists(UserDto userDto) {
        Optional<User> existingUser = userStorageDao.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Пользователь с таким email: " + userDto.getEmail() + " уже существует");
        }
    }

    public void validateEmail(UserDto userDto) {
        if (userDto.getEmail().isEmpty()) {
            userDto.setEmail("default@mail.ru");
        }
        String emailRegex = userDto.getEmail();
        if (!emailRegex.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new RuntimeException("Указан некорректный email адрес");
        }
    }
}