package com.ahmedmq.twitter.api.retrofit.client.provider.twitter;

import com.ahmedmq.twitter.api.common.model.liked_tweets.LikedTweets;
import com.ahmedmq.twitter.api.common.model.user_lookup.UserLookup;
import com.ahmedmq.twitter.api.retrofit.client.TwitterApiRetrofitClientService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWireMock(port = 0)
@TestPropertySource(
		properties = {
				"twitter.api.url=http://localhost:${wiremock.server.port}"
		}
)
@SpringBootTest
class TwitterApiAuthenticatedClientTest
{

	@Autowired
	TwitterApiRetrofitClientService twitter;

	@Test
	void user_lookup_endpoint() {

		UserLookup lookup = twitter.lookup("twitter_demo");
		assertThat(lookup).isNotNull();
		assertThat(lookup.data()).isNotNull();
		assertThat(lookup.data().id()).isEqualTo("342345708");
		assertThat(lookup.data().name()).isEqualTo("Twitter Demo");
		assertThat(lookup.data().username()).isEqualTo("twitter_demo");

	}

	@Test
	void liked_tweet_endpoint() {

		LikedTweets liked = twitter.liked("342345708", 5, null);
		assertThat(liked).isNotNull();
		assertThat(liked.data()).isNotNull();
		assertThat(liked.data().size()).isEqualTo(5);
		assertThat(liked.data().get(0).id()).isEqualTo("1583562049761644544");
		assertThat(liked.data().get(0).text()).isEqualTo("How to make the best paper airplane  https://t.co/1w4Bj7RLH3");
	}
}