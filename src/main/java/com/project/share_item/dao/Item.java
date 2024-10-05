package com.project.share_item.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Item {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private Long ownerId;
    private String request;
}