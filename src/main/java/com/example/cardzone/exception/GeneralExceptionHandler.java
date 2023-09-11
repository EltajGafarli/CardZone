package com.example.cardzone.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldError>> handleValidationErrors(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardNotFoundErrors(CardNotFoundException exception) {
        ErrorResponse response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughBalanceException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughBalanceException(NotEnoughBalanceException exception) {
        ErrorResponse response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> sqlError(EmptyResultDataAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
