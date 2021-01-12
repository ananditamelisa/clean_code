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

  public OrderItemSummaryRequest getFilterRequest(String merchantId,
      String username, OrderListFilterVO vo) {
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
    result.setOrderStatus(orderItemSummaryResponse.getOrderItemStatus());

    Optional<String> orderStatus =
        allowedOrderStatus.stream()
            .filter(status -> orderItemSummaryResponse.getOrderItemStatus().equals(status))
            .findFirst();
    if(orderStatus.isPresent()) {
      result.setOrderStatusString(orderStatus.get());
    } else {
      result.setOrderStatusString("-");
    }

    result.setProductName(orderItemSummaryResponse.getItemName());
    result.setProductPrice(orderItemSummaryResponse.getPrice());
    result.setDueDate(orderItemSummaryResponse.getAutoCancelTimestamp());
    result.setMerchantDeliveryType(orderItemSummaryResponse.getMerchantDeliveryType());
    result.setLogisticService(orderItemSummaryResponse.getLogisticsProductName());
    result.setLogisticProviderCode(orderItemSummaryResponse.getLogisticsProductCode());
    result.setLogisticsOptionName(orderItemSummaryResponse.getLogisticsOptionName());
    result.setLogisticsOptionCode(orderItemSummaryResponse.getLogisticsOptionCode());
    result.setMerchantSku(orderItemSummaryResponse.getMerchantSku());
    result.setProductTypeCode(orderItemSummaryResponse.getProductTypeCode());
    result.setProductTypeName(orderItemSummaryResponse.getProductTypeName());
    result.setLogisticsProductName(orderItemSummaryResponse.getLogisticsProductName());
    result.setPickupPointCode(orderItemSummaryResponse.getPickupPointCode());
    result.setItemSku(orderItemSummaryResponse.getItemSku());

    if(StringUtils.isBlank(orderItemSummaryResponse.getAwbNumber())) {
      result.setAwbNumber("-");
    } else {
      result.setAwbNumber(orderItemSummaryResponse.getAwbNumber());
    }

    result.setAwbStatus(
        StringUtils.isEmpty(orderItemSummaryResponse.getAwbNumber()) ? "-" : orderItemSummaryResponse.getAwbValidityStatus());
    result.setPaid(orderItemSummaryResponse.getIsMerchantPaid());
    result.setCustomerFullName(orderItemSummaryResponse.getCustomerFullName());
    result.setPickupPointName(orderItemSummaryResponse.getPickupPointName());
    result.setInstantPickup(orderItemSummaryResponse.isInstantPickup());
    result.setSettlementCodeExpired(orderItemSummaryResponse.isSettlementCodeExpired());
    result.setAutoCancelWarning(
        isAutoCancelWarning(orderItemSummaryResponse.getOrderItemStatus(),
            orderItemSummaryResponse.getAutoCancelTimestamp()));
    result.setReadyToProcessDate(orderItemSummaryResponse.getStatusFPUpdatedTimestamp());
    result.setPackageId(orderItemSummaryResponse.getPackageId());
    result.setPackageCreated(orderItemSummaryResponse.getPackageCreated());
    result.setFinalPrice(Optional.ofNullable(orderItemSummaryResponse.getPrice())
        .map(BigDecimal::valueOf).orElse(BigDecimal.ZERO)
        .subtract(Optional.ofNullable(orderItemSummaryResponse.getMerchantAdjustment())
            .map(BigDecimal::valueOf).orElse(BigDecimal.ZERO))
        .doubleValue());
    result.setOrderType(orderItemSummaryResponse.getOrderType());
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
