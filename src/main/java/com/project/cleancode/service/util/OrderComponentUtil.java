package com.project.cleancode.service.util;

import com.project.cleancode.model.OrderElementListResponse;
import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;
import com.project.cleancode.model.OrderListFilterVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrderComponentUtil {

  private static final List<String> allowedOrderStatus = Arrays
      .asList("DELIVERED", "WAITING_FOR_PAYMENT",
          "CANCELLED");

  public OrderItemSummaryRequest getFilterRequest(String merchantId, OrderListFilterVO vo) {
    OrderItemSummaryRequest filterRequest = new OrderItemSummaryRequest();
    filterRequest.setMerchantCode(merchantId);
    filterRequest.setAutoCancelTimestamp(vo.getFilterAutoCancelTimestamp());
    filterRequest.setInstantPickup(vo.isInstantPickup());
    if (StringUtils.isNotEmpty(vo.getStatus())) {
      filterRequest.setOrderItemStatus(getFilterStatusList(vo.getStatus()));
    } else {
      filterRequest.setOrderItemStatus(allowedOrderStatus);
    }

    if (StringUtils.isNotBlank(vo.getProductTypeCode())) {
      filterRequest.setProductTypeCode(vo.getProductTypeCode());
    }
    if (StringUtils.isNotBlank(vo.getPickupPointCode())) {
      filterRequest.setPickupPointCode(vo.getPickupPointCode());
    }

    return filterRequest;
  }

  public OrderElementListResponse mapToOrderListResponse(
      OrderItemSummaryResponse orderItemSummaryResponse) {
    OrderElementListResponse result = new OrderElementListResponse();
    result.setOrderNo(orderItemSummaryResponse.getOrderId());
    result.setOrderItemNo(orderItemSummaryResponse.getOrderItemId());
    result.setQty(orderItemSummaryResponse.getQuantity());
    result.setOrderDate(orderItemSummaryResponse.getOrderDate());

    Optional<String> orderStatus =
        allowedOrderStatus.stream()
            .filter(status -> orderItemSummaryResponse.getOrderItemStatus().equals(status))
            .findFirst();
    if(orderStatus.isPresent()) {
      result.setOrderStatus(orderStatus.get());
    } else {
      result.setOrderStatus("-");
    }

    result.setProductName(orderItemSummaryResponse.getItemName());
    result.setProductPrice(orderItemSummaryResponse.getPrice());
    result.setDueDate(orderItemSummaryResponse.getAutoCancelTimestamp());
    result.setMerchantDeliveryType(orderItemSummaryResponse.getMerchantDeliveryType());
    result.setPickupPointName(orderItemSummaryResponse.getPickupPointName());
    result.setItemSku(orderItemSummaryResponse.getItemSku());
    result.setPaid(orderItemSummaryResponse.getIsMerchantPaid());
    result.setCustomerFullName(orderItemSummaryResponse.getCustomerFullName());
    result.setAutoCancelWarning(
        isAutoCancelWarning(orderItemSummaryResponse.getOrderItemStatus(),
            orderItemSummaryResponse.getAutoCancelTimestamp()));
    result.setFinalPrice(Optional.ofNullable(orderItemSummaryResponse.getPrice())
        .map(BigDecimal::valueOf).orElse(BigDecimal.ZERO)
        .subtract(Optional.ofNullable(orderItemSummaryResponse.getMerchantAdjustment())
            .map(BigDecimal::valueOf).orElse(BigDecimal.ZERO))
        .doubleValue());
    return result;
  }

  private boolean isAutoCancelWarning(String orderStatus, Date autoCancelTimestamp) {
    if (orderStatus.equals("FP")) {
      Date currTime = new Date();
      if (autoCancelTimestamp != null) {
        return currTime.getTime() - autoCancelTimestamp.getTime() <= 0;
      }
      return false;
    } else {
      return false;
    }
  }

  private List<String> getFilterStatusList(String status) {
    return Stream.of(status.split(","))
        .map(String::trim)
        .map(Object::toString)
        .collect(Collectors.toList());
  }
}
