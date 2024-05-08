CREATE OR REPLACE TRIGGER MODI_UP__EQ_CLASI_COMP_CERRADA
FOR INSERT OR UPDATE ON CLASIFICACION
COMPOUND TRIGGER
    v_estado_competencia COMPETICION.ESTADO%TYPE;
    v_id_equipo EQUIPO.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    -- Almacenamos el ID_EQUIPO del registro nuevo o actualizado
    v_id_equipo := COALESCE(:NEW.ID_EQUIPO, :OLD.ID_EQUIPO);
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN
    -- Solo si se ha realizado una actualización (INSERT o UPDATE)
    IF INSERTING OR UPDATING THEN
        -- Consultamos el estado de la competición asociada al equipo
        SELECT c.ESTADO INTO v_estado_competencia
        FROM COMPETICION c
        INNER JOIN CLASIFICACION cl ON c.ID_COMPETICION = cl.ID_COMPETICION
        WHERE cl.ID_EQUIPO = v_id_equipo
        AND c.ESTADO = 'cerrado';

        -- Si encontramos una competición cerrada asociada al equipo, lanzamos un error
        IF v_estado_competencia = 'cerrado' THEN
            RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operación porque el equipo está asociado a una competición cerrada.');
        END IF;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
END AFTER STATEMENT;
END MODI_UP__EQ_CLASI_COMP_CERRADA;

UPDATE CLASIFICACION 
SET ID_EQUIPO = 7
WHERE ID_CLASIFICACION = 12;

INSERT INTO CLASIFICACION (ID_COMPETICION, ID_EQUIPO, PUNTOS) VALUES (3, 1, 10);


