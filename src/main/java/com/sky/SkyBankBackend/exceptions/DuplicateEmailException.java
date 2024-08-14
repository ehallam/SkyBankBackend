package com.sky.SkyBankBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Email already exists in the database")
public class DuplicateEmailException extends RuntimeException{
}