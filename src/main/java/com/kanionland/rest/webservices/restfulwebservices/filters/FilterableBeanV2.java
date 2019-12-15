package com.kanionland.rest.webservices.restfulwebservices.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FilterableBeanV2 {
   private String character;
   private String number;
   // @JsonIgnore //Is being used as a Static Filtering
   private int cost;
   private int surplus;

}
