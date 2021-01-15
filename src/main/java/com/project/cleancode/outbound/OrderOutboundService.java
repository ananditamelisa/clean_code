package com.project.cleancode.outbound;

import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;
import com.project.cleancode.model.RmaOutboundResponse;

import java.util.List;

public interface OrderOutboundService {

  List<OrderItemSummaryResponse> findOrderItemSummaryByFilter(
      OrderItemSummaryRequest filterRequest,
      String orderBy, String sortBy, int page, int size);

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
