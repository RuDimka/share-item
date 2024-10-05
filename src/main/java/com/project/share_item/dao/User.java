package com.project.share_item.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class User {
    private Long id;
    private String name;
    private String email;
}