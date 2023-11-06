package com.abc.gateway.service.filters;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtils {

  public static final String ID_CORRELATIVO = "id-correlativo";

  public String getIdCorrelativo(HttpHeaders requestHeaders){
    if (requestHeaders.get(ID_CORRELATIVO) !=null) {
      List<String> header = requestHeaders.get(ID_CORRELATIVO);
      return header.stream().findFirst().get();
    }
    else{
      return null;
    }
  }

  public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
    return exchange.mutate().request(
            exchange.getRequest().mutate()
                .header(name, value)
                .build())
        .build();
  }

  public ServerWebExchange setIdCorrelativo(ServerWebExchange exchange, String correlationId) {
    return this.setRequestHeader(exchange, ID_CORRELATIVO, correlationId);
  }
}
