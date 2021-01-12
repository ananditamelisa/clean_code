package com.project.cleancode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class BeanConfiguration {

  @Bean(name = "orderRestTemplate")
  public RestTemplate xOrderTemplate() {
    HttpComponentsClientHttpRequestFactory connectionFactory =
        new HttpComponentsClientHttpRequestFactory();
    connectionFactory.setConnectTimeout(2000);
    connectionFactory.setReadTimeout(2000);

    return new RestTemplate(connectionFactory);
  }
}
