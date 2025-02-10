package com.evolvestech.library_management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_books")
public class Book extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publication_date", length = 10)
    private String publicationDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "isbn", nullable = false, unique = true, length = 13)
    private String isbn;
}
