package ru.narsabu.complimento.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bot")
public class TelegramBotConfigurationProperties {

    private String name;
    private String username;
    private String token;
}
