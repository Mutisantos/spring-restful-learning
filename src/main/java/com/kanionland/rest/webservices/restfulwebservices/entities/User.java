package com.kanionland.rest.webservices.restfulwebservices.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Una clase de prueba llamada User")
@Entity
public class User {
   @Id
   @GeneratedValue
   private Integer uid;
   // If the user object has @Valid annotation, it will validate the Size and the Date to be in the past
   // Also, when the validation fails, there can be sent a customized message
   @Size(min = 2, message = "Name should have at least 2 characters")
   @ApiModelProperty(notes = "Name should have at least 2 characters. Add a period if it's those name with only one letter.")
   private String name;
   @Past
   @ApiModelProperty(notes = "Dates can't later than current date")
   private Date birthDate;
   @OneToMany(mappedBy = "userCreator")
   private List<Post> userPosts;
}
