package com.project.cleancode.controller;

import com.project.cleancode.model.OrderElementListResponse;
import com.project.cleancode.model.OrderFilterRequest;
import com.project.cleancode.model.OrderListFilterVO;
import com.project.cleancode.service.OrderService;
import com.project.cleancode.service.util.OrderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class OrderController {

  @Autowired
  private OrderService<List<OrderElementListResponse>> orderService;

  @RequestMapping(value = {"/orders"},
      method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public List<OrderElementListResponse> getOrderList(@RequestParam String businessPartnerCode,
      @RequestParam String username,
      @ModelAttribute OrderFilterRequest filterRequest,
      @RequestParam(required = false, defaultValue = "0") Integer page,
      @RequestParam(required = false, defaultValue = "10") Integer size)
      throws Exception {
    OrderListFilterVO vo = new OrderListFilterVO();
    BeanUtils.copyProperties(filterRequest, vo,
        "orderDate", "filterStartDate", "filterEndDate");
    vo.setOrderDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse(filterRequest.getOrderDate()));
    vo.setFilterStartDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse(filterRequest.getFilterStartDate()));
    vo.setFilterEndDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse(filterRequest.getFilterEndDate()));
    vo.setFilterAutoCancelTimestamp(new SimpleDateFormat("yyyy-MM-dd")
        .parse(filterRequest.getFilterAutoCancelTimestamp()));
    vo.setLogistic(filterRequest.getLogisticCode());
    // Date range max difference = 30
    OrderUtil.validateFilterDateRange(vo.getFilterStartDate(), vo.getFilterEndDate(), 30);

    return orderService.filter(username, businessPartnerCode, page, size, vo);
  }
}
