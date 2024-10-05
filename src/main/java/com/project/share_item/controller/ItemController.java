package com.project.share_item.controller;

import com.project.share_item.dto.ItemDto;
import com.project.share_item.dto.ItemRequestDto;
import com.project.share_item.dto.ItemResponseDto;
import com.project.share_item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemResponseDto addNewItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                      @RequestBody ItemRequestDto itemRequestDto) {
        return itemService.addNewItem(userId, itemRequestDto);
    }

    @PatchMapping("/{itemId}")
    public ItemResponseDto updateItemById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @PathVariable Long itemId,
                                          @RequestBody ItemDto itemDto) {
        return itemService.updateItemById(userId, itemId, itemDto);
    }

    @GetMapping("/{id}")
    public ItemResponseDto getItemById(@PathVariable Long id,
                                       @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemById(id, userId);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return ResponseEntity.ok().body(itemService.getAllItems(ownerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponseDto>> searchToItemsByText(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                                     @RequestParam String text) {
        return ResponseEntity.ok().body(itemService.searchToItemsByText(userId, text));

    }
}