package com.project.cleancode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RmaServiceConfig {

  @Value("${sysparam.return.order.host}")
  private String host;
  @Value("${sysparam.return.order.port}")
  private int port;
  @Value("${sysparam.return.order.context}")
  private String context;

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getContext() {
    return context;
  }
}
