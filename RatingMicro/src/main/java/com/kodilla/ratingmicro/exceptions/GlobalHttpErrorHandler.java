package com.kodilla.ratingmicro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Object> ratingNotFoundHandler(RatingNotFoundException e) {
        return new ResponseEntity<>("Rating is hard to find", HttpStatus.BAD_REQUEST);
    }
}
