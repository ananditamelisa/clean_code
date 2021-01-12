package com.project.cleancode.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ReturnOrderServiceConfig {

  @Value("${sysparam.return.order.host}")
  private String host;
  @Value("${sysparam.return.order.port}")
  private int port;
  @Value("${sysparam.return.order.context}")
  private String context;
}
