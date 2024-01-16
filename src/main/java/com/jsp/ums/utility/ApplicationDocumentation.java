package com.jsp.ums.utility;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
  Info info() {
	  return new Info().title("User Management System API").version("1.0V").description("User Management System is a restful api built using"+" springboot and mysql database")
			  .contact(contact());		 
  }
   Contact contact() {
      return new Contact().name("Rohit").email("abc@gmail.com").url("https://github.com/rohit0508S").email("abc");
              
  }
  @Bean
  OpenAPI openAPI() {
	  return new OpenAPI().info(info());
  }
}
