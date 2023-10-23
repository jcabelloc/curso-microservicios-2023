package com.abc.prestamos.service;
import com.abc.prestamos.entity.Prestamo;
import com.abc.prestamos.model.PrestamoDto;
import com.abc.prestamos.model.PrestamoReq;
import java.util.List;
public interface PrestamoService {
  Prestamo create(PrestamoReq prestamoReq);
  List<PrestamoDto> getAll();
  public Prestamo getById(Integer nroPrestamo);
  Prestamo update(Integer id, PrestamoReq prestamoReq);
  PrestamoDto getDtoById(Integer nroPrestamo);
}