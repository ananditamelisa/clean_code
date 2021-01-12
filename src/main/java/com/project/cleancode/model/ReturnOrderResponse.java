package com.project.cleancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnOrderResponse{

  private String returnNo;
  private String gdnSku;
  private String orderNo;
  private String orderItemNo;
  private String productName;
  private Integer orderQuantity;
  private Double productSalePrice;
  private String orderLogistic;
  private String returnStatusCode;
  private String returnStatus;
  private String returnResolution;
  private String reason;
}
