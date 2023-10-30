package com.abc.clientes.service;

import com.abc.clientes.entity.Cliente;
import com.abc.clientes.model.ClienteDto;
import com.abc.clientes.model.ClienteReq;
import com.abc.clientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  ClienteRepository clienteRepository;


  @Override
  public Cliente create(ClienteReq req) {
    Cliente cliente = Cliente
        .builder()
        .nombres(req.getNombres())
        .apellidoPaterno(req.getApellidoPaterno())
        .apellidoMaterno(req.getApellidoMaterno())
        .clasificacion(req.getClasificacion())
        .build();
    return clienteRepository.save(cliente);
  }

  @Override
  public List<ClienteDto> getAll() {
    return clienteRepository.getAll();
  }

  @Override
  public Cliente update(Integer id, ClienteReq req) {
    Cliente cliente = getById(id);
    cliente.setNombres(req.getNombres());
    cliente.setApellidoPaterno(req.getApellidoPaterno());
    cliente.setApellidoMaterno(req.getApellidoMaterno());
    cliente.setClasificacion(req.getClasificacion());
    return clienteRepository.save(cliente);
  }

  @Override
  public ClienteDto getDtoById(Integer codCliente) {
    Optional<ClienteDto> opt = clienteRepository.findDtoByCodCliente(codCliente);
    return opt.orElseThrow(() -> new RuntimeException("No existe tal cliente"));
  }

  @Override
  public Cliente getById(Integer codCliente) {
    Optional<Cliente> opt = clienteRepository.findById(codCliente);
    return opt.orElseThrow(() -> new RuntimeException("No existe tal cliente"));
  }
}
