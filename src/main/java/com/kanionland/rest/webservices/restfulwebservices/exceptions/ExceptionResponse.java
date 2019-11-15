package com.kanionland.rest.webservices.restfulwebservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Builder;
import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Builder
public class ExceptionResponse {

   private final Date timestamp;
   private final String message;
   private final String details;

}
