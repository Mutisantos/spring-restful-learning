package com.kanionland.rest.webservices.restfulwebservices.filters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
   @GetMapping(path = "/filtering")
   public MappingJacksonValue requestFilterBean() {
      FilterableBean filteredResponse = new FilterableBean("A", "1", 200);
      MappingJacksonValue mapping = new MappingJacksonValue(filteredResponse);
      SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter
            .filterOutAllExcept(Stream.of("character", "number").collect(Collectors.toSet()));
      FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterableBean", simpleFilter);
      mapping.setFilters(filterProvider);
      return mapping;
   }

   @GetMapping(value = "/filtering-list/param", params = "version=1")
   public List<FilterableBean> requestFilterBeanList() {
      return Stream.of(new FilterableBean("A", "1", 200), new FilterableBean("B", "2", 300))
            .collect(Collectors.toList());
   }

   @GetMapping(value = "/filtering-list/param", params = "version=2")
   public List<FilterableBeanV2> requestFilterBeanListV2() {
      return Stream.of(new FilterableBeanV2("A", "1", 200, 10), new FilterableBeanV2("B", "2", 300, -1))
            .collect(Collectors.toList());
   }

   @GetMapping(value = "/filtering-list/header", headers = "X_API_VERSION=1")
   public List<FilterableBean> requestFilterBeanListByHeader() {
      return Stream.of(new FilterableBean("A", "1", 200), new FilterableBean("B", "2", 300))
            .collect(Collectors.toList());
   }

   @GetMapping(value = "/filtering-list/header", headers = "X_API_VERSION=2")
   public List<FilterableBeanV2> requestFilterBeanListV2ByHeader() {
      return Stream.of(new FilterableBeanV2("A", "1", 200, 10), new FilterableBeanV2("B", "2", 300, -1))
            .collect(Collectors.toList());
   }
}
