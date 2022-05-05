package com.company.dto;

import com.company.enums.StudentBookStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentBookDTO {
    private String uuid;
    private String student_uuid;
    private String book_uuid;
    private LocalDateTime taken_date;
    private StudentBookStatus status;
}
