package com.project.cleancode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class OrderElementListResponse {

  private static final long serialVersionUID = 4889895949731220174L;

  private String orderNo;
  private String orderItemNo;
  private Integer qty;
  private Date orderDate;
  private String orderStatus;
  private String orderStatusString;
  private String customerFullName;
  private String productName;
  private Double productPrice;
  private Double finalPrice;
  private String logisticService;
  private String logisticProviderCode;
  private Date dueDate;
  private String merchantDeliveryType;
  private String logisticsOptionName;
  private String logisticsOptionCode;
  private String merchantSku;
  private String productTypeCode;
  private String productTypeName;
  private String logisticsProductCode;
  private String logisticsProductName;
  private String pickupPointCode;
  private String pickupPointName;
  private String itemSku;
  private String awbNumber;
  private String awbStatus;
  private Boolean paid;
  private boolean instantPickup;
  private boolean settlementCodeExpired;
  private boolean autoCancelWarning;
  private Date readyToProcessDate;
  private String packageId;
  private Boolean packageCreated;
  private String orderType;

  public OrderElementListResponse(String orderNo, String orderItemNo, Integer qty, Date orderDate,
      String orderStatus, String orderStatusString, String productName, Double productPrice) {
    this.orderNo = orderNo;
    this.orderItemNo = orderItemNo;
    this.qty = qty;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.orderStatusString = orderStatusString;
    this.productName = productName;
    this.productPrice = productPrice;
  }
}
