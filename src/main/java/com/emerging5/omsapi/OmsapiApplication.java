package com.emerging5.omsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class OmsapiApplication {

	private static final Logger log = LoggerFactory.getLogger(OmsapiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OmsapiApplication.class, args);
	}

	@GetMapping
	public String ServerRoot(){
		return "OMSAPI Version 1.1.0";
	}
}
