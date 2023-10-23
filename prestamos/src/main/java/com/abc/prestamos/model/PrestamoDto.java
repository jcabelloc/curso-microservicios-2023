package com.abc.prestamos.model;
import com.abc.prestamos.entity.Prestamo;
import java.math.BigDecimal;
public interface PrestamoDto {
  Integer getNroPrestamo();
  BigDecimal getMontoDesembolso();
  BigDecimal getTea();
  BigDecimal getNroCuotas();
  Prestamo.Frecuencia getFrecuencia();
}