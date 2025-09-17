drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabra(
codigoPalabra int auto_increment,
palabra varchar(30),
pista1 varchar(60),
pista2 varchar(60),
pista3 varchar(60),
primary key PK_codigoPalabra(codigoPalabra)
);

CREATE TABLE Usuario (
    codigoUsuario INT AUTO_INCREMENT,
    correoUsuario VARCHAR(100) NOT NULL UNIQUE,
    contraseñaUsuario VARCHAR(100) NOT NULL,
    PRIMARY KEY PK_codigoUsuario (codigoUsuario)
);

DELIMITER //
CREATE PROCEDURE sp_AgregarUsuario(
    IN c VARCHAR(100),
    IN pass VARCHAR(100)
)
BEGIN
    INSERT INTO Usuario (correoUsuario, contraseñaUsuario)
    VALUES (c, pass);
END //
DELIMITER ;

CALL sp_AgregarUsuario('quintom', 'admin');


DELIMITER //
CREATE PROCEDURE sp_ListarUsuarios()
BEGIN
    SELECT codigoUsuario, correoUsuario, contraseñaUsuario
    FROM Usuario;
END //
DELIMITER ;

CALL sp_ListarUsuarios();


DELIMITER //
CREATE PROCEDURE sp_AgregarPalabra(
    IN p VARCHAR(30),
    IN p1 VARCHAR(60),
    IN p2 VARCHAR(60),
    IN p3 VARCHAR(60)
)
BEGIN
    INSERT INTO Palabra (palabra, pista1, pista2, pista3)
    VALUES (p, p1, p2, p3);
END //

DELIMITER ;

CALL sp_AgregarPalabra('JAVASCRIPT', 'Lenguaje de programación usado en la web', 'Permite hacer páginas interactivas', 'Se ejecuta en el navegador');
CALL sp_AgregarPalabra('PROGRAMACION', 'Actividad que consiste en escribir código','Es la base para crear software', 'Usa lenguajes como Java, Python o C++');
CALL sp_AgregarPalabra('DESARROLLO', 'Proceso de creación de aplicaciones', 'Incluye análisis, diseño y pruebas', 'Puede ser web, móvil o de escritorio');
CALL sp_AgregarPalabra('NETBEANS', 'Un entorno de desarrollo integrado (IDE)', 'Muy usado para programar en Java', 'Tiene soporte para múltiples lenguajes');
CALL sp_AgregarPalabra('COMPUTADORA', 'Máquina que procesa información', 'Puede ejecutar programas', 'Usa hardware y software');
CALL sp_AgregarPalabra('FUNCIONES', 'Bloques de código reutilizables', 'Se pueden invocar varias veces', 'Reciben parámetros y pueden devolver valores');


DELIMITER //
CREATE PROCEDURE sp_ListarPalabra()
BEGIN
    SELECT codigoPalabra, palabra, pista1, pista2, pista3 
    FROM Palabra;
END //

DELIMITER ;

CALL sp_ListarPalabra();


DELIMITER //
create procedure sp_ObtenerPalabraAleatoria()
begin
    select codigoPalabra,palabra, pista1, pista2, pista3 from Palabra order by RAND() limit 1;
end //

DELIMITER ;

CALL sp_ObtenerPalabraAleatoria();