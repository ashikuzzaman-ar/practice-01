package com.studevs.practice.practice01.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
		  .title("Practice project")
		  .version("0.0.1-SNAPSHOT")
		  .description("Practice project for demonstration")
		  .contact(new Contact()
			.name("Md. Ashikuzzaman")
			.email("ashikuzzaman.ar@gmail.com")
			.url("https://github.com/ashikuzzaman-ar/"))
		  .license(new License()
			.url("https://github.com/ashikuzzaman-ar/practice-01/blob/main/README.md")));
	}
}