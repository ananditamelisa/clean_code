package com.project.cleancode.outbound;

import com.project.cleancode.enums.ApiOutboundCode;
import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderOutboundServiceImpl implements OrderOutboundService{

  @Autowired
  private RestTemplateUtil restTemplateUtil;

  @Override
  public List<OrderItemSummaryResponse> findOrderItemSummaryByFilter(
      OrderItemSummaryRequest filterRequest,
      String orderBy, String sortBy, int page, int size) {
    Map<String, Object> additionalParameters = new HashMap<>();
    additionalParameters.put("page", page);
    additionalParameters.put("size", size);
    additionalParameters.put("orderBy", orderBy);
    additionalParameters.put("sortBy", sortBy);

    ResponseEntity<List<OrderItemSummaryResponse>> response =
        restTemplateUtil.exchangeRequest(
            new ParameterizedTypeReference<List<OrderItemSummaryResponse>>() {},
            ApiOutboundCode.ORDER_GET_LIST,
            additionalParameters,
            null,
            filterRequest);

    return response.getBody();
  }
}
