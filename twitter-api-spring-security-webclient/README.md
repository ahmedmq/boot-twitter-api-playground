# Twitter API using Spring Security and Spring Webclient

This repository contains sample code to integrate a Spring Boot application with the Twitter OAuth API using
Spring Security and Spring Webflux(WebClient)

## Usage

- Configure the following environment variables before running the application

| Environment Variable    | Description                                                                        |
|-------------------------|------------------------------------------------------------------------------------|
| twitter-consumer-key    | Also known as the API key that acts like a username for the twitter integration    |
| twitter-consumer-secret | Also known as the API secret that acts like a password for the twitter integration |
| twitter-username        | Twitter username whose details are fetched                                         |

The consumer key and consumer secret can be fetched from the Twitter Developer Portal. Details mentioned [here](https://developer.twitter.com/en/docs/authentication/oauth-1-0a/api-key-and-secret)

- Start the application from the console by entering the command from the root of the project

    ```
    ./mvnw spring-boot:run
    ```
If all works well, then the liked tweets for the given Twitter username are displayed on the console    

```
Data[id=1583562049761644544, text=How to make the best paper airplane  https://t.co/1w4Bj7RLH3]
Data[id=1581902874811854848, text=Why is this so true https://t.co/4LoVSfOpdr https://t.co/AXNx1IunkR]
Data[id=1581654488598736896, text=@elonmusk “Two things are infinite: The universe and human stupidity.” 
...
```