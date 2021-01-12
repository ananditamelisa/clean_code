package com.project.cleancode.enums;

public enum ReturnResolutionType {

  NONE("-"),
  CASH_REFUND("Cash Refund"),
  VOUCHER_REFUND("Voucher Refund"),
  PRODUCT_REPLACEMENT("Product Replacement"),
  MANUAL("Other Manual Process");

  private String message;

  private ReturnResolutionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
