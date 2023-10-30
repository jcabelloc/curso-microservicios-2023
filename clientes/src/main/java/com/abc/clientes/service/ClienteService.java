package com.abc.clientes.service;

import com.abc.clientes.entity.Cliente;
import com.abc.clientes.model.ClienteDto;
import com.abc.clientes.model.ClienteReq;
import java.util.List;

public interface ClienteService {

  Cliente create(ClienteReq req);

  List<ClienteDto> getAll();

  Cliente getById(Integer codCliente);

  Cliente update(Integer id, ClienteReq req);

  ClienteDto getDtoById(Integer codCliente);
}
