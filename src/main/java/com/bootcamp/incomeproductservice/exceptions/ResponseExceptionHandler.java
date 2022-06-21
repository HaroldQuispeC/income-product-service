package com.bootcamp.incomeproductservice.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * handleModelException method.
   *
   * @param model ModelException
   * @param web   WebRequest
   * @return
   */
  @ExceptionHandler(ModelException.class)
  public final ResponseEntity<ExceptionResponse> handleModelException(
          ModelException model, WebRequest web) {

    ExceptionResponse exception = new ExceptionResponse(
            LocalDateTime.now(),
            model.getMessage(),
            web.getDescription(false));

    logger.error(String.format("%s - %s",
            model.getMessage(),
            Arrays.toString(model.getStackTrace())));

    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

  /**
   * handleFallbackException method.
   * @param model ModelException
   * @param web Webrequest
   * @return ResponseEntity ExceptionResponse
   */
  @ExceptionHandler(FallbackException.class)
  public final ResponseEntity<ExceptionResponse> handleFallbackException(
          FallbackException model, WebRequest web) {

    ExceptionResponse exception = new ExceptionResponse(
            LocalDateTime.now(),
            model.getMessage(),
            web.getDescription(false));

    logger.error(String.format("%s - %s",
            model.getMessage(),
            Arrays.toString(model.getStackTrace())));

    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

  /**
   * handleExceptionInternal method overriding.
   * @param ex Exception
   * @param body Body
   * @param headers Headers
   * @param status Status
   * @param request Request
   * @return
   */
  @Override
  public final ResponseEntity<Object> handleExceptionInternal(
          Exception ex, @Nullable Object body, HttpHeaders headers,
          HttpStatus status, WebRequest request) {

    String bodyMessage = (body == null ? "" : body.toString());

    ExceptionResponse exception = new ExceptionResponse(
            LocalDateTime.now(),
            ex.getMessage() + " - " + bodyMessage,
            request.getDescription(false));

    logger.error(String.format("%s - %s",
            ex.getMessage(),
            request.getDescription(true)));

    return new ResponseEntity<>(exception, status);
  }
}