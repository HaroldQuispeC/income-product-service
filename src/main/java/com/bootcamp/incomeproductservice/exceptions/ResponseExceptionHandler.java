package com.bootcamp.incomeproductservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(ModelException.class)
    public  final ResponseEntity<ExceptionResponse> handleModelException(ModelException model, WebRequest web){
        ExceptionResponse exception = new ExceptionResponse(LocalDateTime.now(), model.getMessage(), web.getDescription(false));

        logger.error(model.getMessage() + " - " + Arrays.toString(model.getStackTrace()));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }


}