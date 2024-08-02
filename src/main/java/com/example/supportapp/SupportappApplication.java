package com.example.supportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.supportapp.repository")
@ComponentScan(basePackages = "com.example.supportapp")
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
				System.out.println("CORS configuration applied");
			}
		};
	}
}
