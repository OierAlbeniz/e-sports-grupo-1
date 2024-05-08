CREATE OR REPLACE TRIGGER TR_DELETE_CLASIFICACION
FOR DELETE ON CLASIFICACION
COMPOUND TRIGGER
    v_estado_competicion COMPETICION.ESTADO%TYPE;
    OLD_ID_EQUIPO CLASIFICACION.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    OLD_ID_EQUIPO := :OLD.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN
    -- Verificar si alguna competici�n en la que el equipo est� participando est� cerrada
    SELECT c.ESTADO INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO IN OLD_ID_EQUIPO AND c.ESTADO = 'cerrado';

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operaci�n. El equipo est� en una competici�n cerrada.');
    END IF;
EXCEPTION
    -- Capturar excepci�n cuando no hay ninguna competici�n cerrada para este equipo
    WHEN NO_DATA_FOUND THEN
        NULL; -- No hacer nada si no hay competiciones�cerradas
END AFTER STATEMENT;
END;

--ESTE ES EL QUE NO FUNCIONA---