package com.ahmedmq.twitter.api.retrofit.client;

import java.io.IOException;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.common.service.TwitterApiService;
import com.ahmedmq.twitter.api.retrofit.client.provider.twitter.TwitterApiAuthenticatedClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwitterApiRetrofitClientService implements TwitterApiService {

	private final TwitterApiAuthenticatedClient twitter;

	public UserLookup lookup(String username) {
		try {
			return twitter.lookup(username).execute().body();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public LikedTweets liked(String id, long limit, String paginationToken) {
		try {
			return twitter.liked(id, limit, paginationToken).execute().body();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
