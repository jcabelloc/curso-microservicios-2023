package com.abc.gateway.service.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

  private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

  @Autowired
  FilterUtils filterUtils;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
    if (existeIdCorrelativo(requestHeaders)) {
      logger.debug("id-correlativo encontrado en el TrackingFilter: {}. ",
          filterUtils.getIdCorrelativo(requestHeaders));
    } else {
      String idCorrelativo = generarIdCorrelativo();
      exchange = filterUtils.setIdCorrelativo(exchange, idCorrelativo);
      logger.debug("id-correlativo generado en el TrackingFilter: {}.", idCorrelativo);
    }

    return chain.filter(exchange);
  }


  private boolean existeIdCorrelativo(HttpHeaders requestHeaders) {
    if (filterUtils.getIdCorrelativo(requestHeaders) != null) {
      return true;
    } else {
      return false;
    }
  }

  private String generarIdCorrelativo() {
    return java.util.UUID.randomUUID().toString();
  }
}
