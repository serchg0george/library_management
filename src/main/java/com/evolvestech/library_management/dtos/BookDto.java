package com.evolvestech.library_management.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    @Length(max = 10)
    private String publicationDate;

    @NotBlank
    private String genre;

    @NotBlank
    @Length(max = 13)
    private String isbn;
}
