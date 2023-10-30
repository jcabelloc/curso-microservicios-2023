package com.abc.clientes.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClienteReq {

  private Integer codCliente;

  @NotNull(message = "El/Los nombres es/son requeridos")
  private String nombres;

  @NotNull(message = "El apellido paterno es requerido")
  private String apellidoPaterno;

  private String apellidoMaterno;

  private Integer clasificacion;

}
