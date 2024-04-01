/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Veljko
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            details.add(error.getField() + ":" + error.getDefaultMessage());
        });
        final String bodyOfResponse = "Error during validation of object " + ex.getBindingResult().getObjectName();
        final ApiValidationException exception = new ApiValidationException(bodyOfResponse, HttpStatus.BAD_REQUEST, ZonedDateTime.now(), details);
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "HttpRequest with that parametars is not allowed ";
        final ApiException exception = new ApiException(bodyOfResponse, HttpStatus.METHOD_NOT_ALLOWED, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {SelectionNotFoundException.class})
    protected ResponseEntity<Object> handleSelectionNotFound(final SelectionNotFoundException ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolation(final DataIntegrityViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "Data integrity violation exception";
        final ApiException exception = new ApiException(bodyOfResponse + ":" + ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultDataAccess(final EmptyResultDataAccessException ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(final UserNotFoundException ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WrongCredentials.class})
    protected ResponseEntity<Object> handleWrongCredentials(final WrongCredentials ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    protected ResponseEntity<Object> handleDatabaseError(final CannotCreateTransactionException ex, final WebRequest request) {
        final String bodyOfResponse = "Server side error.";
        final ApiException exception = new ApiException(bodyOfResponse, HttpStatus.SERVICE_UNAVAILABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {MatchNotFound.class})
    protected ResponseEntity<Object> handleMatchNotFound(final MatchNotFound ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Wrong JSON format. JSON cannot be parsed.";
        final ApiException exception = new ApiException(bodyOfResponse, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BusyUsernameException.class})
    protected ResponseEntity<Object> handleBusyUsernameException(final BusyUsernameException ex, final WebRequest request) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE);
    }

}
