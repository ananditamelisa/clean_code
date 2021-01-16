package com.project.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemSummaryRequest {

  private List<String> orderItemStatus;
  private String pickupPointCode;
  private String productTypeCode;
  private String merchantCode;
  private boolean instantPickup;
  private Date autoCancelTimestamp;
  private Date orderCreatedTimestamp;
}
