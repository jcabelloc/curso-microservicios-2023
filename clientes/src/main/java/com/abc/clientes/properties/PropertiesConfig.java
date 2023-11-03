package com.abc.clientes.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "propiedad")
public class PropertiesConfig {


  @Setter
  private Integer tasaErrores;

  public Integer getTasaErrores() {
    return tasaErrores != null ? tasaErrores : 0;
  }

}
