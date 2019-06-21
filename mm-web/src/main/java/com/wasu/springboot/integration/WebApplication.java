package com.wasu.springboot.integration;

import com.wasu.springboot.integration.common.config.InitReadyEvent;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication application=new SpringApplication(WebApplication.class);
		application.addListeners(new InitReadyEvent());
		application.run(args);
//		SpringApplication.run(SpringbootModelApplication.class, args);
	}

}

