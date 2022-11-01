package com.projects.todoapp.exceptions.handler;

import com.projects.todoapp.exceptions.TaskNotFoundException;
import com.projects.todoapp.exceptions.customError.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> handleGlobalException(Exception exception) {
        return resolveError(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<AppError> handleTaskNotFoundException(TaskNotFoundException exception) {
        return resolveError(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AppError> resourceNotFoundExceptionHandler(ConstraintViolationException exception,
                                                                     WebRequest request) {

        AppError error = new AppError(HttpStatus.BAD_REQUEST, exception);
        error.setMessage("Validation Error");
        error.addValidationErrors(exception.getConstraintViolations());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private ResponseEntity<AppError> resolveError(HttpStatus status, Exception exception) {
        AppError error = new AppError(status, exception, exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
