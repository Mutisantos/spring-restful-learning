package com.kanionland.rest.webservices.restfulwebservices.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
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

   @Autowired
   private MessageSource messageSource;

   // @RequestMapping(method = RequestMethod.GET, path = "/main-internationalized")
   // public String showMainContent(@RequestHeader(name = "Accept-Language", required = false) final Locale locale) {
   // return messageSource.getMessage("good.morning.message", null, locale);
   // }

   @RequestMapping(method = RequestMethod.GET, path = "/main-internationalized")
   public String showMainContent() {
      return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
   }

   @RequestMapping(method = RequestMethod.GET, path = "/users")
   public List<User> retriveAllUsers() {
      return userDAOService.findAll();
   }

   // Using HATEOAS Sprint 2.2.x+
   // @RequestMapping(method = RequestMethod.GET, path = "/users/{uid}")
   // public EntityModel<User> retriveUser(@PathVariable final int uid) throws UserNotFoundException {
   // final User foundUser = userDAOService.findOne(uid);
   // if (Objects.isNull(foundUser)) {
   // // Throwing a unhandled exception upwards will end-up throwing Server Error, which it's not completely true
   // // throw new Exception("");
   // // Unless the exception has a certain ResponseStatus annotation
   // throw new UserNotFoundException("User not found for ID-" + uid);
   // }
   // // Using a HATEOAS Resource, the return will handle both the Object and links related to the response if needed
   // final EntityModel<User> resource = new EntityModel<>(foundUser);
   // final WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
   // resource.add(linkTo.withRel("all-users"));
   // return resource;
   // }

   // Using HATEOAS Sprint 2.1.x+
   @RequestMapping(method = RequestMethod.GET, path = "/users/{uid}")
   public Resource<User> retriveUser(@PathVariable final int uid) throws UserNotFoundException {
      final User foundUser = userDAOService.findOne(uid);
      if (Objects.isNull(foundUser)) {
         // Throwing a unhandled exception upwards will end-up throwing Server Error, which it's not completely true
         // throw new Exception("");
         // Unless the exception has a certain ResponseStatus annotation
         throw new UserNotFoundException("User not found for ID-" + uid);
      }
      Resource<User> resource = new Resource<User>(foundUser);
      ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
      resource.add(linkTo.withRel("all-users"));
      return resource;
   }

   @RequestMapping(method = RequestMethod.DELETE, path = "/users/{uid}")
   public ResponseEntity<Object> deleteUser(@PathVariable final int uid) throws UserNotFoundException {
      if (!userDAOService.removeUser(uid)) {
         throw new UserNotFoundException("User not found for ID-" + uid);
      }
      return ResponseEntity.status(HttpStatus.OK).build();
   }

   @RequestMapping(method = RequestMethod.POST, path = "/users")
   // Whatever is being passed in the Body of the Request, will be mapped into this parameter and object type
   public ResponseEntity<Object> createUser(@Valid @RequestBody final User user) {
      final User createdUser = userDAOService.save(user);
      // Get the current URI of the request and build it with extra fields using the Path Method
      final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(createdUser.getUid()).toUri();
      // Give a response with the expected URI for the newly created resource
      return ResponseEntity.created(location).build();
   }

}
