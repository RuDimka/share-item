package com.project.share_item.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {

    private Long id;
    private Date start;
    private Date end;
    private String item;
    private String booker;
    private Status status;

}
