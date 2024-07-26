package com.example.supportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SupportappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportappApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowCredentials(false)
					.allowedOrigins("*")
					.allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD");
					// .allowedOrigins("http://localhost:9000");
			}
		};
	}
}