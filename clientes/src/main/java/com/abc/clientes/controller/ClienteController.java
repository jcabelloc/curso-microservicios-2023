package com.abc.clientes.controller;

import com.abc.clientes.context.ContextHolder;
import com.abc.clientes.model.ClienteDto;
import com.abc.clientes.model.ClienteReq;
import com.abc.clientes.properties.PropertiesConfig;
import com.abc.clientes.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

  @Autowired
  PropertiesConfig propConfig;

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
    logger.debug("id-correlativo: {}", ContextHolder.getContext().getIdCorrelativo());


    var auth =  SecurityContextHolder.getContext().getAuthentication();
    if (auth instanceof JwtAuthenticationToken) {
      var jwtAuthToken =  (JwtAuthenticationToken) auth;
      logger.debug("Valor del JwtToken: {}", jwtAuthToken.getToken().getTokenValue());
    }
    logger.debug("Datos del auth.getPrincipal(): {}", auth.getPrincipal());
    logger.debug("Datos del auth.getName(): {}", auth.getName());
    logger.debug("Datos del Authorities {}", auth.getAuthorities());
    logger.debug("Esta autenticado {}", auth.isAuthenticated());

    return clienteService.getAll();
  }

  @GetMapping("/{codCliente}")
  public ClienteDto getDtoById(@PathVariable Integer codCliente) {
    logger.debug("Obteniendo Cliente con codCliente {}", codCliente);
    logger.debug("id-correlativo: {}", ContextHolder.getContext().getIdCorrelativo());

    // Simulacion de Falla
    double valor = Math.random() * 100;
    logger.warn("Tasa de errores configurado {} ", propConfig.getTasaErrores());
    if (valor < propConfig.getTasaErrores()) {
      logger.error("Error al obtener Cliente {} ", codCliente);
      throw new RuntimeException("Error al Obtener cliente");
    }


    return clienteService.getDtoById(codCliente);
  }

}
