package com.project.share_item.mapper;

import com.project.share_item.dao.Item;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {

    Item toEntity(ItemRequestDto itemRequestDto);

    ItemResponseDto toResponseDto(Item item);

    ItemRequestDto itemToItemRequestDto(Item item);

    List<ItemResponseDto> toResponseDtoList(List<Item> items);
}
