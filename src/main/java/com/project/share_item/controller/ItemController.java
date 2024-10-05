package com.project.share_item.controller;

import com.project.share_item.dto.ItemDto;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import com.project.share_item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemResponseDto addNewItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                      @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Получен запрос на создание новой вещи от пользователя {}", userId);
        return itemService.addNewItem(userId, itemRequestDto);
    }

    @PatchMapping("/{itemId}")
    public ItemResponseDto updateItemById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @PathVariable Long itemId,
                                          @RequestBody ItemDto itemDto) {
        log.info("Получен запрос пользователем {} на редактирование вещи {}", userId, itemId);
        return itemService.updateItemById(userId, itemId, itemDto);
    }

    @GetMapping("/{id}")
    public ItemResponseDto getItemById(@PathVariable Long id,
                                       @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос пользователем {} на вещь {}", userId, id);
        return itemService.getItemById(id, userId);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("Получен запрос пользователем {} на получение списка вещей", ownerId);
        return ResponseEntity.ok().body(itemService.getAllItems(ownerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponseDto>> searchToItemsByText(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                                     @RequestParam String text) {
        log.info("Получен запрос от пользователя {} а поиск вещи {}", userId, text);
        return ResponseEntity.ok().body(itemService.searchToItemsByText(userId, text));

    }
}