package com.ahmedmq.twitter.api.openfeign.client;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.common.service.TwitterApiService;
import com.ahmedmq.twitter.api.openfeign.client.client.TwitterApiAuthenticatedClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwitterApiOpenFeignClientService implements TwitterApiService {

	private final TwitterApiAuthenticatedClient twitter;

	@Override
	public UserLookup lookup(String username) {
		return twitter.lookup(username);
	}

	@Override
	public LikedTweets liked(String id, long limit, String paginationToken) {
		return twitter.liked(id, limit, paginationToken);
	}
}