package com.wasu.springboot.integration;

import com.wasu.springboot.integration.common.config.InitReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootModelApplication {

	public static void main(String[] args) {
		SpringApplication application=new SpringApplication(SpringBootApplication.class);
		application.addListeners(new InitReadyEvent());
		application.run(args);
//		SpringApplication.run(SpringbootModelApplication.class, args);
	}

}

