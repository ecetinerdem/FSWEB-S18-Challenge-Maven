package com.workintech.fswebs18challengemaven.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<CardErrorResponse> handleException(CardException exception) {
        CardErrorResponse burgerErrorResponse = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(burgerErrorResponse, exception.getHttpStatus());
    }
    @ExceptionHandler
    private ResponseEntity<CardErrorResponse> handleException(Exception exception) {
        CardErrorResponse burgerErrorResponse = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(burgerErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
