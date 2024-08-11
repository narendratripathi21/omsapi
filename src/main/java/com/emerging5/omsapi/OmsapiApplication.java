package com.emerging5.omsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class OmsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsapiApplication.class, args);
	}

	@GetMapping
	public String ServerRoot(){
		return "OMSAPI Version 1.1.0";
	}
}
