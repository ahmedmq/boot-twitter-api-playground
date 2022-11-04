package com.ahmedmq.twitter.api.common.service;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;

public interface TwitterApiService {

	UserLookup lookup(String username);

	LikedTweets liked(String id, long limit, String paginationToken);

}
