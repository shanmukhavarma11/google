package com.example.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.google","com.example.googlecontroller"})
public class GoogleandformjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleandformjwtApplication.class, args);
	}

}
