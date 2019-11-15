package com.kanionland.rest.webservices.restfulwebservices.entities;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class User {
   @Setter
   private Integer uid;
   private final String name;
   private final Date birthDate;

}
