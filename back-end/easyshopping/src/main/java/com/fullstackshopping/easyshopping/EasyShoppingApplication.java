package com.fullstackshopping.easyshopping;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyShoppingApplication {

	public static void main(String[] args) {

		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.configure().load();

		// Get the environment variables
		String databaseUrl = dotenv.get("DATABASE_URL");
		String databaseUsername = dotenv.get("DATABASE_USERNAME");
		String databasePassword = dotenv.get("DATABASE_PASSWORD");

		// Set system properties to environment variables
		System.setProperty("spring.datasource.url", databaseUrl);
		System.setProperty("spring.datasource.username", databaseUsername);
		System.setProperty("spring.datasource.password", databasePassword);

		// Run spring application
		SpringApplication.run(EasyShoppingApplication.class, args);
	}

}
