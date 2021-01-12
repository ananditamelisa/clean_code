package com.project.cleancode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@JsonInclude(Include.NON_EMPTY)
public class OrderElementListResponse {

  private static final long serialVersionUID = 4889895949731220174L;

  private String orderNo;
  private String orderItemNo;
  private Integer qty;
  private Date orderDate;
  private String orderStatus;
  private String customerFullName;
  private String productName;
  private Double productPrice;
  private Double finalPrice;
  private Date dueDate;
  private String merchantDeliveryType;
  private String pickupPointName;
  private String itemSku;
  private Boolean paid;
  private boolean autoCancelWarning;
}
