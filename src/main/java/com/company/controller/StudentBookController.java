package com.company.controller;

import com.company.dto.StudentBookDTO;
import com.company.dto.StudentDTO;
import com.company.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/studentbook")
public class StudentBookController {

    @Autowired
    private StudentBookService studentBookService;


    @PostMapping("/create")
    public String createStudentBook(@RequestBody StudentBookDTO dto) {
        return studentBookService.createStudentBook(dto);
    }

    @GetMapping("/list")
    public List<StudentBookDTO> getListStudentBook() {
        return studentBookService.getListStudentBook();
    }

    @GetMapping("/get/{student_uuid}/list")
    public List<StudentBookDTO> studentBookList(@PathVariable("student_uuid") String student_uuid) {
        List<StudentBookDTO> list = new LinkedList<>();
        for (StudentBookDTO dto : studentBookService.getListStudentBook()) {
            if (dto.getStudent_uuid().equals(student_uuid)) {
                list.add(dto);
            }
        }
        return list;
    }

    @PutMapping("/update/{uuid}")
    public Boolean updateStudentBook(@PathVariable("uuid") String uuid) {
        return studentBookService.updateStudentBook(uuid);
    }

}
