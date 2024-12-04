package com.project.share_item.entity;

import java.time.LocalDate;

public class Booking {

    private int id;
    private LocalDate start;
    private LocalDate end;
    private Item item;
    private User booker;
    private Status status;
}
