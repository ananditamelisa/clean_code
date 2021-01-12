package com.project.cleancode.outbound;

import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;

import java.util.List;

public interface OrderOutboundService {

  List<OrderItemSummaryResponse> findOrderItemSummaryByFilter(
      OrderItemSummaryRequest filterRequest,
      String orderBy, String sortBy, int page, int size);
}
