package com.project.cleancode.controller;

import com.project.cleancode.model.ReturnOrderResponse;
import com.project.cleancode.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ReturnOrderController {

  @Autowired
  private OrderService<List<ReturnOrderResponse>> orderService;

  @RequestMapping(value = {"/returnOrders"},
      method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public List<ReturnOrderResponse> getReturnedOrder(
      @RequestParam String businessPartnerCode,
      @RequestParam(required = false) String rmaNumber,
      @RequestParam(required = false) String orderIdOrItemId,
      @RequestParam(required = false) String returDate,
      @RequestParam(required = false) String rmaResolution,
      @RequestParam(required = false) String status,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "0") Integer page) throws Exception {
    return orderService.filter(businessPartnerCode, rmaNumber, orderIdOrItemId, returDate,
        rmaResolution, status, size, page);
  }
}
