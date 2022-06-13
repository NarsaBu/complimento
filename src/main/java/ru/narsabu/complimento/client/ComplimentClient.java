package ru.narsabu.complimento.client;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.narsabu.complimento.configuration.properties.ComplimentConfigurationProperties;

@Service
@RequiredArgsConstructor
public class ComplimentClient {

    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    private final ComplimentConfigurationProperties config;
    private final RestTemplate complimentRestTemplate;

    public String performRequest() {
            val response = complimentRestTemplate.postForObject(HTTPS + config.getUrl(), null, String.class);
            if (!response.contains("success")) {
                return complimentRestTemplate.postForObject(HTTP + config.getUrl(), null, String.class);
            }

            return response;
    }
}
