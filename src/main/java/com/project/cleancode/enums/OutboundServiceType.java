package com.project.cleancode.enums;

import com.project.cleancode.config.OrderServiceConfig;
import com.project.cleancode.config.RmaServiceConfig;

public enum OutboundServiceType {

  ORDER("order-service", "orderRestTemplate", OrderServiceConfig.class),
  RMA("return-order-service", "returnOrderRestTemplate",
      RmaServiceConfig.class);

  private String serviceName;

  private String restTemplateName;

  private Class<?> uriConfig;

  OutboundServiceType(String serviceName, String restTemplateName,
      Class<?> uriConfig) {
    this.serviceName = serviceName;
    this.restTemplateName = restTemplateName;
    this.uriConfig = uriConfig;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getRestTemplateName() {
    return restTemplateName;
  }

  public Class<?> getUriConfig() {
    return uriConfig;
  }
}
