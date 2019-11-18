package com.kanionland.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//Since it's a springBoot application, the component scan will only happen for all the classes within the package of this file
//This also includes files in sub-folders of the current folder

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

   @Bean
   public LocaleResolver localeResolver() {
      final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
      localeResolver.setDefaultLocale(Locale.US);
      return localeResolver;
   }

   @Bean
   public ResourceBundleMessageSource messageSource() {
      final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasename("messages");
      return messageSource;
   }

}
