package com.project.share_item.service;

import com.project.share_item.dto.UserDto;
import com.project.share_item.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(UserDto userDto);

    UserDto getUserById(int id);

    UserDto updateUserById(int id, UserDto userDto);

    void deleteUser(Integer id);
}