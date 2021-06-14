package com.ftn.uns.ac.rs.theperfectmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@ConfigurationPropertiesScan
public class ThePerfectMealApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThePerfectMealApplication.class, args);
	}

}
