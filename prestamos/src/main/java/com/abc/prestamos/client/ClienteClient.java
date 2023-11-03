package com.abc.prestamos.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "clientes")
public interface ClienteClient {


  Logger logger = LoggerFactory.getLogger(ClienteClient.class);

  @RequestMapping(
      method= RequestMethod.GET,
      value="/clientes/{codCliente}",
      consumes="application/json")
  @CircuitBreaker(name = "clientes", fallbackMethod = "getClienteFallback")  // <== Corresponde a la instancia Circuit Breaker del Config
  @Retry(name = "clientes")
  ClienteDto getCliente(@PathVariable("codCliente") Integer codCliente);


  default ClienteDto getClienteFallback(Integer codCliente, Exception e) {
    logger.info("Obteniendo al cliente {} mediante fallback", codCliente);
    return ClienteDto
        .builder()
        .codCliente(codCliente)
        .nombres("--")
        .apellidoPaterno("--")
        .apellidoMaterno("--")
        .clasificacion(0)
        .build();
  }

}
