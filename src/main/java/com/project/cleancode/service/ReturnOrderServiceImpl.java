package com.project.cleancode.service;

import com.project.cleancode.enums.ReturnResolutionType;
import com.project.cleancode.enums.ReturnStatus;
import com.project.cleancode.model.RmaOutboundResponse;
import com.project.cleancode.model.ReturnOrderResponse;
import com.project.cleancode.outbound.RmaOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnOrderServiceImpl implements OrderService<List<ReturnOrderResponse>> {

  @Autowired
  private RmaOutboundService rmaOutboundService;

  @Override
  public List<ReturnOrderResponse> filter(Object... args) throws Exception {
    String businessPartnerCode = (String) args[0];
    String rmaNumber = (String) args[1];
    String orderIdOrItemId = (String) args[2];
    String returDate = (String) args[3];
    String rmaResolution = (String) args[4];
    String status = (String) args[5];
    Integer size = (Integer) args[6];
    Integer page = (Integer) args[7];
    List<RmaOutboundResponse> outboundResponses =
        rmaOutboundService.filterFindRmaByMerchantCode(businessPartnerCode, rmaNumber,
            orderIdOrItemId, returDate, rmaResolution, status, size, page);
    return convertReturnOrder(outboundResponses);
  }

  private List<ReturnOrderResponse> convertReturnOrder(List<RmaOutboundResponse> data) {
    List<ReturnOrderResponse> returns = new ArrayList<ReturnOrderResponse>();
    for (RmaOutboundResponse returnData : data) {
      ReturnOrderResponse returnItem = new ReturnOrderResponse();
      returnItem.setReturnNo(returnData.getRmaNumber());
      returnItem.setOrderNo(returnData.getOldOrderInformation().getOldOrderId());
      returnItem.setOrderItemNo(returnData.getOldOrderInformation().getOldOrderItemId());
      returnItem.setOrderQuantity(returnData.getQuantity());
      returnItem.setGdnSku(returnData.getOldOrderInformation().getSkuCode());
      returnItem.setProductName(returnData.getOldOrderInformation().getSkuName());
      returnItem.setProductSalePrice(returnData.getOldOrderInformation().getProductPrice());
      returnItem.setReason(returnData.getReason());
      if (returnData.getStatus() != null) {
        returnItem.setReturnStatusCode(returnData.getStatus());
        returnItem
            .setReturnStatus(ReturnStatus.valueOf(returnData.getStatus()).getMessage());
      }
      if (returnData.getRmaResolution() != null &&
          returnData.getRmaResolution().getRmaResolutionType() != null) {
        returnItem.setReturnResolution(ReturnResolutionType
            .valueOf(returnData.getRmaResolution().getRmaResolutionType().name()).getMessage());
      }
      returns.add(returnItem);
    }
    return returns;
  }
}
