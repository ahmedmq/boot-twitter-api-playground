package com.ahmedmq.twitter.api.retrofit.client.provider;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

import org.springframework.context.annotation.Bean;

public class LoggingRetrofitConfig {

	@Bean
	public Interceptor loggingInterceptor() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return interceptor;
	}

}