package com.project.share_item.mapper;

import com.project.share_item.entity.User;
import com.project.share_item.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toResponseDto(User user);
}