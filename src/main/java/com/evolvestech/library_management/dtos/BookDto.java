package com.evolvestech.library_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record BookDto(Long id,
                      @NotBlank String title,

                      @NotBlank String author,

                      @NotBlank
                      @Length(max = 10)
                      @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Input date should be in format YYYY-MM-DD")
                      String publicationDate,

                      @NotBlank
                      String genre,

                      @NotBlank
                      @Length(max = 13)
                      @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "Input must be either 10 or 13 digits")
                      String isbn
) {
}
