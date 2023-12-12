package com.api.api_vinos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
public class ApiVinosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVinosApplication.class, args);
	}

}
