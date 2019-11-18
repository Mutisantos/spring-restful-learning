package com.kanionland.rest.webservices.restfulwebservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// A class designed to provide a Response for all possible exceptions happening during other calls
@RestController
// For exception handling, initBinder, and more
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
   // The annotation will tell that the controller must handle all exceptions of this type and its extensions.
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) throws Exception {
      final ExceptionResponse er = new ExceptionResponse(new Date(), ex.getClass().toString(),
            request.getDescription(false));
      return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<Object> handleUserNotFoundException(final Exception ex, final WebRequest request)
         throws Exception {
      final ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(),
            request.getDescription(false));
      // Instead of throwing a error that would be caught as a default spring page, it will send a HTTP Response with
      // the error
      return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
   }

   // When an object with @Valid annotation fails the validation, this method is executed
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      final ExceptionResponse er = new ExceptionResponse(new Date(), "Validation Failed",
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
      return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
   }

}
