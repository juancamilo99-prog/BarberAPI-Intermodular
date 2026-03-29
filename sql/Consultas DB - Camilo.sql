-- consulta, join y subconsultas camilo
SELECT * FROM clientes; -- listar toda la tabla clientes

SELECT id_cliente,nombre,correo FROM clientes; -- mostrar solo el id, nombre y el correo de los clientes

SELECT * FROM servicios WHERE precio > 18 and duracion_minutos > 30; -- mostrar precios mayores que 18euros y duracion mayor a 30

SELECT * FROM reservas WHERE estado = 'completada'; -- mostrar reservas con estado "completada"

SELECT * FROM clientes WHERE correo LIKE '%@gmail.com'; -- mostrar todos los correos que terminen con @gmail.com 

SELECT p.id_cliente, p.nombre, p.apellido, p.correo, r.fecha_y_hora
FROM reservas r 
INNER JOIN clientes p ON p.id_cliente = r.id_cliente;  -- mostrar datos de las tablas unidas de clientes y reservas

SELECT AVG(precio) AS precio FROM servicios; -- consulta para calcular el precio medioid_cliente

SELECT c.correo, COUNT(p.id_reserva) AS reservas
FROM clientes c 
LEFT JOIN reservas p ON p.id_cliente = c.id_cliente
GROUP BY c.id_cliente, c.correo;  -- cuantas reservas tiene un cliente con el mismo correo

SELECT * FROM servicios WHERE precio > (SELECT AVG(precio) FROM servicios); -- servicio mas caro que el precio medio

SELECT r.estado, COUNT(*) AS reservas
FROM reservas r
GROUP BY r.estado;  -- mostrar numero total de reservas por estado.

SELECT s.nombre_servicio, COUNT(*) as reservas
FROM servicios s
LEFT JOIN reservas r ON r.id_servicio = s.id_servicio
GROUP BY r.id_servicio, s.nombre_servicio; -- mostrar la cantidad de reservas por cada servicio

SELECT * FROM servicios;
/*Consultas update*/

UPDATE reservas
SET estado = 'cancelada'
WHERE id_reserva = 5;

UPDATE servicios
SET duracion_minutos = 25
WHERE id_servicio = 1;














