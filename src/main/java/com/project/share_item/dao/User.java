package com.project.share_item.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Data
@Repository
public class User {

    private Map<Long, User> users = new HashMap<>();
    private AtomicLong userIdGenerator = new AtomicLong(1);

    private Long id;

    private String name;

    private String email;

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


    public User findByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; //возвращаем null если пользователь не найден
    }

    public boolean existsById(Long ownerId) {
        return !users.containsKey(ownerId);
    }
}
