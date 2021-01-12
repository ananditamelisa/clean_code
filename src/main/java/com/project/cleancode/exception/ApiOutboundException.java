package com.project.cleancode.exception;

import com.project.cleancode.enums.ApiErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiOutboundException extends RuntimeException{

  private String customErrorCode;
  private String customErrorMsg;
  private String httpsStatus;

  public ApiOutboundException(ApiErrorCode errorCode, String customErrorMsg, Exception e) {
    super(e);
    this.customErrorCode = errorCode.getCode();
    this.customErrorMsg = customErrorMsg;
    this.httpsStatus = String.valueOf(errorCode.getHttpStatus());
  }
}
