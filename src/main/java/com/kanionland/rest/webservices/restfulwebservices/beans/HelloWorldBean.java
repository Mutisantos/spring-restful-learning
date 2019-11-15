package com.kanionland.rest.webservices.restfulwebservices.beans;

import lombok.Getter;
import lombok.Setter;


@Setter
// This bean is being returned as a whole object in JSON Format. It's going to show only those methods with a Getter
// implemented
public class HelloWorldBean {

   @Getter
   private final String message;
   // @Getter
   private final String issue;

   public HelloWorldBean(final String message) {
      this.message = message;
      this.issue = "0";
   }
}
