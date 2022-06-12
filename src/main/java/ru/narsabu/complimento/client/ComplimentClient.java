package ru.narsabu.complimento.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.narsabu.complimento.configuration.properties.ComplimentConfigurationProperties;

@Service
@RequiredArgsConstructor
public class ComplimentClient {

    private final ComplimentConfigurationProperties config;
    private final RestTemplate complimentRestTemplate;

    public String performRequest() {
        return complimentRestTemplate.postForObject(config.getUrl(), null, String.class);
    }
}
