package com.ahmedmq.twitter.api.openfeign.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class FeignClientConfiguration {

	private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	private final ClientRegistrationRepository clientRegistrationRepository;

	private static final Authentication ANONYMOUS_AUTHENTICATION = new AnonymousAuthenticationToken("anonymous",
			"anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

	@Bean
	public RequestInterceptor requestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		if (principal == null) {
			principal = ANONYMOUS_AUTHENTICATION;
		}

		// In spring-cloud-openfeign v4.0 this can be replaced with the OAuth2AccessTokenInterceptor

		OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("twitter")
				.principal(principal).build();
		OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
		if (isNull(authorizedClient)) {
			throw new IllegalStateException("client credentials flow on twitter  failed, client is null");
		}

		return requestTemplate -> {
			requestTemplate.header("Authorization", "Bearer " + authorizedClient.getAccessToken().getTokenValue());
		};
	}



	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager() {
		OAuth2AuthorizedClientProvider authorizedClientProvider =
				OAuth2AuthorizedClientProviderBuilder.builder()
						.clientCredentials()
						.build();

		AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
				new AuthorizedClientServiceOAuth2AuthorizedClientManager(
						clientRegistrationRepository, oAuth2AuthorizedClientService);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}
}
