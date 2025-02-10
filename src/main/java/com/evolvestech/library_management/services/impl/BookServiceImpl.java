package com.evolvestech.library_management.services.impl;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.entities.Book;
import com.evolvestech.library_management.mappers.BookMapperImpl;
import com.evolvestech.library_management.repositories.BookRepository;
import com.evolvestech.library_management.services.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapperImpl mapper;

    @Override
    @Transactional
    public BookDto createBook(BookDto book) {
        Book bookEntity = mapper.mapDtoToEntity(book);
        return mapper.mapEntityToDto(repository.save(bookEntity));
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return repository.findById(bookId).map(mapper::mapEntityToDto).orElse(null);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream().map(mapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto book, Long id) {
        Book bookEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        Book updatedBook = mapper.mapDtoToEntity(book);
        updatedBook.setId(bookEntity.getId());
        return mapper.mapEntityToDto(repository.save(updatedBook));
    }

    @Override
    public void deleteBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
