/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Veljko
 */
public class ApiValidationException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
    private final List<String> details;

    public ApiValidationException(final String message, final HttpStatus httpStatus, final ZonedDateTime zonedDateTime, final List<String> details) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public List<String> getDetails() {
        return details;
    }
}
