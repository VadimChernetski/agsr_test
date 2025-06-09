package by.agsr.statistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;

@Configuration
@EnableScheduling
public class WebConfig {

    @Value("${sensor.service.url}")
    private String sensorServiceUrl;

    @Value("${statistics.token}")
    private String token;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
          .baseUrl(sensorServiceUrl)
          .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
          .build();
    }
}
