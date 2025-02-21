package com.evolvestech.library_management.dtos.auth;

import jakarta.validation.constraints.Email;


public record AuthenticationRequestDto(@Email String email,
                                       String password) {

}
