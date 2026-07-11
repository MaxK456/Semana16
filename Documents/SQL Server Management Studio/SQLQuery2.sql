--Crear una nueva base de datos
create database prueba02;
-- Crear la tabla persona
--ir a la BD
use prueba02;
--Crear tabla persona
create table Persona(
ID int NOT NULL PRIMARY KEY,
Apellido varchar(225) not null,
Nombre varchar (255),
Edad int Check (Edad >= 18),
Ciudad varchar(255) DEFAULT 'Lima'
);
--insertar registros
INSERT INTO Persona(ID,Apellido,Nombre, Edad, Ciudad)
VALUES (1, 'Jaramillo M.', 'Jaime', 59, 'Trujillo'),
(2, 'Torres M.', 'Ana', 25, 'Trujillo'),
(3, 'Gonzales T.', 'Abel', 19, 'Trujillo');
-- Para mostrar los registros
Select * from Persona;
INSERT INTO Persona (ID, Apellido)
Values (4, 'Carrillo M.'),
(5, 'Huertas J.');
--Agregar la columna telefono
alter table Persona add telefono varchar(9);
--Actualizar la columna Telefono
Update Persona set telefono = '990' Where ID = 3;
alter table Persona alter column telefono varchar(9) not null;
-- eliminar la columna edad
alter table Persona drop column telefono;
select * from persona;
alter table Persona alter column edad int null;
alter table Persona drop column edad;
--
alter table Persona alter column ID int; 
--limpiar tabla
truncate table persona;