package com.project.cleancode.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderListFilterVO {

  private String status;
  private String logistic;
  private List<String> filterOrderTypes;
  private Date orderDate;
  private String productTypeCode;
  private Date filterAutoCancelTimestamp;
  private String orderBy;
  private String sortBy;
  private boolean instantPickup;
  private String pickupPointCode;
  private Date filterStartDate;
  private Date filterEndDate;
  private String searchFlag;
  private String searchTerm;
}
