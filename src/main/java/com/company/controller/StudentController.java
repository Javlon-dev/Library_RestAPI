package com.company.controller;

import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/create")
    public String createStudent(@RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping("/list")
    public List<StudentDTO> getListStudent() {
        return studentService.getListStudent();
    }

    @GetMapping("/get/{uuid}")
    public StudentDTO getStudentByUuid(@PathVariable("uuid") String uuid) {
        return studentService.getStudentByUuid(uuid);
    }

    @PutMapping("/update/{uuid}")
    public Boolean updateStudent(@PathVariable("uuid") String uuid, @RequestBody StudentDTO dto) {
        return studentService.updateStudent(uuid, dto);
    }

    @DeleteMapping("/delete/{uuid}")
    public Boolean deleteStudent(@PathVariable("uuid") String uuid) {
        return studentService.deleteStudent(uuid);
    }
}
