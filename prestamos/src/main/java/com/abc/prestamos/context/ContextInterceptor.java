package com.abc.prestamos.context;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class ContextInterceptor {
  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header(Context.ID_CORRELATIVO, ContextHolder.getContext().getIdCorrelativo());
      var jwtAuthToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
      requestTemplate.header(Context.AUTHORIZATION, String.format("Bearer %s", jwtAuthToken.getToken().getTokenValue()));
    };
  }

}
