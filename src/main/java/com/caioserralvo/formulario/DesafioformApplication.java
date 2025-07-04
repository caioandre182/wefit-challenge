package com.caioserralvo.formulario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioformApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioformApplication.class, args);
	}

}
