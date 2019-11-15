package com.kanionland.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kanionland.rest.webservices.restfulwebservices.entities.User;
import com.kanionland.rest.webservices.restfulwebservices.entities.UserDAOService;
import com.kanionland.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;

@RestController
public class UserResourceController {

   // Autowiring a Spring-managed component injects the dependency from the component to this class.
   @Autowired
   private UserDAOService userDAOService;

   @RequestMapping(method = RequestMethod.GET, path = "/users")
   public List<User> retriveAllUsers() {
      return userDAOService.findAll();
   }

   @RequestMapping(method = RequestMethod.GET, path = "/users/{uid}")
   public User retriveUser(@PathVariable final int uid) throws UserNotFoundException {
      final User foundUser = userDAOService.findOne(uid);
      if (Objects.isNull(foundUser)) {
         // Throwing a unhandled exception upwards will end-up throwing Server Error, which it's not completely true
         // throw new Exception("");
         // Unless the exception has a certain ResponseStatus annotation
         throw new UserNotFoundException("User not found for ID-" + uid);
      }
      return foundUser;
   }

   @RequestMapping(method = RequestMethod.POST, path = "/users")
   // Whatever is being passed in the Body of the Request, will be mapped into this parameter and object type
   public ResponseEntity<Object> createUser(@RequestBody final User user) {
      final User createdUser = userDAOService.save(user);
      // Get the current URI of the request and build it with extra fields using the Path Method
      final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(createdUser.getUid()).toUri();
      // Give a response with the expected URI for the newly created resource
      return ResponseEntity.created(location).build();
   }

}
