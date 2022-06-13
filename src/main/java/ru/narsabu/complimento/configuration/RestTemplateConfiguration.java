package ru.narsabu.complimento.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;
import ru.narsabu.complimento.configuration.properties.ComplimentConfigurationProperties;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private final Logbook logbook;
    private final ComplimentConfigurationProperties complimentConfigurationProperties;

    @Bean
    public RestTemplate complimentRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return createAbstractRestTemplateBuilder(restTemplateBuilder, complimentConfigurationProperties)
                .defaultHeader(X_REQUESTED_WITH, complimentConfigurationProperties.getXRequestedWith())
                .defaultHeader("Content-Type", "text/plain")
                .build();
    }

    private RestTemplateBuilder createAbstractRestTemplateBuilder(RestTemplateBuilder restTemplateBuilder,
                                                                  RestClientConfig config) {
        return restTemplateBuilder.requestFactory(() -> createRequestFactory(config))
                                  .setConnectTimeout(Duration.of(config.getConnectTimeout(), MILLIS))
                                  .setReadTimeout(Duration.of(config.getReadTimeout(), MILLIS));
    }

    private ClientHttpRequestFactory createRequestFactory(RestClientConfig config) {
        return new HttpComponentsClientHttpRequestFactory(createHttpClient(config));
    }

    private HttpClient createHttpClient(RestClientConfig config) {
        return HttpClientBuilder.create()
                                .setMaxConnTotal(config.getMaxConnTotal())
                                .setMaxConnPerRoute(config.getMaxConnPerRoute())
                                .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
                                .addInterceptorLast(new LogbookHttpResponseInterceptor())
                                .build();
    }
}
