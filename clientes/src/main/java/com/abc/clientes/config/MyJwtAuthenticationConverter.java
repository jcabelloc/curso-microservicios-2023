package com.abc.clientes.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.CollectionUtils;

public class MyJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private static final Logger logger = LoggerFactory.getLogger(MyJwtAuthenticationConverter.class);

  @Override
  public JwtAuthenticationToken convert(Jwt jwt) {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    //grantedAuthoritiesConverter.setAuthorityPrefix("");
    Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);
    String username = getUsernameFrom(jwt);
    logger.info("username obtenido desde el jwt: {}", username);

    if (jwt.hasClaim("azp")) {
      var clientId = jwt.getClaim("azp").toString();
      var clientRoles = getClientRolesFrom(jwt, clientId);
      authorities.addAll(clientRoles.stream()
          .map(SimpleGrantedAuthority::new)
          .toList());
    }
    return new JwtAuthenticationToken(jwt, authorities, username);
  }

  protected String getUsernameFrom(Jwt jwt) {
    if (jwt.hasClaim("preferred_username")) {
      return jwt.getClaimAsString("preferred_username");
    }
    return jwt.getSubject();
  }

  protected Set<String> getClientRolesFrom(Jwt jwt, String clientId) {

    Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");

    if (CollectionUtils.isEmpty(resourceAccess)) {
      return Collections.emptySet();
    }
    logger.debug("resourceAccess: {}", resourceAccess);

    @SuppressWarnings("unchecked")
    Map<String, List<String>> clientAccess = (Map<String, List<String>>) resourceAccess.get(clientId);

    if (CollectionUtils.isEmpty(clientAccess)) {
      return Collections.emptySet();
    }

    List<String> clientRoles = clientAccess.get("roles");
    if (CollectionUtils.isEmpty(clientRoles)) {
      return Collections.emptySet();
    }

    return clientRoles.stream().map("ROLE_"::concat).collect(Collectors.toSet());
  }

}
