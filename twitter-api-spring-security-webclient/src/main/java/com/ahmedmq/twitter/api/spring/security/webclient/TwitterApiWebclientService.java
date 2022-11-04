package com.ahmedmq.twitter.api.spring.security.webclient;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.common.service.TwitterApiService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
@RequiredArgsConstructor
public class TwitterApiWebclientService implements TwitterApiService {

	private final WebClient webClient;

	@Override
	public UserLookup lookup(String username) {
		URI uri = UriComponentsBuilder
				.fromUriString("https://api.twitter.com/2/users/by/username/{username}")
				.buildAndExpand(Collections.singletonMap("username", username)).toUri();

		return webClient
				.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(UserLookup.class)
				.block();
	}

	@Override
	public LikedTweets liked(String id, long limit, String paginationToken) {

		URI uri = UriComponentsBuilder
				.fromUriString("https://api.twitter.com/2/users/{id}/liked_tweets")
				.queryParam("max_results",limit)
				.queryParamIfPresent("pagination_token", Optional.ofNullable(paginationToken))
				.buildAndExpand(Collections.singletonMap("id", id)).toUri();

		return webClient
				.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(LikedTweets.class)
				.block();
	}
}
