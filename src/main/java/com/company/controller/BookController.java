package com.company.controller;

import com.company.dto.BookDTO;
import com.company.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public String createBook(@RequestBody BookDTO dto) {
        return bookService.createBook(dto);
    }

    @GetMapping("/list")
    public List<BookDTO> getListBook() {
        return bookService.getListBook();
    }

    @GetMapping("/get/{uuid}")
    public BookDTO getBookByUuid(@PathVariable("uuid") String uuid) {
       return bookService.getBookByUuid(uuid);
    }

    @PutMapping("/update/{uuid}")
    public Boolean updateBook(@PathVariable("uuid") String uuid, @RequestBody BookDTO dto) {
        return bookService.updateBook(uuid, dto);
    }

    @DeleteMapping("/delete/{uuid}")
    public Boolean deleteBook(@PathVariable("uuid") String uuid) {
        return bookService.deleteBook(uuid);
    }
}
