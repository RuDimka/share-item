package com.project.share_item.dao;


import lombok.Data;

import java.util.Date;

@Data
public class Booking {

    private Long id;

    private Date start;

    private Date end;

    private String booker;

    private String status;
}
