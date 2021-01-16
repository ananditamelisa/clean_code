package com.project.cleancode.service;

import com.project.cleancode.model.OrderElementListResponse;
import com.project.cleancode.model.OrderItemSummaryRequest;
import com.project.cleancode.model.OrderItemSummaryResponse;
import com.project.cleancode.model.OrderListFilterVO;
import com.project.cleancode.outbound.OrderOutboundService;
import com.project.cleancode.service.util.OrderComponentUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;
  @Mock
  private OrderComponentUtil orderComponentUtil;
  @Mock
  @Qualifier(value =  "orderOutboundService")
  private OrderOutboundService orderOutboundService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(orderOutboundService, orderComponentUtil);
  }

  @Test
  public void filterTest_HappyFlow() throws ParseException {
    OrderListFilterVO vo = this.orderListFilterVOBuilder();
    OrderItemSummaryRequest request = this.orderItemSummaryRequestBuilder();
    List<OrderItemSummaryResponse> responses = this.orderItemSummaryResponseListBuilder();
    Mockito.when(orderComponentUtil.getFilterRequest("dummy-merchant-id", vo))
        .thenReturn(request);
    Mockito.when(orderOutboundService.findOrderItemSummaryByFilter(request,
        "orderCreatedTimestamp", "desc", 0, 10)).thenReturn(responses);
    Mockito.when(orderComponentUtil.mapToOrderListResponse(responses.get(0)))
        .thenReturn(this.orderElementListResponseBuilder());

    List<OrderElementListResponse> result =
        orderService.filter("dummy-merchant-id", 0, 10, vo);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
    Assert.assertEquals("dummy-item-sku", result.get(0).getItemSku());

    Mockito.verify(orderComponentUtil).getFilterRequest("dummy-merchant-id", vo);
    Mockito.verify(orderOutboundService).findOrderItemSummaryByFilter(request,
        "orderCreatedTimestamp", "desc", 0, 10);
    Mockito.verify(orderComponentUtil).mapToOrderListResponse(responses.get(0));
  }

  private OrderListFilterVO orderListFilterVOBuilder() throws ParseException {
    return OrderListFilterVO.builder()
        .status("dummy-status-1, dummy-status-2")
        .orderDate(new SimpleDateFormat("yyyy-MM-dd")
            .parse("2020-10-15"))
        .instantPickup(false)
        .pickupPointCode("dummy-pickup-point-code")
        .orderBy("orderCreatedTimestamp")
        .sortBy("desc")
        .build();
  }

  private OrderItemSummaryRequest orderItemSummaryRequestBuilder() throws ParseException {
    List<String> orderItemStatus = Arrays.asList("dummy-status-1", "dummy-status-2");
    return OrderItemSummaryRequest.builder()
        .orderItemStatus(orderItemStatus)
        .merchantCode("dummy-merchant-id")
        .instantPickup(false)
        .pickupPointCode("dummy-pickup-point-code")
        .orderCreatedTimestamp(new SimpleDateFormat("yyyy-MM-dd")
            .parse("2020-10-15"))
        .build();
  }

  private List<OrderItemSummaryResponse> orderItemSummaryResponseListBuilder() {
    OrderItemSummaryResponse response = OrderItemSummaryResponse
        .builder()
        .orderItemId("dummy-order-item-id")
        .orderId("dummy-order-id")
        .itemName("dummy-item-name")
        .itemSku("dummy-item-sku")
        .orderItemStatus("DELIVERED")
        .build();
    return Collections.singletonList(response);
  }

  private OrderElementListResponse orderElementListResponseBuilder(){
    return OrderElementListResponse.builder()
        .orderItemNo("dummy-order-item-id")
        .itemSku("dummy-item-sku")
        .orderStatus("DELIVERED")
        .build();
  }
}
