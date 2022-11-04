package com.ahmedmq.twitter.api.retrofit.client.provider.twitter;

import com.ahmedmq.twitter.api.retrofit.client.provider.TwitterProperties;
import okhttp3.Interceptor;

import org.springframework.context.annotation.Bean;

public class AuthenticatedClientsConfig {
	@Bean
	Interceptor authentication(TwitterApiClient authClient, TwitterProperties properties) {
		return new Authentication(authClient,
				properties.getAuth().getConsumerKey(),
				properties.getAuth().getConsumerSecret());
	}
}
