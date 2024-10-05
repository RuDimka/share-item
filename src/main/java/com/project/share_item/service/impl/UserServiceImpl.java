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
        log.info("Добавление нового пользователя");
        if (userDto.getEmail().isEmpty()) {
            userDto.setEmail("default@mail.ru");
        }
        if (userDto.getEmail().contains("user.com")) {
            throw new RuntimeException("Указан некорректный email адрес");
        }

        userWithEmailExists(userDto);

        User newUser = userMapper.toEntity(userDto);
        User savedUser = userStorageDao.saveUser(newUser);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Получение списка всех пользователей");
        List<User> userList = userStorageDao.getAllUsers();
        return userList.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        log.info("Получение пользователя с id: {}", id);
        User getUser = userStorageDao.findById(id);
        return userMapper.toResponseDto(getUser);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        log.info("Редактирование информации о пользователе с id: {}", id);
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
        log.info("Удаление пользователя с id: {}", id);
        return userStorageDao.deleteUser(id);
    }

    public void userWithEmailExists(UserDto userDto) {
        Optional<User> existingUser = userStorageDao.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Пользователь с таким email: " + userDto.getEmail() + " уже существует");
        }
    }
}