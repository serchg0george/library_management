package com.evolvestech.library_management.controllers;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        bookDto = new BookDto(1L, "Test Title", "Test Author", "2023-01-01", "Fiction", "1234567890");
    }

    @Test
    void testAddBook() throws Exception {
        when(bookService.createBook(any(BookDto.class))).thenReturn(bookDto);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(bookDto));

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDto);

        mockMvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookByIdNotFound() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDto);
        when(bookService.updateBook(any(BookDto.class), eq(1L))).thenReturn(bookDto);

        mockMvc.perform(put("/api/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBookNotFound() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDto);
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/v1/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBookNotFound() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/books/1"))
                .andExpect(status().isNotFound());
    }
}
