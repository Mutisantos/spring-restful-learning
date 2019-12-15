package com.kanionland.rest.webservices.restfulwebservices.swagger;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigBean {

   private static final Contact DEFAULT_CONTACT = new Contact("Muti", "mutisantos.itch.io", "mutisantos@hotmail.com");
   private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("RESTful Api A+ Title",
         "A RESTful API for learning processes in Spring-powered Microservices", "1.0.0", "https://www.lipsum.com/",
         DEFAULT_CONTACT, "GNU-LP License", "omaenohahanokai.com");
   private static final Set<String> DEFAULT_SUPPORTED_CONTENT = Stream.of("application/json", "application/xml")
         .collect(Collectors.toSet());

   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_SUPPORTED_CONTENT);
   }

}
