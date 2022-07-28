package com.example.cinema.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CinemaRoomControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("Missing query parameter password!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected ResponseEntity<Object> handleWrongPasswordException() {
        CustomErrorMessage body = new CustomErrorMessage("The password value is wrong!");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SeatAlreadyPurchasedException.class)
    protected ResponseEntity<Object> handleSeatAlreadyPurchasedException() {
        CustomErrorMessage body = new CustomErrorMessage("The seat has been already purchased!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    protected ResponseEntity<Object> handleWrongTokenException() {
        CustomErrorMessage body = new CustomErrorMessage("The token key or value is wrong!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
