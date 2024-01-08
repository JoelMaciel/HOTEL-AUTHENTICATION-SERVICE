package com.joelmaciel.authservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends Exception {


}
