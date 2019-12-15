package com.kanionland.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

//Since it's a springBoot application, the component scan will only happen for all the classes within the package of this file
//This also includes files in sub-folders of the current folder

@SpringBootApplication
public class RestfulWebServicesApplication {

   public static void main(final String[] args) {
      SpringApplication.run(RestfulWebServicesApplication.class, args);
   }

   @Bean
   public LocaleResolver localeResolver() {
      // Solves the locale by session information, not by headers
      // final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
      AcceptHeaderLocaleResolver acceptLocaleResolver = new AcceptHeaderLocaleResolver();
      acceptLocaleResolver.setDefaultLocale(Locale.US);
      return acceptLocaleResolver;
   }

   // This bean has been replaced by a spring configuration: spring.messages.baseline
   // @Bean
   // public ResourceBundleMessageSource messageSource() {
   // final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
   // messageSource.setBasename("messages");
   // return messageSource;
   // }

}
