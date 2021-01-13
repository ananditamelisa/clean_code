package com.project.cleancode.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderServiceConfig {

  @Value("${sysparam.order.host}")
  private String host;
  @Value("${sysparam.order.port}")
  private int port;
  @Value("${sysparam.order.context}")
  private String context;
}
