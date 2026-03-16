DROP DATABASE IF EXISTS logitrackdb;
CREATE DATABASE logitrackdb;
USE logitrackdb;

SET FOREIGN_KEY_CHECKS = 0;

-- =========================
-- TABLA PERSONA
-- =========================
CREATE TABLE persona (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  documento VARCHAR(100) NOT NULL,
  correo VARCHAR(255) NOT NULL,
  telefono VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- TABLA EMPLEADO
-- =========================
CREATE TABLE empleado (
  id BIGINT NOT NULL,
  rol VARCHAR(50) NOT NULL,
  usuario VARCHAR(100) NOT NULL,
  contrasena VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fkEmpleadoPersona 
  FOREIGN KEY (id) REFERENCES persona(id)
) ENGINE=InnoDB;

-- =========================
-- TABLA PRODUCTO
-- =========================
CREATE TABLE producto (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  categoria VARCHAR(255) NOT NULL,
  tamano VARCHAR(50) NOT NULL,
  precioMensual DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- TABLA BODEGA
-- =========================
CREATE TABLE bodega (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  ubicacion VARCHAR(255) NOT NULL,
  capacidad INT NOT NULL,
  idEncargado BIGINT NOT NULL,
  PRIMARY KEY (id),
  KEY fkBodegaEmpleado (idEncargado),
  CONSTRAINT fkBodegaEmpleado 
  FOREIGN KEY (idEncargado) REFERENCES empleado(id)
) ENGINE=InnoDB;

-- =========================
-- TABLA INVENTARIO
-- =========================
CREATE TABLE inventario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  idBodega BIGINT NOT NULL,
  idProducto BIGINT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uqInventario (idBodega,idProducto),
  KEY fkInventarioProducto (idProducto),
  CONSTRAINT fkInventarioBodega 
  FOREIGN KEY (idBodega) REFERENCES bodega(id),
  CONSTRAINT fkInventarioProducto 
  FOREIGN KEY (idProducto) REFERENCES producto(id)
) ENGINE=InnoDB;

-- =========================
-- TABLA MOVIMIENTO
-- =========================
CREATE TABLE movimiento (
  id BIGINT NOT NULL AUTO_INCREMENT,
  fecha DATE NOT NULL,
  tipoMovimiento VARCHAR(50) NOT NULL,
  idEmpleado BIGINT NOT NULL,
  idBodegaOrigen BIGINT NOT NULL,
  idBodegaDestino BIGINT NOT NULL,
  PRIMARY KEY (id),
  KEY fkMovimientoEmpleado (idEmpleado),
  KEY fkMovimientoBodegaOrigen (idBodegaOrigen),
  KEY fkMovimientoBodegaDestino (idBodegaDestino),
  CONSTRAINT fkMovimientoEmpleado 
  FOREIGN KEY (idEmpleado) REFERENCES empleado(id),
  CONSTRAINT fkMovimientoBodegaOrigen 
  FOREIGN KEY (idBodegaOrigen) REFERENCES bodega(id),
  CONSTRAINT fkMovimientoBodegaDestino 
  FOREIGN KEY (idBodegaDestino) REFERENCES bodega(id)
) ENGINE=InnoDB;

-- =========================
-- TABLA DETALLE MOVIMIENTO
-- =========================
CREATE TABLE detallemovimiento (
  id BIGINT NOT NULL AUTO_INCREMENT,
  idMovimiento BIGINT NOT NULL,
  idProducto BIGINT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (id),
  KEY fkDetalleMovimientoMovimiento (idMovimiento),
  KEY fkDetalleMovimientoProducto (idProducto),
  CONSTRAINT fkDetalleMovimientoMovimiento 
  FOREIGN KEY (idMovimiento) REFERENCES movimiento(id),
  CONSTRAINT fkDetalleMovimientoProducto 
  FOREIGN KEY (idProducto) REFERENCES producto(id)
) ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;

-- ===================================
-- DATOS INICIALES
-- ===================================

-- PERSONAS
INSERT INTO persona (id,nombre,documento,correo,telefono) VALUES
(1,'Carlos Ramirez','100100100','carlos@logitrack.com','3001111111'),
(2,'Laura Martinez','200200200','laura@logitrack.com','3002222222'),
(3,'Andres Torres','300300300','andres@logitrack.com','3003333333'),
(4,'Sofia Mendoza','400400400','sofia@logitrack.com','3004444444');

-- EMPLEADOS
INSERT INTO empleado (id,rol,usuario,contrasena) VALUES
(1,'ADMIN','admin','1234'),
(2,'EMPLEADO','laura','1234'),
(3,'EMPLEADO','andres','1234'),
(4,'EMPLEADO','sofia','1234');

-- PRODUCTOS
INSERT INTO producto (nombre,categoria,tamano,precioMensual) VALUES
('Laptop','Tecnologia','Mediano',150.00),
('Monitor','Tecnologia','Grande',80.00),
('Teclado','Accesorios','Pequeño',20.00),
('Mouse','Accesorios','Pequeño',15.00),
('Servidor','Infraestructura','Grande',500.00),
('Router','Redes','Mediano',60.00);

-- BODEGAS
INSERT INTO bodega (nombre,ubicacion,capacidad,idEncargado) VALUES
('Bodega Central','Bogota',1000,1),
('Bodega Norte','Medellin',800,2),
('Bodega Sur','Cali',600,3);

-- INVENTARIO INICIAL
INSERT INTO inventario (idBodega,idProducto,cantidad) VALUES
(1,1,50),
(1,2,40),
(1,3,100),
(1,4,90),
(2,1,20),
(2,2,30),
(2,5,10),
(3,3,60),
(3,4,80),
(3,6,25);

-- MOVIMIENTOS
INSERT INTO movimiento (fecha,tipoMovimiento,idEmpleado,idBodegaOrigen,idBodegaDestino) VALUES
('2025-03-01','ENTRADA',1,1,1),
('2025-03-02','SALIDA',2,1,2),
('2025-03-03','TRANSFERENCIA',3,2,3);

-- DETALLE MOVIMIENTOS
INSERT INTO detallemovimiento (idMovimiento,idProducto,cantidad) VALUES
(1,1,10),
(1,3,20),
(2,2,5),
(3,4,15);

-- ===================================
-- VISTAS PARA REPORTES
-- ===================================

-- STOCK TOTAL POR BODEGA
CREATE VIEW vista_stock_bodega AS
SELECT 
b.nombre AS bodega,
p.nombre AS producto,
i.cantidad
FROM inventario i
JOIN bodega b ON b.id = i.idBodega
JOIN producto p ON p.id = i.idProducto;

-- PRODUCTOS CON STOCK BAJO (<10)
CREATE VIEW vista_stock_bajo AS
SELECT 
p.nombre,
b.nombre AS bodega,
i.cantidad
FROM inventario i
JOIN producto p ON p.id = i.idProducto
JOIN bodega b ON b.id = i.idBodega
WHERE i.cantidad < 10;

-- HISTORIAL DE MOVIMIENTOS
CREATE VIEW vista_movimientos AS
SELECT
m.id,
m.fecha,
m.tipoMovimiento,
e.usuario,
bo.nombre AS bodega_origen,
bd.nombre AS bodega_destino
FROM movimiento m
JOIN empleado e ON e.id = m.idEmpleado
JOIN bodega bo ON bo.id = m.idBodegaOrigen
JOIN bodega bd ON bd.id = m.idBodegaDestino;

-- PRODUCTOS MAS MOVIDOS
CREATE VIEW vista_productos_mas_movidos AS
SELECT
p.nombre,
SUM(d.cantidad) AS total_movimientos
FROM detallemovimiento d
JOIN producto p ON p.id = d.idProducto
GROUP BY p.nombre
ORDER BY total_movimientos DESC;

-- MOVIMIENTOS POR FECHA
CREATE VIEW vista_movimientos_por_fecha AS
SELECT
fecha,
COUNT(*) AS total_movimientos
FROM movimiento
GROUP BY fecha
ORDER BY fecha;

