package com.project.cleancode.enums;

import com.project.cleancode.config.OrderConfig;
import com.project.cleancode.config.RmaConfig;

public enum OutboundServiceType {

  ORDER("order-service", "orderRestTemplate", OrderConfig.class),
  RMA("return-order-service", "returnOrderRestTemplate",
      RmaConfig.class);

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
