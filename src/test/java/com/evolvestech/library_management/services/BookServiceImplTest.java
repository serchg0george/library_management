package com.evolvestech.library_management.services;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.entities.Book;
import com.evolvestech.library_management.mappers.BookMapperImpl;
import com.evolvestech.library_management.repositories.BookRepository;
import com.evolvestech.library_management.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository repository;

    @Mock
    private BookMapperImpl mapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        bookDto = new BookDto(1L, "Test Book", "Test Author", "2022", "Fiction", "1234567890");
    }

    @Test
    void createBook_ShouldReturnBookDto() {
        when(mapper.mapDtoToEntity(any())).thenReturn(book);
        when(repository.save(any())).thenReturn(book);
        when(mapper.mapEntityToDto(any())).thenReturn(bookDto);

        BookDto result = bookService.createBook(bookDto);

        assertNotNull(result);
        assertEquals(bookDto, result);
        verify(repository, times(1)).save(any());
    }

    @Test
    void getBookById_ShouldReturnBookDto_WhenBookExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(mapper.mapEntityToDto(book)).thenReturn(bookDto);

        BookDto result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void getBookById_ShouldReturnNull_WhenBookDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        BookDto result = bookService.getBookById(1L);

        assertNull(result);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBookDtos() {
        when(repository.findAll()).thenReturn(List.of(book));
        when(mapper.mapEntityToDto(book)).thenReturn(bookDto);

        List<BookDto> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals(bookDto, result.get(0));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBookDto() {
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(mapper.mapDtoToEntity(any())).thenReturn(book);
        when(repository.save(any())).thenReturn(book);
        when(mapper.mapEntityToDto(any())).thenReturn(bookDto);

        BookDto result = bookService.updateBook(bookDto, 1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void updateBook_ShouldThrowException_WhenBookNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(bookDto, 1L));
    }

    @Test
    void deleteBook_ShouldCallRepositoryDeleteById() {
        doNothing().when(repository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(repository, times(1)).deleteById(1L);
    }

}
