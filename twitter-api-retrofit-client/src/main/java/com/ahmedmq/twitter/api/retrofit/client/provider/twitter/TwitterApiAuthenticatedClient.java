package com.ahmedmq.twitter.api.retrofit.client.provider.twitter;


import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.retrofit.client.provider.LoggingRetrofitConfig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import org.springframework.cloud.square.retrofit.core.RetrofitClient;

@RetrofitClient(
		name = "twitter-api-authenticated-client",
		url = "${twitter.api.url}",
		configuration = { AuthenticatedClientsConfig.class, LoggingRetrofitConfig.class}
)
public interface TwitterApiAuthenticatedClient {

	@GET("2/users/by/username/{username}")
	Call<UserLookup> lookup(@Path("username") String username);

	@GET("/2/users/{id}/liked_tweets")
	Call<LikedTweets> liked(
			@Path("id") String id,
			@Query("max_results") long limit,
			@Query("pagination_token") String paginationToken
	);
}
