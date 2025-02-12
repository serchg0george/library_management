package com.evolvestech.library_management.controllers;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.searches.BookSearchRequest;
import com.evolvestech.library_management.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/books")
@Validated
public class BookController {

    private final BookService service;

    @PostMapping("/search")
    public ResponseEntity<List<BookDto>> searchAnimal(@RequestBody BookSearchRequest request) {
        List<BookDto> bookDtos = service.findBookByRequest(request);
        return ResponseEntity.ok(bookDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto book) {
        service.validatePublicationDate(book.publicationDate());
        service.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getBookById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto book) {
        service.validatePublicationDate(book.publicationDate());
        service.updateBook(book, id);
        return new ResponseEntity<>("Book successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return new ResponseEntity<>("Book successfully deleted!", HttpStatus.OK);
    }
}
