package com.project.cleancode.enums;

public enum ApiErrorCode {
  ORDER_LIST_START_DATE_IS_GREATER("ORD-LI-03", 400,
      "Order start date filter can't greater than end date"),
  ORDER_LIST_DATE_RANGE_FILTER_IS_TOO_LONG("ORD-LI-04", 400,
      "Order date filter range is too long"),
  BASE_OUTBOUND_URI_CONFIG_NOT_FOUND("REST-07", 500,
      "Outbound uri configuration is not found"),;



  private final String code;
  private final int httpStatus;
  private final String desc;

  ApiErrorCode(String code, int httpStatus, String desc) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.desc = desc;
  }
  public String getCode() {
    return code;
  }
  public String getDesc() {
    return desc;
  }
  public int getHttpStatus() {
    return httpStatus;
  }
}
