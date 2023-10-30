-- -----------------------------------------------------
-- CARGA INICIAL DE DATA DE PRUEBA
-- -----------------------------------------------------

-- VARIABLES
SET @FECHA= SYSDATE() + INTERVAL 1 DAY;

-- PRESTAMOS
INSERT INTO `cliente` (`cod_cliente`, `nombres`, `apellido_paterno`, `apellido_materno`, `clasificacion`) VALUES
(20001, 'Juan', 'Perez', 'Lopez', 0),
(20002, 'Maria', 'Arteaga', 'Jimenez', 1),
(20003, 'Jose', 'Aguirre', 'Tello', null),
(20004, 'Julia', 'Torres', 'Lozada', 2),
(20005, 'Sergio', 'Vilchez', 'Gamarra', 0);