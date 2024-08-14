package com.sky.SkyBankBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no customer found with that data")
public class CustomerNotFoundException extends RuntimeException{
}