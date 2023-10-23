-- -----------------------------------------------------
-- CARGA INICIAL DE DATA DE PRUEBA
-- -----------------------------------------------------
-- VARIABLES
SET @FECHA= SYSDATE() + INTERVAL 1 DAY;
-- PRESTAMOS
INSERT INTO `prestamo` (`nro_prestamo`, `monto_desembolso`, `tea`, `nro_cuotas`, `frecuencia`) VALUES
(10001, 25000.00, 25.82, 12, 'MENSUAL'),
(10002, 10000.00, 28.10, 6, 'MENSUAL'),
(10003, 35000.00, 31.20, 18, 'SEMANAL'),
(10004, 18000.00, 26.10, 24, 'MENSUAL'),
(10005, 13500.00, 27.40, 8, 'SEMANAL');