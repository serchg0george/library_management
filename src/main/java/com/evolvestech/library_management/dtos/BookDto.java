package com.evolvestech.library_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String publicationDate;

    @NotBlank
    private String genre;

    @NotBlank
    @Length(max = 13)
    private String isbn;
}
