package com.abc.prestamos.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer nroPrestamo;

  private Integer codCliente;

  private BigDecimal montoDesembolso;

  private BigDecimal tea;

  private Integer nroCuotas;

  @Enumerated(EnumType.STRING)
  private Frecuencia frecuencia;

  public enum Frecuencia { MENSUAL, SEMANAL }
}