package com.ahmedmq.twitter.api.retrofit.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.square.retrofit.EnableRetrofitClients;

@SpringBootApplication
@EnableConfigurationProperties
@EnableRetrofitClients
public class TwitterApiRetrofitClientApplication {

	@Value("${twitter.username}")
	String username;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApiRetrofitClientApplication.class, args);
	}

}
