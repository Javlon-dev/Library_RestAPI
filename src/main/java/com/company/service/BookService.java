package com.company.service;


import com.company.controller.BookController;
import com.company.dto.BookDTO;
import com.company.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public String createBook(BookDTO dto) {
        if (dto.getTitle().isEmpty() || dto.getAuthor().isEmpty()) {
            return "Error: Author or Title can't be empty!";
        }
        dto.setUuid(UUID.randomUUID().toString());
        dto.setAdded_date(LocalDateTime.now());
        if (bookRepository.createBook(dto) > 0) {
            return "Successfully created";
        } else return "Error into creation!";
    }

    public List<BookDTO> getListBook() {
        return bookRepository.getListBook();
    }

    public BookDTO getBookByUuid(String uuid) {
        for (BookDTO dto : getListBook()) {
            if (dto.getUuid().equals(uuid)) {
                return dto;
            }
        }
        return null;
    }

    public Boolean updateBook(String uuid, BookDTO dto) {
        if (dto.getTitle().isEmpty() || dto.getAuthor().isEmpty()) {
            return false;
        }
        return bookRepository.updateBook(uuid, dto) > 0;
    }

    public Boolean deleteBook(String uuid) {
        return bookRepository.deleteBook(uuid) > 0;
    }
}
