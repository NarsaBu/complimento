package ru.narsabu.complimento.configuration;

public interface RestClientConfig {

    Integer getConnectTimeout();

    Integer getReadTimeout();

    Integer getMaxConnTotal();

    Integer getMaxConnPerRoute();
}
