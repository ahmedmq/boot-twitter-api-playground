package com.ahmedmq.twitter.api.spring.security.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.authorizeRequests(auth -> {
					auth.anyRequest().permitAll();
				})
				.oauth2Client()
				.and().
				build();
	}

}
