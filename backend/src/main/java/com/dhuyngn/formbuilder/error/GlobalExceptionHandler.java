package com.dhuyngn.formbuilder.error;

import jakarta.servlet.http.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.io.*;
import java.time.*;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status,
                                                                WebRequest request) {
    Map<String, Object> body = new HashMap<>();

    body.put("status", status.value());
    body.put("timestamp", LocalDateTime.now());

    String errors = ex.getBindingResult()
        .getFieldErrors()
        .getFirst()
        .getDefaultMessage();
    body.put("errors", errors);
    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(FormAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<Object>
  handleFormAlreadyExistsException(FormAlreadyExistsException ex)
      throws IOException {
    Map<String, Object> body = new HashMap<>();

    body.put("status", HttpStatus.CONFLICT.value());
    body.put("timestamp", LocalDateTime.now());

    String error = ex.getMessage();

    body.put("errors", error);

    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(FormNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object>
  handleFormNotFoundException(FormNotFoundException ex)
      throws IOException {
    Map<String, Object> body = new HashMap<>();

    body.put("status", HttpStatus.CONFLICT.value());
    body.put("timestamp", LocalDateTime.now());

    String error = ex.getMessage();
    body.put("errors", error);

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public void constraintViolationException(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }

}
