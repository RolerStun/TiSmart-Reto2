CREATE TABLE provincia(
    idProvincia NUMBER GENERATED BY DEFAULT AS IDENTITY,
    descProvincia VARCHAR2(100) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idProvincia)
);
desc provincia;

insert into provincia(descProvincia, fecharegistro) values ('Callao', to_Date(sysdate, 'DD/MM/YYYY'));
insert into provincia(descProvincia, fecharegistro) values ('Lima', to_Date(sysdate, 'DD/MM/YYYY'));

CREATE TABLE distrito(
    idDistrito NUMBER GENERATED BY DEFAULT AS IDENTITY,
    idProvincia NUMBER REFERENCES provincia NOT NULL,
    descDistrito VARCHAR2(100) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idDistrito)
);
desc distrito;

insert into distrito(idProvincia, descdistrito, fecharegistro) values (1, 'Carmen de la Legua', to_Date(sysdate, 'DD/MM/YYYY'));
insert into distrito(idProvincia, descdistrito, fecharegistro) values (2, 'San Miguel', to_Date(sysdate, 'DD/MM/YYYY'));

CREATE TABLE sede(
    idSede NUMBER GENERATED BY DEFAULT AS IDENTITY,
    descSede VARCHAR2(100) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idSede)
);
desc sede;
--aca
insert into sede(descSede, fecharegistro) values ('Callao', to_Date(sysdate, 'DD/MM/YYYY'));
insert into sede(descSede, fecharegistro) values ('San Miguel', to_Date(sysdate, 'DD/MM/YYYY'));


CREATE TABLE gerente(
    idGerente NUMBER GENERATED BY DEFAULT AS IDENTITY,
    descGerente VARCHAR2(100) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idGerente)
);
desc gerente;


insert into gerente(descGerente, fecharegistro) values ('Americo Ramirez', to_Date(sysdate, 'DD/MM/YYYY'));
insert into gerente(descGerente, fecharegistro) values ('Jose Peralta', to_Date(sysdate, 'DD/MM/YYYY'));

CREATE TABLE condicion(
    idCondicion NUMBER GENERATED BY DEFAULT AS IDENTITY,
    descCondicion VARCHAR2(100) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idCondicion)
);
desc condicion;


insert into condicion(descCondicion, fecharegistro) values ('Condicion1', to_Date(sysdate, 'DD/MM/YYYY'));
insert into condicion(descCondicion, fecharegistro) values ('Condicion2', to_Date(sysdate, 'DD/MM/YYYY'));

CREATE TABLE hospital(
    idHospital NUMBER GENERATED BY DEFAULT AS IDENTITY,
    idDistrito NUMBER REFERENCES distrito NOT NULL,
    nombre VARCHAR2(150) NOT NULL,
    antiguedad NUMBER NOT NULL,
    area NUMBER(5,2) NOT NULL,
    idSede NUMBER REFERENCES sede NOT NULL,
    idGerente NUMBER REFERENCES gerente NOT NULL,
    idCondicion NUMBER REFERENCES condicion NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idHospital)
);
desc hospital;


insert into hospital(idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion, fecharegistro) values (3, 'Hospital San Jose', 25, 999.99, 1, 2, 1, to_Date(sysdate, 'DD/MM/YYYY'));
insert into hospital(idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion, fecharegistro) values (2, 'Hospital Essalud I Octavio Mongrut Muñoz', 40, 999.99, 2, 1, 2, to_Date(sysdate, 'DD/MM/YYYY'));
insert into hospital(idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion, fecharegistro) values (3, 'Hospital San Jose', 35, 999.99, 1, 2, 1, to_Date(sysdate, 'dd/mm/yyyy'));
insert into hospital(idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion, fecharegistro) values (3, 'Hospital Sanitas', 35, 999.99, 1, 2, 1, to_Date(sysdate, 'dd/mm/yyyy'));

CREATE TABLE users(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR2(100) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    PRIMARY KEY(id)
);
desc users;

insert into users(username, password) values ('rolerstun', '1234');
insert into users(username, password) values ('admin', '1234');


/***************************************************************************/

CREATE OR REPLACE PROCEDURE lista_hospitales(
    p_hospital IN hospital.nombre%TYPE DEFAULT NULL,
    p_sede IN hospital.idsede%TYPE DEFAULT NULL,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    IF p_hospital IS NOT NULL AND p_sede IS NULL THEN
        OPEN p_result FOR
            SELECT h.idhospital, h.iddistrito, h.nombre, h.antiguedad, h.area, h.idsede, h.idgerente, h.idcondicion, h.fecharegistro
            FROM hospital h 
            JOIN distrito d ON d.iddistrito = h.iddistrito
            JOIN sede s ON s.idsede = h.idsede
            JOIN gerente g ON g.idgerente = h.idgerente
            JOIN condicion c ON c.idcondicion = h.idcondicion
            WHERE LOWER(h.nombre) LIKE '%' || LOWER(p_hospital) || '%'
            ORDER BY h.idhospital ASC;
    ELSIF p_hospital IS NULL AND p_sede IS NOT NULL THEN
        OPEN p_result FOR
            SELECT h.idhospital, h.iddistrito, h.nombre, h.antiguedad, h.area, h.idsede, h.idgerente, h.idcondicion, h.fecharegistro
            FROM hospital h 
            JOIN distrito d ON d.iddistrito = h.iddistrito
            JOIN sede s ON s.idsede = h.idsede
            JOIN gerente g ON g.idgerente = h.idgerente
            JOIN condicion c ON c.idcondicion = h.idcondicion
            WHERE h.idsede = p_sede
            ORDER BY h.idhospital ASC;
    ELSE
        OPEN p_result FOR
            SELECT h.idhospital, h.iddistrito, h.nombre, h.antiguedad, h.area, h.idsede, h.idgerente, h.idcondicion, h.fecharegistro
            FROM hospital h 
            JOIN distrito d ON d.iddistrito = h.iddistrito
            JOIN sede s ON s.idsede = h.idsede
            JOIN gerente g ON g.idgerente = h.idgerente
            JOIN condicion c ON c.idcondicion = h.idcondicion
            ORDER BY h.idhospital ASC;
    END IF;
END;


/***************************************************************************/

CREATE OR REPLACE PROCEDURE insertar_hospital(
    p_iddistrito IN hospital.iddistrito%TYPE,
    p_nombre IN hospital.nombre%TYPE,
    p_antiguedad IN hospital.antiguedad%TYPE,
    p_area IN hospital.area%TYPE,
    p_idsede IN hospital.idsede%TYPE,
    p_idgerente IN hospital.idgerente%TYPE,
    p_idcondicion IN hospital.idcondicion%TYPE,
    p_fecharegistro IN hospital.fecharegistro%TYPE
)
AS
BEGIN
    INSERT INTO hospital (iddistrito, nombre, antiguedad, area, idsede, idgerente, idcondicion, fecharegistro)
    VALUES (p_iddistrito, p_nombre, p_antiguedad, p_area, p_idsede, p_idgerente, p_idcondicion, p_fecharegistro);
END;


/***************************************************************************/


CREATE OR REPLACE PROCEDURE actualizar_hospital(
    p_idhospital IN hospital.idhospital%TYPE,
    p_iddistrito IN hospital.iddistrito%TYPE,
    p_nombre IN hospital.nombre%TYPE,
    p_antiguedad IN hospital.antiguedad%TYPE,
    p_area IN hospital.area%TYPE,
    p_idsede IN hospital.idsede%TYPE,
    p_idgerente IN hospital.idgerente%TYPE,
    p_idcondicion IN hospital.idcondicion%TYPE,
    p_fecharegistro IN hospital.fecharegistro%TYPE
)
IS
BEGIN
    UPDATE hospital
    SET iddistrito = p_iddistrito,
        nombre = p_nombre,
        antiguedad = p_antiguedad,
        area = p_area,
        idsede = p_idsede,
        idgerente = p_idgerente,
        idcondicion = p_idcondicion,
        fecharegistro = p_fecharegistro
    WHERE idhospital = p_idhospital;
END;


/***************************************************************************/

CREATE OR REPLACE PROCEDURE eliminar_hospital(
    p_idhospital IN hospital.idhospital%TYPE
)
IS
BEGIN
    DELETE FROM hospital
    WHERE idHospital = p_idhospital;
END;
