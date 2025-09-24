drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabras(
    codigo_Palabra int auto_increment,
    palabra varchar(30),
    pista_1 varchar(60),
    pista_2 varchar(60),
    pista_3 varchar(60),
    primary key PK_codigo_Palabra(codigo_Palabra)
);

CREATE TABLE Usuarios (
    codigo_Usuario INT AUTO_INCREMENT,
    correo_Usuario VARCHAR(100) NOT NULL UNIQUE,
    contraseña_Usuario VARCHAR(100) NOT NULL,
    PRIMARY KEY PK_codigo_Usuario (codigo_Usuario)
);

DELIMITER //
CREATE PROCEDURE sp_Agregar_Usuario(
    IN c VARCHAR(100),
    IN pass VARCHAR(100)
)
BEGIN
    INSERT INTO Usuarios (correo_Usuario, contraseña_Usuario)
    VALUES (c, pass);
END //
DELIMITER ;

CALL sp_Agregar_Usuario('quintom', 'admin');


DELIMITER //
CREATE PROCEDURE sp_Listar_Usuarios()
BEGIN
    SELECT codigo_Usuario, correo_Usuario, contraseña_Usuario
    FROM Usuarios;
END //
DELIMITER ;

CALL sp_Listar_Usuarios();


DELIMITER //
CREATE PROCEDURE sp_Agregar_Palabras(
    IN p VARCHAR(30),
    IN p1 VARCHAR(60),
    IN p2 VARCHAR(60),
    IN p3 VARCHAR(60)
)
BEGIN
    INSERT INTO Palabras (palabra, pista_1, pista_2, pista_3)
    VALUES (p, p1, p2, p3);
END //

DELIMITER ;

CALL sp_Agregar_Palabras('JAVASCRIPT', 'Lenguaje de programación usado en la web', 'Permite hacer páginas interactivas', 'Se ejecuta en el navegador');
CALL sp_Agregar_Palabras('PROGRAMACION', 'Actividad que consiste en escribir código','Es la base para crear software', 'Usa lenguajes como Java, Python o C++');
CALL sp_Agregar_Palabras('DESARROLLO', 'Proceso de creación de aplicaciones', 'Incluye análisis, diseño y pruebas', 'Puede ser web, móvil o de escritorio');
CALL sp_Agregar_Palabras('NETBEANS', 'Un entorno de desarrollo integrado (IDE)', 'Muy usado para programar en Java', 'Tiene soporte para múltiples lenguajes');
CALL sp_Agregar_Palabras('COMPUTADORA', 'Máquina que procesa información', 'Puede ejecutar programas', 'Usa hardware y software');
CALL sp_Agregar_Palabras('FUNCIONES', 'Bloques de código reutilizables', 'Se pueden invocar varias veces', 'Reciben parámetros y pueden devolver valores');


DELIMITER //
CREATE PROCEDURE sp_Listar_Palabra()
BEGIN
    SELECT codigo_Palabra, palabra, pista_1, pista_2, pista_3 
    FROM Palabras;
END //

DELIMITER ;

CALL sp_Listar_Palabra();


DELIMITER //
create procedure sp_ObtenerPalabraAleatoria()
begin
    select codigo_Palabra, palabra, pista_1, pista_2, pista_3 from Palabras order by RAND() limit 1;
end //

DELIMITER ;

call sp_ObtenerPalabraAleatoria();
