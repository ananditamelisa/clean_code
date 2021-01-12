package com.project.cleancode.outbound;

import com.project.cleancode.model.RmaOutboundResponse;

import java.util.List;

public interface RmaOutboundService {

  List<RmaOutboundResponse> filterFindRmaByMerchantCode(String merchantCode,
      String rmaNumber,
      String orderIdItemId,
      String returDate,
      String rmaResolution,
      String status,
      int size,
      int page)
      throws Exception;
}
