package com.project.cleancode.outbound;

import com.project.cleancode.config.OrderServiceConfig;
import com.project.cleancode.config.RmaServiceConfig;
import com.project.cleancode.enums.ApiErrorCode;
import com.project.cleancode.enums.ApiOutboundCode;
import com.project.cleancode.exception.ApiOutboundException;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class RestTemplateUtil {

  @Autowired
  private BeanFactory beanFactory;

  public <T> ResponseEntity<T> exchangeRequest(ParameterizedTypeReference<T> responseType,
      ApiOutboundCode outboundCode, Map<String, Object> additionalParams,
      Map<String, String> uriVariables, Object body) {
    RestTemplate restTemplate = getRestTemplate(outboundCode);

    URI uri = this.filterAndBuildUri(outboundCode, additionalParams, uriVariables);
    HttpEntity<Object> requestEntity = new HttpEntity<>(body);
    ResponseEntity<T> response = restTemplate.exchange(uri,
        HttpMethod.valueOf(outboundCode.getRequestMethod()), requestEntity, responseType);
    return response;
  }

  private URI filterAndBuildUri(ApiOutboundCode outboundCode, Map<String, Object> additionalParams,
      Map<String, String> uriVariables) {
    String path = replaceUriVariables(outboundCode.getPath(),
        Optional.ofNullable(uriVariables).orElseGet(HashMap::new));
    try {
      switch(outboundCode.getService()){
        case ORDER:
          OrderServiceConfig orderConfig =
              (OrderServiceConfig) beanFactory.getBean(outboundCode.getService().getUriConfig());
          return this.generateUri(orderConfig.getHost(), orderConfig.getContext() + path,
             orderConfig.getPort(), additionalParams);
        case RMA:
          RmaServiceConfig rmaConfig =
              (RmaServiceConfig) beanFactory.getBean(outboundCode.getService().getUriConfig());
          return this.generateUri(rmaConfig.getHost(),
              rmaConfig.getContext() + path, rmaConfig.getPort(), additionalParams);
        default:
          throw new ApiOutboundException(ApiErrorCode.BASE_OUTBOUND_URI_CONFIG_NOT_FOUND,
              ApiErrorCode.BASE_OUTBOUND_URI_CONFIG_NOT_FOUND.getDesc() + ": "
                  + outboundCode.getService().name(), null);
      }
    } catch (Exception e) {
      throw new ApiOutboundException();
    }
  }

  private URI generateUri(String host, String path, int port, Map<String, Object> additionalParams)
      throws Exception {
    URI uri = new URIBuilder(host)
        .setPort(port)
        .setPath(path)
        .build();

    URIBuilder uriBuilder = new URIBuilder(uri);
    Optional.ofNullable(additionalParams)
        .orElseGet(HashMap::new)
        .entrySet()
        .stream()
        .filter(entry ->
            Objects.nonNull(entry.getValue()))
        .forEach(entry ->
            uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue())));

    return uriBuilder.build();
  }

  private String replaceUriVariables(String path, Map<String, String> uriVariables) {
    for (Map.Entry<String, String> entry : uriVariables.entrySet()) {
      path = path.replace(String.format("{%s}", entry.getKey()), entry.getValue());
    }
    return path;
  }

  private RestTemplate getRestTemplate(ApiOutboundCode outboundCode) {
    RestTemplate restTemplate;
    try {
      restTemplate = beanFactory.getBean(outboundCode.getService().getRestTemplateName(),
          RestTemplate.class);
    } catch (Exception e) {
      throw e;
    }
    return restTemplate;
  }
}
