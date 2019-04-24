package com.kkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroseviceArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroseviceArticleApplication.class, args);
	}
}
