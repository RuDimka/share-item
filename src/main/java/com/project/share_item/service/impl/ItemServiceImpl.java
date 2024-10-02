package com.project.share_item.service.impl;

import com.project.share_item.dao.Item;
import com.project.share_item.dao.User;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import com.project.share_item.exceptions.ObjectNotFoundExceptions;
import com.project.share_item.mapper.ItemMapper;
import com.project.share_item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final Item item;
    private final User user;
    private final ItemMapper itemMapper;
    private final AtomicLong itemIdGenerator = new AtomicLong(1);

    @Override
    public ItemResponseDto addNewItem(Long userId, ItemRequestDto itemRequestDto) {

        if (user.existsById(userId)) {
            throw new ObjectNotFoundExceptions(HttpStatus.NOT_FOUND);
        }

        if (itemRequestDto.getName() == null || itemRequestDto.getName().isEmpty()) {
            throw new RuntimeException("Не введено имя пользователя");
        }

        if (itemRequestDto.getDescription() == null || itemRequestDto.getDescription().isEmpty()) {
            throw new RuntimeException("Не заполнено описание");
        }

        if (itemRequestDto.getAvailable() == null) {
            throw new RuntimeException("Отсутствует поле доступности");
        }

        Item newItem = itemMapper.toEntity(itemRequestDto);
        newItem.setOwnerId(userId);
        newItem.setId(itemIdGenerator.getAndIncrement());

        Item savedItem = item.saveItem(newItem);
        return itemMapper.toResponseDto(savedItem);
    }

    @Override
    public ItemResponseDto updateItemById(Long userId, Long itemId, ItemRequestDto itemRequestDto) {
        Item updateItem = item.findById(itemId);

        if (user.existsById(userId)) {
            throw new ObjectNotFoundExceptions(HttpStatus.NOT_FOUND);
        }
        if (itemId.equals(item.getOwnerId())) {
            if (!item.existsItem(itemId)) {
                throw new ObjectNotFoundExceptions(HttpStatus.NOT_FOUND);
            }
        }
        if (itemRequestDto.getName() != null) {
            updateItem.setName(itemRequestDto.getName());
        }
        if (itemRequestDto.getDescription() != null) {
            updateItem.setDescription(itemRequestDto.getDescription());
        }
        if (itemRequestDto.getAvailable() != null) {
            updateItem.setAvailable(itemRequestDto.getAvailable());
        }
        itemMapper.itemToItemRequestDto(updateItem);
        item.saveItem(updateItem);
        return itemMapper.toResponseDto(updateItem);
    }

    @Override
    public ItemResponseDto getItemById(Long id, Long userId) {
        Item getItem = item.findById(id);
        return itemMapper.toResponseDto(getItem);
    }

    @Override
    public List<ItemResponseDto> getAllItems(Long ownerId) {
        List<Item> itemList = item.getAllItemsByOwner(ownerId);
        return itemMapper.toResponseDtoList(itemList);
    }

    @Override
    public List<ItemResponseDto> searchToItemsByText(Long userId, String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        String searchQuery = text.toLowerCase();
        return item.getAllItemsBySearch().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().toLowerCase().contains(searchQuery)
                        || item.getDescription().contains(searchQuery))
                .map(itemMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
