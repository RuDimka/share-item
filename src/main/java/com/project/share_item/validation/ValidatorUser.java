package com.project.share_item.validation;

import com.project.share_item.Repository.UserRepository;
import com.project.share_item.dto.UserDto;
import com.project.share_item.entity.User;
import com.project.share_item.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ValidatorUser {

    private final UserRepository userRepository;

    public void userWithEmailExists(UserDto userDto) {
        Optional<User> existingUser = userRepository.findUserByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new UserNotFoundException("Пользователь с таким email: " + userDto.getEmail() + " уже существует");
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