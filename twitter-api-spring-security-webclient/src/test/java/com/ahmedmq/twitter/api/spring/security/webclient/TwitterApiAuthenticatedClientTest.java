package com.ahmedmq.twitter.api.spring.security.webclient;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(initializers = WiremockInitializer.class)
@SpringBootTest
@ActiveProfiles("test")
class TwitterApiAuthenticatedClientTest
{

	@Autowired
	TwitterApiWebclientService twitter;

	@Autowired
	WireMockServer wireMockServer;

	@Test
	void user_lookup_endpoint() {

		wireMockServer.stubFor(
				WireMock.get("/2/users/by/username/twitter_demo")
						.withHeader("Authorization", equalTo("Bearer AAAAAAAAAAAAAAAAAAAAAEgkiwEAAAAAam%2Bb49N5bkbiQIx6Nzr6tvOR5kk%3DRQngUp3Lu11HZttp1TkGbBuvTa4F66HuqgkZgfYTmGi299gxDY"))
						.willReturn(
								aResponse()
										.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
										.withBodyFile("user-lookup.json")
						)
		);

		UserLookup lookup = twitter.lookup("twitter_demo");
		assertThat(lookup).isNotNull();
		assertThat(lookup.data()).isNotNull();
		assertThat(lookup.data().id()).isEqualTo("342345708");
		assertThat(lookup.data().name()).isEqualTo("Twitter Demo");
		assertThat(lookup.data().username()).isEqualTo("twitter_demo");

	}

	@Test
	void liked_tweet_endpoint() {

		wireMockServer.stubFor(
				WireMock.get("/2/users/342345708/liked_tweets?max_results=5")
						.withQueryParam("max_results", equalTo("5"))
						.withHeader("Authorization", equalTo("Bearer AAAAAAAAAAAAAAAAAAAAAEgkiwEAAAAAam%2Bb49N5bkbiQIx6Nzr6tvOR5kk%3DRQngUp3Lu11HZttp1TkGbBuvTa4F66HuqgkZgfYTmGi299gxDY"))
						.willReturn(
								aResponse()
										.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
										.withBodyFile("liked-tweets.json")
						)
		);

		LikedTweets liked = twitter.liked("342345708", 5, null);
		assertThat(liked).isNotNull();
		assertThat(liked.data()).isNotNull();
		assertThat(liked.data().size()).isEqualTo(5);
		assertThat(liked.data().get(0).id()).isEqualTo("1583562049761644544");
		assertThat(liked.data().get(0).text()).isEqualTo("How to make the best paper airplane  https://t.co/1w4Bj7RLH3");
	}
}