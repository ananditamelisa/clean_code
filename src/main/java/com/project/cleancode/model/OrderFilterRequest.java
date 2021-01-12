package com.project.cleancode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderFilterRequest {

  private String status;
  private String logisticCode;
  private String orderDate;
  private String orderBy;
  private String sortBy;
  private String pickupPointCode;
  private String filterStartDate;
  private String filterEndDate;
  private String searchTerm;
}
