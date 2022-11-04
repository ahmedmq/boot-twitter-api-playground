package com.ahmedmq.twitter.api.retrofit.client.provider;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "twitter")
@Configuration
@Data
public class TwitterProperties {

	private Auth auth = new Auth();

	@Data
	public class Auth {
		String consumerKey;
		String consumerSecret;
	}

}
