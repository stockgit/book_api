package com.accend.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
/**
 * Global Exception Handler
 * @author Satib
 * @version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(BookException ee, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ee.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ErrorDetails> DataNotFoundHandler(DataNotFound cnf, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(cnf.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException noHandlerFoundException, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(noHandlerFoundException.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> MethodArgumentExceptionHandler(MethodArgumentNotValidException exception) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage("Validation error");
        err.setDetails(exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> illegealArgumentHandler(IllegalArgumentException cnf, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(cnf.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception exception, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(exception.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }
}
