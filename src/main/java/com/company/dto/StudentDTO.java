package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private String uuid;
    private String firstname;
    private String lastname;
    private String phone;
    private LocalDateTime added_date;
}
