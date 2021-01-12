package com.project.cleancode.service;

import com.project.cleancode.outbound.OrderOutboundService;
import com.project.cleancode.model.OrderElementListResponse;
import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;
import com.project.cleancode.model.OrderListFilterVO;
import com.project.cleancode.service.util.OrderComponentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService<List<OrderElementListResponse>>{

  @Autowired
  private OrderComponentUtil orderComponentUtil;
  @Autowired
  protected OrderOutboundService orderOutboundService;

  @Override
  public List<OrderElementListResponse> filter(Object... args) {
    String username = (String) args[0];
    String merchantId = (String) args[1];
    int page = (int) args[2];
    int size = (int) args[3];
    OrderListFilterVO vo = (OrderListFilterVO) args[4];
    OrderItemSummaryRequest filterRequest =
        orderComponentUtil.getFilterRequest(username, vo);
    List<OrderItemSummaryResponse> orderItemList = orderOutboundService
        .findOrderItemSummaryByFilter(filterRequest, vo.getOrderBy(), vo.getSortBy(), page, size);
    List<OrderElementListResponse> orderElementList = new ArrayList<>();
    if (orderItemList != null) {
      for (OrderItemSummaryResponse orderElement : orderItemList) {
        OrderElementListResponse orderElementResponse =
            orderComponentUtil.mapToOrderListResponse(orderElement);
        orderElementList.add(orderElementResponse);
      }
    }
    return orderElementList;
  }
}
