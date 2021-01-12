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

  private String reason;
  private String rmaNumber;
  private String rmaRejectReason;
  private String status;

  private String rmaRequestApprovalBy;
  private OldOrderInformation oldOrderInformation;
  private RmaResolution rmaResolution;
}
