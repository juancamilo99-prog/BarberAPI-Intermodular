/*Consultas Simples*/

-- 1. Obtener catálogo completo de servicios ordenado por precio de menor a mayor
SELECT nombre_servicio, descripcion, precio, duracion_minutos
FROM servicios
ORDER BY precio ASC;

-- 2. Buscar un cliente especifico por su correo electrónico
SELECT id_cliente, nombre, apellido, telefono
FROM clientes
WHERE correo = 'montero@gmail.com';

-- 3. Listar todos los servicios que duren entre 30 minutos o menos
SELECT nombre_servicio, duracion_minutos
FROM servicios
WHERE duracion_minutos <= 30;

-- 4. Ver cuantas reservas hay en cada estado (Pendiente, Completado, Cancelado)
SELECT id_cliente, estado, COUNT(id_reserva) AS total_reservas
FROM reservas
GROUP BY estado, id_cliente;

-- 5. Mostrar las citas programadas para una fecha concreta
SELECT id_reserva, fecha_y_hora, estado
FROM reservas
WHERE fecha_y_hora >= '2026-04-16 00:00:00';

/*Consultas con uso de JOIN*/

-- 6. ver listdo de reservas con el nombre del cliente 
SELECT r.id_reserva, r.fecha_y_hora, r.estado, c.nombre, c.apellido
FROM reservas r
INNER JOIN clientes c
ON r.id_cliente = c.id_cliente;

-- 7. Ver listado de reservas con el servicio que quiere el cliente y su precio
SELECT r.id_reserva, r.fecha_y_hora, s.nombre_servicio, s.precio
FROM reservas r
INNER JOIN servicios s
ON r.id_servicio = s.id_servicio;

-- 8. Mostrar todos los clientes y sus reservas con LEFT JOIN
SELECT c.nombre, c.apellido, r.fecha_y_hora, r.estado
FROM clientes c
LEFT JOIN reservas r
ON c.id_cliente = r.id_cliente;

-- 9. Mostrar el catálogo completo de los servicios y cuando se han reservado (RIGHT JOIN)
SELECT r.fecha_y_hora, r.estado, s.nombre_servicio, s.precio
FROM reservas r
RIGHT JOIN servicios s
ON r.id_servicio = s.id_servicio;

-- 10. Listar total de cliente con detalle del servicio que han pedido
SELECT c.nombre, c.apellido, r.fecha_y_hora, r.estado, s.nombre_servicio
FROM clientes c
LEFT JOIN reservas r
ON c.id_cliente = r.id_cliente
LEFT JOIN servicios s
ON r.id_servicio = s.id_servicio;

/* CONSULTAS UPDATE*/
-- 1. Un cliente cambia su número de telefono
UPDATE clientes
SET telefono = '600500111'
WHERE correo = 'borja_m@gmail.com';

-- 2. Reprgramar una cita. cambio de fecha y hora
UPDATE reservas
SET fecha_y_hora = '2026-04-10 18:30:00'
WHERE id_reserva = 4;

-- 3. Marcar una reserva como "Completada" o "Cancelada"ALTER
UPDATE reservas
SET estado = 'Completada'
WHERE id_reserva = 2;

-- 4. Eliminar una reservas especifica
DELETE FROM reservas
WHERE id_reserva = 5;

-- Nueva inserccion de datos - Prueba de UPDATE y DELETE by Jheremi
INSERT INTO clientes (nombre, apellido, correo, telefono)
VALUES
('Borja', 'Martin', 'borja_m@gmail.com', '655000111');

INSERT INTO reservas(fecha_y_hora,estado,id_cliente,id_servicio)
VALUES
('2026-04-15 10:00:00', 'Pendiente', 5, 1);
