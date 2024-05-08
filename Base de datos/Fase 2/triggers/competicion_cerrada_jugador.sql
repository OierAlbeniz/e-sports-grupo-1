CREATE OR REPLACE TRIGGER TR_COMPETICION_CERRADA_JUG
BEFORE INSERT OR UPDATE OR DELETE ON JUGADOR
FOR EACH ROW
DECLARE
    v_estado_competicion COMPETICION.ESTADO%TYPE;
BEGIN
    -- Verificar si alguna competici�n en la que el equipo est� participando est� cerrada
    SELECT c.ESTADO INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO = :NEW.ID_EQUIPO OR cl.ID_EQUIPO = :OLD.ID_EQUIPO AND c.ESTADO = 'cerrado';

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operaci�n. El equipo est� en una competici�n cerrada.');
    END IF;
EXCEPTION
    -- Capturar excepci�n cuando no hay ninguna competici�n cerrada para este equipo
    WHEN NO_DATA_FOUND THEN
        NULL; -- No hacer nada si no hay competiciones cerradas
END TR_COMPETICION_CERRADA_JUG;

DELETE FROM JUGADOR 
WHERE NOMBRE ='Lily';
ROLLBACK
UPDATE JUGADOR
SET ID_EQUIPO = 8
WHERE NOMBRE ='David';

INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Natalie', 'Garcia', 'Martinez', 2000, 'Espa�a', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'Nate', 'Delantero', 7);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Evan', 'Martinez', 'Rodriguez', 1800, 'Francia', TO_DATE('1992-08-20', 'YYYY-MM-DD'), 'Evan', 'Portero', 7);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Samantha', 'Sanchez', 'Lopez', 2200, 'Argentina', TO_DATE('1993-02-10', 'YYYY-MM-DD'), 'Sam', 'Defensa', 7);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Henry', 'Lopez', 'Gutierrez', 1900, 'Mexico', TO_DATE('1994-04-25', 'YYYY-MM-DD'), 'Harry', 'Centrocampista', 7);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Abigail', 'Martinez', 'Rodriguez', 2100, 'Inglaterra', TO_DATE('1991-12-03', 'YYYY-MM-DD'), 'Abby', 'Defensa', 7);

INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Charlotte', 'Rodriguez', 'Garcia', 2000, 'Espa�a', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'Charlie', 'Delantero', 10);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Jacob', 'Gutierrez', 'Martinez', 1800, 'Francia', TO_DATE('1992-08-20', 'YYYY-MM-DD'), 'Jake', 'Portero', 10);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Mia', 'Sanchez', 'Lopez', 2200, 'Argentina', TO_DATE('1993-02-10', 'YYYY-MM-DD'), 'Mia', 'Defensa', 10);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Noah', 'Lopez', 'Gutierrez', 1900, 'Mexico', TO_DATE('1994-04-25', 'YYYY-MM-DD'), 'Noah', 'Centrocampista', 10);
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Aurora', 'Martinez', 'Rodriguez', 2100, 'Inglaterra', TO_DATE('1991-12-03', 'YYYY-MM-DD'), 'Rory', 'Defensa', 10);