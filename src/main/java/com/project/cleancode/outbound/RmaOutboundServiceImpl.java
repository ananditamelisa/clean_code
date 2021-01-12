package com.project.cleancode.outbound;

import com.project.cleancode.enums.ApiOutboundCode;
import com.project.cleancode.model.RmaOutboundResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RmaOutboundServiceImpl implements RmaOutboundService {

  @Autowired
  private RestTemplateUtil restTemplateUtil;

  @Override
  public List<RmaOutboundResponse> filterFindRmaByMerchantCode(String businessPartnerCode,
      String rmaNumber,
      String orderIdItemId,
      String returDate,
      String rmaResolution,
      String status,
      int size,
      int page) {
    final Map<String, Object> additionalParameterMap = new HashMap<String, Object>();
    additionalParameterMap.put("merchantCode", businessPartnerCode);
    if (StringUtils.isNotBlank(rmaResolution)) {
      additionalParameterMap.put("rmaResolution", rmaResolution);
    }
    if (StringUtils.isNotBlank(status)) {
      additionalParameterMap.put("status", status);
    }
    if(StringUtils.isNotBlank(rmaNumber)) {
      additionalParameterMap.put("rmaNumber", rmaNumber);
    }
    if(StringUtils.isNotBlank(orderIdItemId)) {
      additionalParameterMap.put("orderIdItemId", orderIdItemId);
    }
    if(StringUtils.isNotBlank(returDate)) {
      additionalParameterMap.put("returDate", returDate);
    }
    additionalParameterMap.put("page", String.valueOf(page));
    additionalParameterMap.put("size", String.valueOf(size));

    ResponseEntity<List<RmaOutboundResponse>> response = restTemplateUtil.exchangeRequest(
        new ParameterizedTypeReference<List<RmaOutboundResponse>>() {},
        ApiOutboundCode.RMA_RETURN_GET_LIST_FILTER,
        additionalParameterMap,
        null, null);

    return response.getBody();
  }
}
