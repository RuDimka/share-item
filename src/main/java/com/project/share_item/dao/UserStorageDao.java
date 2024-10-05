package com.project.share_item.dao;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserStorageDao {

    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong userIdGenerator = new AtomicLong(1);

    public User saveUser(User user) {
        user.setId(userIdGenerator.getAndIncrement());
        users.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User deleteUser(Long id) {
        return users.remove(id);
    }

    public User updateUser(User user) {
        return users.put(user.getId(), user);
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public boolean existsById(Long ownerId) {
        return !users.containsKey(ownerId);
    }
}