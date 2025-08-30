package com.inner_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InnerCodeApplication {
    // ./mvnw spring-boot:run
	public static void main(String[] args) {
		SpringApplication.run(InnerCodeApplication.class, args);

	}
}
