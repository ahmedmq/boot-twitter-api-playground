package com.ahmedmq.twitter.api.retrofit.client.provider.twitter;

import com.ahmedmq.twitter.api.common.model.auth.AuthToken;
import com.ahmedmq.twitter.api.retrofit.client.provider.LoggingRetrofitConfig;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

import org.springframework.cloud.square.retrofit.core.RetrofitClient;

@RetrofitClient(name = "twitter-api-client",
		url = "${twitter.api.url}",
		configuration = LoggingRetrofitConfig.class
		)
public interface TwitterApiClient {

	@POST("oauth2/token")
	@FormUrlEncoded
	Call<AuthToken> authenticate(@Header("Authorization") String authorisation, @Field("grant_type") String grantType);
}

