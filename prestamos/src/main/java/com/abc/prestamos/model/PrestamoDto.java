package com.abc.prestamos.model;
import com.abc.prestamos.entity.Prestamo;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PrestamoDto {

  private final Integer nroPrestamo;

  private final Integer codCliente;

  private final BigDecimal montoDesembolso;

  private final BigDecimal tea;

  private final Integer nroCuotas;

  private final Prestamo.Frecuencia frecuencia;

  private String nombreCliente;

  private Integer clasificacionCliente;

}