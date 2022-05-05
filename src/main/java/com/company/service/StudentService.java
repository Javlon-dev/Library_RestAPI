package com.company.service;

import com.company.controller.StudentController;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String createStudent(StudentDTO dto) {
        if (dto.getFirstname().isEmpty() || dto.getLastname().isEmpty() || dto.getPhone().isEmpty()) {
            return "Error: Firstname or Lastname or Phone can't be empty!";
        }
        dto.setUuid(UUID.randomUUID().toString());
        dto.setAdded_date(LocalDateTime.now());
        if (studentRepository.createStudent(dto) > 0) {
            return "Successfully created";
        } else return "Error into creation!";
    }

    public List<StudentDTO> getListStudent() {
        return studentRepository.getListStudent();
    }

    public StudentDTO getStudentByUuid(String uuid) {
        for (StudentDTO dto : getListStudent()) {
            if (dto.getUuid().equals(uuid)) {
                return dto;
            }
        }
        return null;
    }

    public Boolean updateStudent(String uuid, StudentDTO dto) {
        if (dto.getFirstname().isEmpty() || dto.getLastname().isEmpty() || dto.getPhone().isEmpty()) {
            return false;
        }
        return studentRepository.updateStudent(uuid, dto) > 0;
    }

    public Boolean deleteStudent(String uuid) {
        return studentRepository.deleteStudent(uuid) > 0;
    }
}
