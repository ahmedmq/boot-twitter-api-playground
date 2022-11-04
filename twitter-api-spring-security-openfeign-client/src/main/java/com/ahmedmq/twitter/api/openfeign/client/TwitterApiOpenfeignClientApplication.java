package com.ahmedmq.twitter.api.openfeign.client;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class TwitterApiOpenfeignClientApplication {

	@Value("${twitter.username}")
	String username;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApiOpenfeignClientApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TwitterApiOpenFeignClientService twitter){
		return args ->  {
			UserLookup lookup = twitter.lookup(username);
			if (lookup != null){
				LikedTweets likedTweets = twitter.liked(lookup.data().id(), 10, null);
				likedTweets.data().forEach(System.out::println);
			}
		};
	}

}
