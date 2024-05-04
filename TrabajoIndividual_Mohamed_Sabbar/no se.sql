/*create database trabajadores;*/
use trabajadores;

CREATE TABLE trabajador (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NULL,
  puesto VARCHAR(45) NULL,
  salario INT NULL,
  fecha_alta date null,
  PRIMARY KEY (id));
  
  drop table trabajador;
  insert into trabajador (nombre, puesto, salario, fechaAlta)
	values ('mikel', 'alumno', 1200, 2023-04-26)