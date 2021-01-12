package com.project.cleancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OldOrderInformation {

  private String oldOrderId;
  private String oldOrderItemId;
  private String skuCode;
  private String skuName;
  private double productPrice;
}
