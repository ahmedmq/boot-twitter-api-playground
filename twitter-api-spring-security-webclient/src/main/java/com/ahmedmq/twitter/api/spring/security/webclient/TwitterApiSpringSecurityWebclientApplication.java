package com.ahmedmq.twitter.api.spring.security.webclient;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TwitterApiSpringSecurityWebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterApiSpringSecurityWebclientApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TwitterApiWebclientService twitter){
		return  args -> {
			UserLookup lookup = twitter.lookup("ahmedmq_t");
			if (lookup != null){
				LikedTweets likedTweets = twitter.liked(lookup.data().id(), 10, null);
				likedTweets.data().forEach(System.out::println);
			}
		};
	}

}
