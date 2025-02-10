package com.evolvestech.library_management.controllers;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/books")
@Validated
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto book) {
        service.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        if (service.getBookById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getBookById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto book) {
        if (service.getBookById(id) == null) {
            return new ResponseEntity<>("Book with provided id " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        service.updateBook(book, id);
        return new ResponseEntity<>("Book successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (service.getBookById(id) == null) {
            return new ResponseEntity<>("Book with provided id " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        service.deleteBook(id);
        return new ResponseEntity<>("Book successfully deleted!", HttpStatus.OK);
    }
}
