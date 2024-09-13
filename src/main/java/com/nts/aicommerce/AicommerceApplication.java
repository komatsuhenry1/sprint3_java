package com.nts.aicommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AicommerceApplication {

	@GetMapping("/")
	private ResponseEntity<String> info(){
		return ResponseEntity.ok("Working");
	}

	public static void main(String[] args) {
		SpringApplication.run(AicommerceApplication.class, args);
	}
}