package com.project.share_item.service.impl;

import com.project.share_item.Repository.ItemRepository;
import com.project.share_item.Repository.UserRepository;
import com.project.share_item.entity.Item;
import com.project.share_item.dto.ItemDto;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import com.project.share_item.exceptions.ObjectNotFoundExceptions;
import com.project.share_item.mapper.ItemMapper;
import com.project.share_item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    //private final AtomicLong itemIdGenerator = new AtomicLong(1);
    private final UserRepository userRepository;

    @Override
    public ItemResponseDto addNewItem(Long userId, ItemRequestDto itemRequestDto) {
        log.info("Вещь {} добавлена пользователем {}", itemRequestDto.getName(), userId);
        validateUser(userId);
        validateItemDto(itemRequestDto);

        Item newItem = itemMapper.toEntity(itemRequestDto);
        newItem.setOwnerId(userId);
        //newItem.setId(itemIdGenerator.getAndIncrement());

        //Item savedItem = itemRepository.saveItem(newItem);
        //return itemMapper.toResponseDto(savedItem);
        return null;
    }

    @Override
    public ItemResponseDto updateItemById(Long userId, Long itemId, ItemDto itemDto) {
        log.info("Пользователь {} отредактировал вещь {}", userId, itemId);
        //Item updateItem = itemRepository.findById(itemId);
        //validateItem(itemId);
        validateUser(userId);

//        if (itemDto.getName() != null) {
//            updateItem.setName(itemDto.getName());
//        }
//        if (itemDto.getDescription() != null) {
//            updateItem.setDescription(itemDto.getDescription());
//        }
//        if (itemDto.getAvailable() != null) {
//            updateItem.setAvailable(itemDto.getAvailable());
//        }
//        itemMapper.itemToItemRequestDto(updateItem);
//        itemRepository.saveItem(updateItem);
//        return itemMapper.toResponseDto(updateItem);
        return null;
    }

    @Override
    public ItemResponseDto getItemById(Long id, Long userId) {
        log.info("Получен предмета {}", id);
        //Item getItem = itemRepository.findById(id);
        //return itemMapper.toResponseDto(getItem);
        return null;
    }

    @Override
    public List<ItemResponseDto> getAllItems(Long ownerId) {
        log.info("Получен список вещей");
        //List<Item> itemList = itemRepository.getAllItemsByOwner(ownerId);
        //return itemMapper.toResponseDtoList(itemList);
        return null;
    }

    @Override
    public List<ItemResponseDto> searchToItemsByText(Long userId, String text) {
        log.info("Пользователь {} нашёл вещь {}", userId, text);
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
//        String searchQuery = text.toLowerCase();
//        return itemRepository.getAllItemsBySearch().stream()
////                .filter(Item::getAvailable)
////                .filter(item -> item.getName().toLowerCase().contains(searchQuery)
////                        || item.getDescription().contains(searchQuery))
////                .map(itemMapper::toResponseDto)
//                .collect(Collectors.toList());
        return null;
    }

    public void validateUser(Long userId) {
        if (userRepository.existsById(Math.toIntExact(userId))) {
            throw new ObjectNotFoundExceptions(HttpStatus.NOT_FOUND);
        }
    }

    public void validateItemDto(ItemRequestDto itemRequestDto) {
        if (itemRequestDto.getName() == null || itemRequestDto.getName().isEmpty()) {
            throw new RuntimeException("Не введено имя пользователя");
        }

        if (itemRequestDto.getDescription() == null || itemRequestDto.getDescription().isEmpty()) {
            throw new RuntimeException("Не заполнено описание");
        }

        if (itemRequestDto.getAvailable() == null) {
            throw new RuntimeException("Отсутствует поле доступности");
        }
    }

//    public void validateItem(Long itemId) {
//        if (!itemRepository.existsItem(itemId)) {
//            throw new ObjectNotFoundExceptions(HttpStatus.NOT_FOUND);
//        }
//    }
}