package com.ahmedmq.twitter.api.retrofit.client;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.square.retrofit.EnableRetrofitClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
@EnableRetrofitClients
public class TwitterApiRetrofitClientApplication {

	@Value("${twitter.username}")
	String username;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApiRetrofitClientApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TwitterApiRetrofitClientService twitter){
		return args ->  {
			UserLookup lookup = twitter.lookup(username);
			if (lookup != null){
				LikedTweets likedTweets = twitter.liked(lookup.data().id(), 10, null);
				likedTweets.data().forEach(System.out::println);
			}
		};

	}

}
