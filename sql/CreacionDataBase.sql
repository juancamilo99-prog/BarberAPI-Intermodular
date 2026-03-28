/* Creación de la base de datos */ 
CREATE DATABASE IF NOT EXISTS BarberAPI;
USE BarberAPI;

-- 1. Tabla Clientes
CREATE TABLE clientes (
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (50) NOT NULL,
    apellido VARCHAR (50) NOT NULL,
    correo VARCHAR (100) NOT NULL,
    telefono VARCHAR (15) NOT NULL
);

-- 2. Tabla Servicios
CREATE TABLE servicios (
	id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre_servicio VARCHAR (100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL (6,2) NOT NULL,
    duracion_minutos INT NOT NULL
);

-- 3. Tabla Reservas
CREATE TABLE reservas (
	id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    fecha_y_hora DATETIME NOT NULL,
    estado ENUM ('Pendiente', 'Completada', 'Cancelada' ) DEFAULT 'Pendiente',
    
    id_cliente INT NOT NULL,
    id_servicio INT NOT NULL,
    
    CONSTRAINT fk_cliente
		FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
        ON DELETE CASCADE,
        
	CONSTRAINT fk_servicio
		FOREIGN KEY (id_servicio) REFERENCES servicios(id_servicio)
        ON DELETE CASCADE
);