package com.project.share_item.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ItemStorageDao {

    private final Map<Long, Item> items = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

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