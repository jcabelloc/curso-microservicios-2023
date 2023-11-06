package com.abc.clientes.context;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextInterceptor {
  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header(Context.ID_CORRELATIVO, ContextHolder.getContext().getIdCorrelativo());
    };
  }

}
