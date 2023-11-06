package com.abc.prestamos.context;

public class Context {

  public static final String ID_CORRELATIVO = "id-correlativo";
  private String idCorrelativo = new String();

  public String getIdCorrelativo() {
    return idCorrelativo;
  }

  public void setIdCorrelativo(String idCorrelativo) {
    this.idCorrelativo = idCorrelativo;
  }
}
