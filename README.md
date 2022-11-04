# Spring Boot Twitter API Playground

This repo contains multiple Spring Boot applications showcasing integration with Twitter REST APIs using different HTTP clients.

Twitter APIs provide different authentication [methods](https://developer.twitter.com/en/docs/authentication/overview), but this repo contains code to authenticate using [application-only](https://developer.twitter.com/en/docs/authentication/oauth-2-0/application-only) technique. In essence, we generate an App only access token(Bearer token) by passing the Twitter consumer key and consumer secret to the `/token/oauth` endpoint.

The different modules use three different Twitter APIs

| API                                                          | Description                                          |
|--------------------------------------------------------------|------------------------------------------------------|
| `POST https://api.twitter.com/oauth2/token`                  | Generate an access token (Bearer token) for app only |
| `GET https://api.twitter.com/2/users/by/username/{username}` | User details lookup by username                      |
| `GET https://api.twitter.com/2/users/{id}/liked_tweets`      | List of liked tweets                                 |


## Modules

### twitter-api-retrofit-client

Make use of the [Retrofit](https://square.github.io/retrofit/) HTTP client for interfacing with the Twitter API. Spring Cloud provides an abstraction to Retrofit client with [Spring Cloud Square](https://github.com/spring-projects-experimental/spring-cloud-square) dependency

### twitter-api-spring-security-openfeign-client

Use the [OpenFeign](https://github.com/OpenFeign/feign) HTTP client for interfacing with the Twitter API. Spring Cloud provides an abstraction to OpenFeign with the [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign) dependency

Also uses [Spring Security OAuth Client](https://docs.spring.io/spring-security/reference/reactive/oauth2/client/index.html) to execute the `client_credentails` flow for the Twitter API authentication 

### twitter-api-spring-security-webclient

Use the Spring Webflux [Webclient](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html) to perform the HTTP requests in a non-blocking, reactive programming way.

Similar to the OpenFeign module, this also uses Spring Security Oauth Client for executing the OAuth client credentials flow

### twitter-api-common

Contains the core domain classes shared across all the above three modules.

## Pre-requisites

Get access to the Twitter API by following the steps mentioned [here](https://developer.twitter.com/en/docs/twitter-api/getting-started/getting-access-to-the-twitter-api)
