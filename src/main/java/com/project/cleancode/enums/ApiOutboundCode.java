package com.project.cleancode.enums;

import org.springframework.web.bind.annotation.RequestMethod;

public enum ApiOutboundCode {

  ORDER_GET_LIST(OutboundServiceType.ORDER, "/api/summary/filter",
      RequestMethod.POST.name()),
  RMA_RETURN_GET_LIST_FILTER(OutboundServiceType.RMA, "/api/filterFindRmaByMerchantCode",
      RequestMethod.GET.name());


  private final OutboundServiceType service;
  private final String path;
  private final String requestMethod;

  ApiOutboundCode(OutboundServiceType service, String path, String requestMethod) {
    this.service = service;
    this.path = path;
    this.requestMethod = requestMethod;
  }

  public String getPath() {
    return path;
  }

  public OutboundServiceType getService() {
    return service;
  }

  public String getRequestMethod() {
    return requestMethod;
  }
}
