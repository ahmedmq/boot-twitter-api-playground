package com.ahmedmq.twitter.api.retrofit.client.provider.twitter;

import java.io.IOException;

import com.ahmedmq.twitter.api.common.model.auth.AuthToken;
import com.ahmedmq.twitter.api.retrofit.client.provider.twitter.TwitterApiClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import org.springframework.util.Assert;

@RequiredArgsConstructor
class Authentication implements Interceptor {

	private final TwitterApiClient authClient;
	private final String clientId;
	private final String secret;

	@NotNull
	@Override
	public Response intercept(Chain chain) throws IOException {
		var original = chain.request();
		if (containsAccessToken(original)) {
			return chain.proceed(original);
		}
		return chain.proceed(withAccessToken(original));
	}

	private static boolean containsAccessToken(Request original) {
		return original.header("Authorization") != null;
	}

	private Request withAccessToken(Request original) {
		return original
				.newBuilder()
				.addHeader("Authorization", accessTokenBearer())
				.build();
	}

	@SneakyThrows
	private String accessTokenBearer() {
		AuthToken token = authClient
				.authenticate(Credentials.basic(clientId, secret), "client_credentials")
				.execute()
				.body();

		Assert.notNull(token, "Token must not be empty");

		return "Bearer %s".formatted(token.getAccessToken());
	}
}