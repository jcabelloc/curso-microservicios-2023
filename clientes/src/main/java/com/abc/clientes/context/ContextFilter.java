package com.abc.clientes.context;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContextFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    ContextHolder.getContext().setIdCorrelativo(httpServletRequest.getHeader(Context.ID_CORRELATIVO) );

    logger.debug("id-correlativo: {}", ContextHolder.getContext().getIdCorrelativo());

    filterChain.doFilter(httpServletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
