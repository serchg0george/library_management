package com.evolvestech.library_management.services.impl;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.entities.Book;
import com.evolvestech.library_management.exceptions.BookNotFoundException;
import com.evolvestech.library_management.mappers.BookMapperImpl;
import com.evolvestech.library_management.repositories.BookRepository;
import com.evolvestech.library_management.searches.BookSearchRequest;
import com.evolvestech.library_management.services.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapperImpl mapper;
    private final EntityManager entityManager;
    private static final String AUTHOR = "author";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    @Transactional
    public BookDto createBook(BookDto book) {
        Book bookEntity = mapper.mapDtoToEntity(book);
        return mapper.mapEntityToDto(repository.save(bookEntity));
    }

    @Override
    public BookDto getBookById(Long id) {
        return repository.findById(id).map(mapper::mapEntityToDto).orElse(null);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream().map(mapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto book, Long id) {
        Book bookEntity = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Book updatedBook = mapper.mapDtoToEntity(book);
        updatedBook.setId(bookEntity.getId());
        return mapper.mapEntityToDto(repository.save(updatedBook));
    }

    @Override
    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<BookDto> findBookByRequest(final BookSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Book> root = criteriaQuery.from(Book.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), query);
            Predicate authorPredicate = criteriaBuilder.like(root.get(AUTHOR), query);
            Predicate publicationDatePredicate = criteriaBuilder.like(root.get("publicationDate"), query);
            Predicate genrePredicate = criteriaBuilder.like(root.get("genre"), query);
            Predicate isbnPredicate = criteriaBuilder.like(root.get("isbn"), query);

            predicates.add(criteriaBuilder.or(titlePredicate, authorPredicate, publicationDatePredicate, genrePredicate, isbnPredicate));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(AUTHOR)));

        TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream().map(mapper::mapEntityToDto).toList();
    }

    @Override
    public void validatePublicationDate(String publicationDate) {
        if (publicationDate == null || publicationDate.isBlank()) {
            throw new IllegalArgumentException("Publication date cannot be null or blank");
        }

        try {
            LocalDate date = LocalDate.parse(publicationDate, DATE_FORMATTER);
            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Invalid date: Date cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date should be in format YYYY-MM-DD");
        }
    }
}
