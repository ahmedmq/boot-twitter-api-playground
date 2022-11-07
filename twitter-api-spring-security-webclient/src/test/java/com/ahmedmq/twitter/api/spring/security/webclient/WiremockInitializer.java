package com.ahmedmq.twitter.api.spring.security.webclient;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

public class WiremockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
		wireMockServer.start();

		wireMockServer.stubFor(
				WireMock.post("/oauth2/token")
						.withBasicAuth("test-consumer-key","test-consumer-secret")
						.withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8"))
						.withRequestBody(containing("grant_type=client_credentials"))
						.willReturn(
								aResponse()
										.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
										.withBodyFile("token-response.json")
						)
		);

		TestPropertyValues
				.of(
						"spring.security.oauth2.client.provider.twitter.token-uri=http://localhost:" + wireMockServer.port() + "/oauth2/token",
						"twitter.api.url=http://localhost:" + wireMockServer.port()
				).applyTo(applicationContext);

		applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

		applicationContext.addApplicationListener(applicationEvent -> {
			if (applicationEvent instanceof ContextClosedEvent) {
				wireMockServer.stop();
			}
		});

	}



}
