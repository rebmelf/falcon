package io.falcon.falcontest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.falcon.falcontest.repository.service.ServiceException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestAPIExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorMessage> serviceExceptionHandler(final ServiceException e) {
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), BAD_REQUEST);
  }
}
