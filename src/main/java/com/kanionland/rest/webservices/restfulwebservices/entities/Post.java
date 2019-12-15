package com.kanionland.rest.webservices.restfulwebservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Una clase de prueba llamada User")
@Entity
public class Post {
   @Setter
   @Id
   @GeneratedValue
   private Integer id;
   // If the user object has @Valid annotation, it will validate the Size and the Date to be in the past
   // Also, when the validation fails, there can be sent a customized message
   @Size(min = 2, message = "Name should have at least 2 characters")
   @ApiModelProperty(notes = "Name should have at least 2 characters. Add a period if it's those name with only one letter.")
   private String description;
   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore // This can break looped reference between to entities
   @Setter
   private User userCreator;

}
