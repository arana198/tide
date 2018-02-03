package com.tide.common.exception.handler;

import com.tide.common.error.ErrorResource;
import com.tide.common.error.FieldErrorResource;
import com.tide.common.exception.BadRequestException;
import com.tide.common.exception.InternalServerException;
import com.tide.common.exception.ResourceAlreadyExistsException;
import com.tide.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@ControllerAdvice
class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ire, final WebRequest request) {
        final ErrorResource error = new ErrorResource(HttpStatus.BAD_REQUEST.value(), "InvalidRequest", ire.getMessage());
        final List<FieldErrorResource> fieldErrorResources = Optional.ofNullable(ire.getErrors())
                .map(Errors::getFieldErrors)
                .map(List::stream)
                .orElse(Stream.empty())
                .map(FieldErrorResource::of)
                .collect(Collectors.toList());

        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ire, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final ResourceNotFoundException ex, final WebRequest request) {
        ErrorResource error = new ErrorResource(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleConflictException(final ResourceAlreadyExistsException ex, final WebRequest request) {
        ErrorResource error = new ErrorResource(HttpStatus.CONFLICT.value(), "Conflict Error", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, error, headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({Exception.class, InternalServerException.class})
    public ResponseEntity<Object> handleUncaughtException(final Exception ex, final WebRequest request) {
        log.error("Internal Server Error", ex);
        ErrorResource error = new ErrorResource(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown Error", ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}