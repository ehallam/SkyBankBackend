package com.sky.SkyBankBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no payee found with that data")
public class PayeeNotFoundException extends RuntimeException{
}