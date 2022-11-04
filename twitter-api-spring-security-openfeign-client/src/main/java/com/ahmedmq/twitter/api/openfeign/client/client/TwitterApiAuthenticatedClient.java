package com.ahmedmq.twitter.api.openfeign.client.client;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.openfeign.client.config.FeignClientConfiguration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
		name = "twitter-openfeign-client",
		url = "${twitter.api.url}",
		configuration = FeignClientConfiguration.class
)
public interface TwitterApiAuthenticatedClient  {

	@GetMapping("2/users/by/username/{username}")
	UserLookup lookup(@PathVariable(name = "username") String username);

	@GetMapping("/2/users/{id}/liked_tweets")
	LikedTweets liked(
			@PathVariable("id") String id,
			@RequestParam("max_results") long limit,
			@RequestParam("pagination_token") String paginationToken
	);
}
