package com.sky.SkyBankBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class SkyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyBankBackendApplication.class, args);
	}
}
