


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