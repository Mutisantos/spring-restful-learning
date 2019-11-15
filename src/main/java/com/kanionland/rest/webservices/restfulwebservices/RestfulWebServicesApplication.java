package com.kanionland.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Since it's a springBoot application, the component scan will only happen for all the classes within the package of this file
//This also includes files in sub-folders of the current folder

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
