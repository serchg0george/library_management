package com.evolvestech.library_management.dtos.auth;

import jakarta.validation.constraints.Email;


public record RegisterRequestDto(String firstName,
                                 String lastName,
                                 @Email String email,
                                 String password) {

}
