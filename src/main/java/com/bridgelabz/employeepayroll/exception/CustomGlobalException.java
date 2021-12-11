package com.bridgelabz.employeepayroll.exception;

import com.bridgelabz.employeepayroll.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalException extends ResponseEntityExceptionHandler {

    /**
     * Purpose : error handle for @Valid.
     * @param ex : Exception to be thrown when validation on an argument annotated with @Valid fail
     * @param headers : Pass additional information between the clients and the server through the
     *                 request and response header.
     * @param status : Help identify the cause of the problem when a web page or other resource
     *                 does not load properly.
     * @param request : Help identify the cause of the problem when a web page or other resource
     *                  does not load properly.
     * @return ResponseEntity : Contains message, data & status.
     */
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * Purpose : To handle when given id is not found in database.
     * @param ex: Used when given resource is not present.
     * @return ResponseEntity : Contains details about exception;string message and http status
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("Invalid ID");
        return new ResponseEntity("Given id is Not Found", HttpStatus.BAD_REQUEST);
    }

}