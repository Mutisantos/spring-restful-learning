package com.kanionland.rest.webservices.restfulwebservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kanionland.rest.webservices.restfulwebservices.beans.HelloWorldBean;

//This annotation tells SpringMVC that this controller will handle REST requests
@RestController
public class HelloWorldController {
   // Tell the framework this method will be called with a HTTP Request mapping: path + method
   // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
   @GetMapping(path = "/hello-world") // => annotations with the method within
   public String helloWorld() {
      return "Hello World";
   }

   @GetMapping(path = "/hello-world-bean")
   public HelloWorldBean helloWorldBean() {
      return new HelloWorldBean("Hello World");
   }

   @GetMapping(path = "/hello-world-bean/path-variable/{name}") // Using a Path variable, that can be retrieved from the
                                                                // method using the PathVariable annotation
   public HelloWorldBean helloWorldBean(@PathVariable final String name) {
      return new HelloWorldBean(String.format("Hello World,%s", name));
   }


}
