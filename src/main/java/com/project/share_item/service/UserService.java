package com.project.share_item.service;

import com.project.share_item.dao.User;
import com.project.share_item.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUserById(Long id, UserDto userDto);

    User deleteUser(Long id);
}