package com.project.cleancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RmaOutboundResponse {

  private int quantity;
  private int oldQuantity; // input when qc
  private boolean isExpectProductReturn;

  private String reason;
  private String oldReason; // input when qc
  private String fractionQuantity; // input when qc
  private String reasonPO;
  private String responsibility;
  private String rmaNumber;
  private String rmaRejectReason;

  private String rmaRequestApprovalBy;
  private String storeId;
  private RmaResolution rmaResolution;
  private String status;
}
