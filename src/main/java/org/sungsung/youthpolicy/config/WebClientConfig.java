package org.sungsung.youthpolicy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
//   ai에 요청을 보내기위한 설정
    @Value("${openai.api-key}")
    private String apiKey;

    @Bean
    public WebClient openAiWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.openai.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }
}
