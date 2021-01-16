package com.project.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemSummaryResponse {

  private String orderItemId;
  private String orderId;
  private String customerFullName;
  private String itemName;
  private Integer quantity;
  private Double price;
  private Double merchantAdjustment;
  private String orderItemStatus;
  private String pickupPointName;
  private String itemSku;
  private String merchantCode;
  private Date autoCancelTimestamp;
  private String merchantDeliveryType;
  private Date orderDate;
  private Boolean isMerchantPaid;
}
