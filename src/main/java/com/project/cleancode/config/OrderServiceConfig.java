package com.project.cleancode.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class OrderServiceConfig {

  @Value("${sysparam.order.host}")
  private String host;
  @Value("${sysparam.order.port}")
  private int port;
  @Value("${sysparam.order.context}")
  private String context;
}
