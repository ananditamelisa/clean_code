package com.project.cleancode.exception;

import com.project.cleancode.enums.ApiErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMetadataException extends RuntimeException{

  private String exceptionType;
  private String errorMsg;
  private ApiErrorCode errorCode;
}
