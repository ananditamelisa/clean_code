package com.project.cleancode.controller;

import com.project.cleancode.model.OrderElementListResponse;
import com.project.cleancode.model.OrderListFilterVO;
import com.project.cleancode.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OrderControllerTest {

  @InjectMocks
  private OrderController orderController;
  @Mock
  private OrderService<List<OrderElementListResponse>> orderService;

  private MockMvc mockMvc;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = standaloneSetup(this.orderController).build();
  }

  @Test
  public void getOrderList_HappyFlow_Success() throws Exception {
    OrderListFilterVO orderListFilterVO = createOrderListFilterVO();
    List<OrderElementListResponse> responses =
        Collections.singletonList(createOrderListResponse());

    when(orderService.filter(eq("dummy-bpcode"), eq(0), eq(10),
        eq(orderListFilterVO))).thenReturn(responses);

    mockMvc.perform(get("/orders")
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .contentType(MediaType.APPLICATION_JSON)
        .param("storeId", "10001")
        .param("requestId", "dummy-request-id")
        .param("businessPartnerCode", "dummy-bpcode")
        .param("orderDate", "2018-10-15")
        .param("filterStartDate", "2018-10-12")
        .param("filterEndDate", "2018-10-15")
        .param("status", "dummy-status")
        .param("size", String.valueOf(10))
        .param("page", String.valueOf(0)))
        .andExpect(status().isOk());

    verify(orderService, times(1))
        .filter(eq("dummy-bpcode"), eq(0), eq(10), eq(orderListFilterVO));
  }

  private OrderElementListResponse createOrderListResponse() throws ParseException {
    return OrderElementListResponse.builder()
        .orderDate(new SimpleDateFormat("yyyy-MM-dd")
            .parse("2018-10-15"))
        .orderItemNo("dummy-order-item-id")
        .itemSku("dummy-item-sku")
        .build();
  }

  private OrderListFilterVO createOrderListFilterVO() throws ParseException {
    OrderListFilterVO orderListFilterVO = new OrderListFilterVO();
    orderListFilterVO.setOrderDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse("2018-10-15 00:00:00"));
    orderListFilterVO.setFilterStartDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse("2018-10-12"));
    orderListFilterVO.setFilterEndDate(new SimpleDateFormat("yyyy-MM-dd")
        .parse("2018-10-15"));
    orderListFilterVO.setStatus("dummy-status");
    return orderListFilterVO;
  }
}
