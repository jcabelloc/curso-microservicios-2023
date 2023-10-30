package com.abc.clientes.controller;

import com.abc.clientes.model.ClienteDto;
import com.abc.clientes.model.ClienteReq;
import com.abc.clientes.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

  @Autowired
  ClienteService clienteService;

  @PostMapping
  public Integer create(@Valid @RequestBody ClienteReq clienteReq, BindingResult result) {
    logger.debug("Creando un Cliente {}", clienteReq);

    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    return clienteService.create(clienteReq).getCodCliente();
  }

  @PutMapping("/{codCliente}")
  public void update(@PathVariable Integer codCliente, @Valid @RequestBody ClienteReq clienteReq,
      BindingResult result) {
    logger.debug("Actualizando Cliente con id {} y con data {}", codCliente, clienteReq);
    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    clienteService.update(codCliente, clienteReq);
  }

  @GetMapping
  public List<ClienteDto> getAll() {
    logger.debug("Obteniendo Clientes");

    return clienteService.getAll();
  }

  @GetMapping("/{codCliente}")
  public ClienteDto getDtoById(@PathVariable Integer codCliente) {
    logger.debug("Obteniendo Cliente con codCliente {}", codCliente);
    return clienteService.getDtoById(codCliente);
  }

}
