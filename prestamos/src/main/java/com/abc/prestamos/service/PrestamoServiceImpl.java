package com.abc.prestamos.service;
import com.abc.prestamos.client.ClienteClient;
import com.abc.prestamos.client.ClienteDto;
import com.abc.prestamos.entity.Prestamo;
import com.abc.prestamos.model.PrestamoDto;
import com.abc.prestamos.model.PrestamoReq;
import com.abc.prestamos.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {
  @Autowired
  PrestamoRepository prestamoRepository;

  @Autowired
  ClienteClient clienteClient;
  @Override
  public PrestamoDto getDtoById(Integer nroPrestamo) {
    Optional<PrestamoDto> opt = prestamoRepository.findDtoByNroPrestamo(nroPrestamo);
    PrestamoDto prestamo = opt.orElseThrow(() -> new RuntimeException("No existe tal prestamo"));

    ClienteDto cliente = clienteClient.getCliente(prestamo.getCodCliente());
    prestamo.setNombreCliente(cliente.getNombres() + " " + cliente.getApellidoPaterno());
    prestamo.setClasificacionCliente(cliente.getClasificacion());

    return prestamo;
  }

  @Override
  public Prestamo create(PrestamoReq req) {
    Prestamo prestamo = Prestamo
        .builder()
        .montoDesembolso(req.getMontoDesembolso())
        .tea(req.getTea())
        .nroCuotas(req.getNroCuotas())
        .frecuencia(req.getFrecuencia())
        .build();
    return prestamoRepository.save(prestamo);
  }
  @Override
  public List<PrestamoDto> getAll() {
    return prestamoRepository.getAll();
  }
  @Override
  public Prestamo update(Integer nroPrestamo, PrestamoReq req) {
    Prestamo prestamo = getById(nroPrestamo);
    prestamo.setMontoDesembolso(req.getMontoDesembolso());
    prestamo.setTea(req.getTea());
    prestamo.setFrecuencia(req.getFrecuencia());
    prestamo.setNroCuotas(req.getNroCuotas());
    return prestamoRepository.save(prestamo);
  }
  @Override
  public Prestamo getById(Integer nroPrestamo) {
    Optional<Prestamo> opt = prestamoRepository.findById(nroPrestamo);
    return opt.orElseThrow(() -> new RuntimeException("No existe tal prestamo"));
  }
}