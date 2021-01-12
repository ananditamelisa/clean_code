package com.project.cleancode.enums;

public enum RmaResolutionType {
  NONE("Belum ada solusi"),
  CASH_REFUND("Pengembalian Uang"),
  VOUCHER_REFUND("Pengembalian Voucher Belanja"),
  PRODUCT_REPLACEMENT("Tukar Produk"),
  MANUAL("Lainnya");

  private String description;

  private RmaResolutionType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
