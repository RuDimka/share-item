package com.project.share_item.service.impl;

import com.project.share_item.Repository.UserRepository;
import com.project.share_item.dto.UserDto;
import com.project.share_item.entity.User;
import com.project.share_item.mapper.UserMapper;
import com.project.share_item.service.UserService;
import com.project.share_item.validation.ValidatorUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ValidatorUser validatorUser;

    @Override
    public User createUser(UserDto userDto) {
        log.info("Добавлен пользователь {} ", userDto.getName());
        validatorUser.userWithEmailExists(userDto);
        validatorUser.validateEmail(userDto);
        return userRepository.save(userMapper.toEntity(userDto));

    }

    public UserDto getUserById(int id) {
        log.info("Получен пользователь {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toResponseDto).orElse(null);
    }

    @Override
    public UserDto updateUserById(int id, UserDto userDto) {
        log.info("Информации о пользователе {} обновлена", id);
        User updateUser = userRepository.findById(id).get();
        validatorUser.userWithEmailExists(userDto);
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            updateUser.setName(userDto.getName());
        }
        User userUpdate = userRepository.save(updateUser);
        return userMapper.toResponseDto(userUpdate);
    }

    @Override
    public void deleteUser(Integer id) {
        log.info("Пользователя {} удален", id);
        userRepository.deleteById(id);
    }
}