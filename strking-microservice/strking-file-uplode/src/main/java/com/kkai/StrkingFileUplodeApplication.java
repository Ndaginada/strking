package com.kkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.HashMap;

@SpringBootApplication
@EnableEurekaClient
public class StrkingFileUplodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrkingFileUplodeApplication.class, args);
	}
}
