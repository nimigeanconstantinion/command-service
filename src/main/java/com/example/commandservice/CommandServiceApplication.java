package com.example.commandservice;

import com.example.commandservice.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(CorsProperties.class)

public class CommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandServiceApplication.class, args);
	}

}
