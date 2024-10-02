package com.project.share_item.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Data
@Repository
public class Item {
    private Map<Long, Item> items = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private Long ownerId;

    private String request;

    public Item saveItem(Item item) {
        item.setId(idGenerator.getAndIncrement());
        items.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return items.get(id);
    }

    public List<Item> getAllItemsByOwner(Long ownerId) {
        return items.values()
                .stream()
                .filter(item -> item.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public List<Item> getAllItemsBySearch() {
        return new ArrayList<>(items.values());
    }

    public boolean existsItem(Long id) {
        return items.containsKey(id);
    }

}
