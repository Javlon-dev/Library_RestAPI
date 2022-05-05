package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {

    private String uuid;
    private String author;
    private String title;
    private LocalDateTime added_date;
}
