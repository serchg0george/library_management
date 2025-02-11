package com.evolvestech.library_management.services;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.searches.BookSearchRequest;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto book);

    BookDto getBookById(Long bookId);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto book, Long id);

    void deleteBook(Long bookId);

    List<BookDto> findBookByRequest(final BookSearchRequest request);

    void validatePublicationDate(String publicationDate);
}
