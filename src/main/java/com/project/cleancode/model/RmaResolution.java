package com.project.cleancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.cleancode.enums.RmaResolutionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RmaResolution {

  private String approvalBy;
  private Date approvalDate;
  private Boolean tarikMP;
  private String metodePengirimanCustomer;
  private String metodePengirimanCustomerDescription;
  private RmaResolutionType rmaResolutionType;
}
