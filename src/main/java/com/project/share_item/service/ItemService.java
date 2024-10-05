package com.project.share_item.service;

import com.project.share_item.dto.ItemDto;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    ItemResponseDto addNewItem(Long userId, ItemRequestDto itemRequestDto);

    ItemResponseDto updateItemById(Long userId, Long itemId, ItemDto itemDto);

    ItemResponseDto getItemById(Long id, Long userId);

    List<ItemResponseDto> getAllItems(Long ownerId);

    List<ItemResponseDto> searchToItemsByText(Long userId, String text);
}