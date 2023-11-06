package com.abc.gateway.service.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {

  private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

  @Autowired
  FilterUtils filterUtils;

  @Bean
  public GlobalFilter postGlobalFilter() {
    return (exchange, chain) -> {
      return chain.filter(exchange).then(
          Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            String idCorrelativo = filterUtils.getIdCorrelativo(requestHeaders);
            logger.debug("Agregando el id-correlativo al response header. {}", idCorrelativo);
            exchange.getResponse().getHeaders().add(FilterUtils.ID_CORRELATIVO, idCorrelativo);
            logger.debug("Completando el request para {}.", exchange.getRequest().getURI());
      }));
    };
  }


}
