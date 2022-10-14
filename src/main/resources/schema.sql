CREATE TABLE producto(
	ID LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NOMBRE VARCHAR(255) NOT NULL,
	PRECIO LONG NOT NULL ,
	IMAGEN VARCHAR(255)NOT NULL,
	USUARIO);

CREATE TABLE usuario(
	ID NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NOMBRE VARCHAR(255) NOT NULL,
	AVATAR VARCHAR(255),
	APELLIDO VARCHAR(255) NOT NULL,
	EMAIL VARCHAR(255) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL	
	);	
