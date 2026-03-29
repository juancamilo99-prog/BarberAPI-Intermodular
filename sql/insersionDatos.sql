-- insersion datos en la tabla del cliente
INSERT INTO clientes(nombre,apellido,correo,telefono)
VALUES
('Jheremi','Lema','jheremi@gmail.com','666112233'),
('Camilo','Montero', 'montero@gmail.com','604141948'),
('Sophie','Lema','sophie@gmail.com','666999888'),
('Miri','Belen','miribelen@gmail.com','666555444');

-- insersion datos de la tabla servivios
INSERT INTO servicios(nombre_servicio,descripcion,precio,duracion_minutos)
VALUES
('Corte clasico','Corte de pelo tradicional con tijera o maquina',14.99,30),
('Corte degradado','Corte de pelo con degradado moderno fade-out',19.99,30),
('Corte con flequillo','Corte recto que a manera de fleco dejar caer sobre frente',24.99,40),
('Corte nube','Corte para cabello risado, ligero y difuso',29.99,60),
('Combo one', 'Combo de corte de pelo tradicional mas barba', 15.99,40);

-- insersion de datos de la tabla reservas
INSERT INTO reservas(fecha_y_hora,estado,id_cliente,id_servicio)
VALUES
('2026-04-15 10:00:00', 'Pendiente', 1, 2), -- Jheremi reserva un Corte degradado
('2026-04-15 11:30:00', 'Pendiente', 2, 5), -- Camilo reserva un Combo corte + barba
('2026-04-16 17:00:00', 'Completada', 3, 3), -- Sophie reserva un corte con flequillo(ya completada)
('2026-04-18 18:00:00', 'Cancelada', 4, 4); -- Miribelen reserva un corte nube


