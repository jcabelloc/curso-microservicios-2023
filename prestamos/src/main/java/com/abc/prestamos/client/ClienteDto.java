package com.abc.prestamos.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClienteDto {

  private Integer codCliente;

  private String nombres;

  private String apellidoPaterno;

  private String apellidoMaterno;

  private Integer clasificacion;

}
// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.abc.prestamos.client.ClienteDto` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
/*
public interface ClienteDto {

  Integer getCodCliente();

  String getNombres();

  String getApellidoPaterno();

  String getApellidoMaterno();

  Integer getClasificacion();

}*/
