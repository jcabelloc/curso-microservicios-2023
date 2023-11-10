


### Creacion de Esquemas de BD
```
DROP SCHEMA IF EXISTS `clientes`;
CREATE SCHEMA IF NOT EXISTS `clientes` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;

USE `clientes`;
CREATE USER IF NOT EXISTS 'clientes' identified by 'secreto';
GRANT ALL PRIVILEGES ON `clientes`.* TO 'clientes'@'%';
FLUSH PRIVILEGES;

DROP SCHEMA IF EXISTS `prestamos`;
CREATE SCHEMA IF NOT EXISTS `prestamos` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;

USE `prestamos`;
CREATE USER IF NOT EXISTS 'prestamos' identified by 'secreto';
GRANT ALL PRIVILEGES ON `prestamos`.* TO 'prestamos'@'%';
FLUSH PRIVILEGES;
```

### Generar Imagenes
#### Opcion 1) Usando Dockerfile
``` 
mvn clean package
docker build -t jcabelloc/prestamos .
docker run -p 8080:8080 jcabelloc/prestamos 
```

``` 
mvn clean package
docker build -t jcabelloc/clientes .
docker run -p 8080:8080 jcabelloc/clientes

```

#### Opcion 2) Usando BuildPacks
```
mvn spring-boot:build-image 
mvn spring-boot:build-image -DskipTests
```


### Docker Compose
```
docker compose up
docker compose down
```

### Configuracion Service

```
export CONFIG_HOME=~/workspaces/cursos/curso-microservicios/base/config
set CONFIG_HOME='C:/'
```

```
export ENCRYPT_KEY=clavesupersupercreta
set ENCRYPT_KEY=clavesupersupercreta
```


## Keycloak
### Iniciar Keycloak con Docker
* Por Primera Vez
```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.1 start-dev
```
* Las siguientes veces. (happy_kepler es el nombre del contenedor que le fue asignado en automatico)
```
docker start happy_kepler
```
### Client ID and Secret
```
portal-abc:acbjKWmR0RHCTuSDiQJVrXQRSazYZ1Ec
```
### Keycloak: Authorization Code
* Desde el Navegador ingresar a este URL.
```
http://localhost:8080/realms/abc-realm/protocol/openid-connect/auth?client_id=portal-abc&response_type=code&scope=openid&redirect_uri=http://localhost:4200/auth
```
* En la opcion de Login, ingresar las credenciales del usuario. Una vez autenticado, se nos redigira a un URL como el siguiente, que incluye el code(authorization code)
```
http://localhost:4200/auth?session_state=4f6126de-1fdc-452a-b6a1-9bc3d322d4d7&code=4bbf9f13-6a40-4cbb-bd14-bacaeb52bea3.4f6126de-1fdc-452a-b6a1-9bc3d322d4d7.25c7bb91-d855-4947-895d-dbcaf900dad2
```
* Usar dicho "code" en Postman para solicitar el token



### Spring Authorization Server
* Para obtener el Authorization Code, desde el Browser
```
http://localhost:8080/oauth2/authorize?response_type=code&client_id=portal-abc&scope=openid&redirect_uri=http://localhost:4200/auth
```
* Obtener el "code", y usarlo para obtener el token(postman)