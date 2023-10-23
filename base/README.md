


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