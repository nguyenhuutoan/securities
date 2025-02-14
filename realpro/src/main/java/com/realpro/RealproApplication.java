package com.realpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RealproApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealproApplication.class, args);
	}
	/**
	@Bean
	public SpringTemplateEngine templateEngine() {
		
		
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();

	    templateEngine.addDialect(new LayoutDialect());
	    return templateEngine;
	}**/

}
