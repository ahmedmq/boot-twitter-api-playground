package com.ahmedmq.twitter.api.openfeign.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TwitterApiOpenfeignClientApplication {

	@Value("${twitter.username}")
	String username;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApiOpenfeignClientApplication.class, args);
	}
}
