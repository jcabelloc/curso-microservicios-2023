package com.abc.prestamos.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "clientes")
public interface ClienteClient {
  @RequestMapping(
      method= RequestMethod.GET,
      value="/clientes/{codCliente}",
      consumes="application/json")
  ClienteDto getCliente(@PathVariable("codCliente") Integer codCliente);

}
