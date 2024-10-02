package com.project.share_item.dto;

import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private Long owner;
    private String request;
}
