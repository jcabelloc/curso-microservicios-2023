package com.abc.prestamos.controller;
import com.abc.prestamos.model.PrestamoDto;
import com.abc.prestamos.model.PrestamoReq;
import com.abc.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
  private static final Logger logger = LoggerFactory.getLogger(PrestamoController.class);
  @Autowired
  PrestamoService prestamoService;
  @PostMapping
  public Integer create(@Valid @RequestBody PrestamoReq prestamoReq, BindingResult result) {
    logger.debug("Creando un Prestamo {}", prestamoReq);
    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    return prestamoService.create(prestamoReq).getNroPrestamo();
  }
  @PutMapping("/{nroPrestamo}")
  public void update(@PathVariable Integer nroPrestamo, @Valid @RequestBody PrestamoReq prestamoReq,
      BindingResult result) {
    logger.debug("Actualizando Prestamo con id {} y con data {}", nroPrestamo, prestamoReq);
    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    prestamoService.update(nroPrestamo, prestamoReq);
  }
  @GetMapping
  public List<PrestamoDto> getAll() {
    logger.debug("Obteniendo Prestamos");
    return prestamoService.getAll();
  }
  @GetMapping("/{nroPrestamo}")
  public PrestamoDto getDtoById(@PathVariable Integer nroPrestamo) {
    logger.debug("Obteniendo Prestamo con nroPrestamo {}", nroPrestamo);
    return prestamoService.getDtoById(nroPrestamo);
  }
}