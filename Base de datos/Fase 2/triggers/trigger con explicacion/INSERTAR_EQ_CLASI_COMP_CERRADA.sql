/*--- INSERTAR EQUIPO EN UNA CASLIFICACION CUANDO LA COMPETICION ESTE CERRADA*/
/*Hemos creado una trigger que al momento de querer insertar un equipo en la clasificacion, 
no deja porque la competicion esta cerrada*/

CREATE OR REPLACE TRIGGER INSERTAR_EQ_CLASI_COMP_CERRADA
FOR INSERT ON CLASIFICACION
COMPOUND TRIGGER
    v_estado_competencia COMPETICION.ESTADO%TYPE;
    NEW_ID_EQUIPO EQUIPO.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    NEW_ID_EQUIPO:= :NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN
    SELECT c.ESTADO INTO v_estado_competencia
    FROM COMPETICION c
    INNER JOIN CLASIFICACION cl ON c.ID_COMPETICION = cl.ID_COMPETICION
    WHERE cl.ID_EQUIPO = NEW_ID_EQUIPO
    AND c.ESTADO = 'cerrado';

    IF v_estado_competencia = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede insertar el equipo porque est� asociado a una competici�n cerrada.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
END AFTER STATEMENT;
END INSERTAR_EQ_CLASI_COMP_CERRADA;
------------------------------------------------------------

