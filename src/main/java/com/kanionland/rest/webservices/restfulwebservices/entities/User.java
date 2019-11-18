package com.kanionland.rest.webservices.restfulwebservices.entities;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class User {
   @Setter
   private Integer uid;
   // If the user object has @Valid annotation, it will validate the Size and the Date to be in the past
   // Also, when the validation fails, there can be sent a customized message
   @Size(min = 2, message = "Name should have at least 2 characters")
   private final String name;
   @Past
   private final Date birthDate;

}
