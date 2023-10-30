-- -----------------------------------------------------
-- CARGA INICIAL DE DATA DE PRUEBA
-- -----------------------------------------------------

-- VARIABLES
SET @FECHA= SYSDATE() + INTERVAL 1 DAY;

-- PRESTAMOS
INSERT INTO `prestamo` (`nro_prestamo`, `cod_cliente`, `monto_desembolso`, `tea`, `nro_cuotas`, `frecuencia`) VALUES
(10001, 20001, 25000.00, 25.82, 12, 'MENSUAL'),
(10002, 20002, 10000.00, 28.10, 6, 'MENSUAL'),
(10003, 20003, 35000.00, 31.20, 18, 'SEMANAL'),
(10004, 20004, 18000.00, 26.10, 24, 'MENSUAL'),
(10005, 20005, 13500.00, 27.40, 8, 'SEMANAL');
