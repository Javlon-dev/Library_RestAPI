package com.company.service;

import com.company.enums.StudentBookStatus;
import com.company.dto.BookDTO;
import com.company.dto.StudentBookDTO;
import com.company.dto.StudentDTO;
import com.company.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class StudentBookService {

    @Autowired
    private StudentBookRepository studentBookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    StudentService studentService;


    public String createStudentBook(StudentBookDTO dto) {
        Optional<StudentDTO> studentDTOOptional = Optional.ofNullable(studentService.getStudentByUuid(dto.getStudent_uuid()));
        if (studentDTOOptional.isEmpty()) {
            return "Error Student!";
        }
        Optional<BookDTO> bookDTOOptional = Optional.ofNullable(bookService.getBookByUuid(dto.getBook_uuid()));
        if (bookDTOOptional.isEmpty()) {
            return "Error Book!";
        }
        dto.setUuid(UUID.randomUUID().toString());
        dto.setTaken_date(LocalDateTime.now());
        dto.setStatus(StudentBookStatus.TAKEN);
        if (studentBookRepository.createStudentBook(dto) > 0) {
            return "Successfully created";
        } else return "Error into creation!";
    }

    public List<StudentBookDTO> getListStudentBook() {
        return studentBookRepository.getListStudentBook();
    }

    public StudentBookDTO getStudentBookUuid(String uuid) {
        for (StudentBookDTO studentBookDTO : getListStudentBook()) {
            if (studentBookDTO.getUuid().equals(uuid)) {
                return studentBookDTO;
            }
        }
        return null;
    }

    public Boolean updateStudentBook(String uuid) {
        return studentBookRepository.updateStudentBook(uuid) > 0;
    }

}
