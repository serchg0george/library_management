package com.evolvestech.library_management.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionResponse(HttpStatus status, String message,

                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
                                LocalDateTime dateTime) {
}
