package com.project.cleancode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderFilterRequest {

  private String status;
  private String logistic;
  private String logisticCode;
  private String filterOrderType;
  private List<String> filterOrderTypes = new ArrayList<>();
  private String orderDate;
  private String productTypeCode;
  private String filterAutoCancelTimestamp;
  private String orderBy;
  private String sortBy;
  private boolean instantPickup;
  private String pickupPointCode;
  private String filterStartDate;
  private String filterEndDate;
  private String searchFlag;
  private String searchTerm;
}
