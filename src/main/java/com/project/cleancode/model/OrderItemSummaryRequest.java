package com.project.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSummaryRequest {

  private List<String> orderOrItemIds;
  private String orderItemId;
  private String orderId;
  private String customerFullName;
  private String merchantSku;
  private String itemName;
  private Integer quantity;
  private Double price;
  private String logisticsProductCode;
  private List<String> orderItemStatus;
  private List<String> orderType;
  private String pickupPointCode;
  private String productTypeName;
  private String productTypeCode;
  private String merchantCode;
  private Boolean markForDelete = false;
  private Boolean isInternationalSeller;
  private boolean instantPickup;

  private String merchantDeliveryType;
  private Date orderDate;
  private Date autoCancelTimestamp;

  private Boolean isMerchantPaid;

  private Date statusFPUpdatedTimestamp;

  private Boolean packageCreated;

  private String packageId;

  @Deprecated
  private String productType;

  @Deprecated
  private Date createdDate;

}
