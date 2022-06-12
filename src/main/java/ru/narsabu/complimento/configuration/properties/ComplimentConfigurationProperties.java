package ru.narsabu.complimento.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.narsabu.complimento.configuration.RestClientConfig;

@Data
@Configuration
@ConfigurationProperties(prefix = "compliment")
public class ComplimentConfigurationProperties implements RestClientConfig {

    private String url;
    private String xRequestedWith;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer maxConnTotal;
    private Integer maxConnPerRoute;
}
