package com.wasu.springboot.integration;

import com.wasu.springboot.integration.common.config.InitReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication application=new SpringApplication(ServiceApplication.class);
		application.addListeners(new InitReadyEvent());
		application.run(args);
	}

}

