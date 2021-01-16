package com.project.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListFilterVO {

  private String status;
  private String logistic;
  private Date orderDate;
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
