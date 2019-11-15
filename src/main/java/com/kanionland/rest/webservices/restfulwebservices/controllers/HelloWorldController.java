package com.kanionland.rest.webservices.restfulwebservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//This annotation tells SpringMVC that this controller will handle REST requests
@RestController
public class HelloWorldController {
   // Tell the framework this method will be called with a HTTP Request mapping: path + method
   // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
   @GetMapping(path = "/hello-world") // => annotations with the method within
   public String helloWorld() {
      return "Hello World";
   }

}
