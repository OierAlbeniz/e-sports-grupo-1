/*-------procedure equipos competicion-------*/

--en este procedure se va a insertar un equipo a la competicion que se necesite
--se van a pasar los parametros de el id de la competicion y el nombre de el equipo
-- y se insertara en la tabla clasificacion automaticamente


CREATE OR REPLACE PROCEDURE equipo_en_competiciones(
    equipo_nombre IN VARCHAR2,
    competicion_id IN NUMBER
) AS
    equipo_id NUMBER(3);
BEGIN
    -- Buscar el ID del equipo
    BEGIN
        SELECT ID_EQUIPO INTO equipo_id
        FROM EQUIPO
        WHERE NOMBRE = equipo_nombre;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo especificado no existe.');
    END;
    -- Almacenar el equipo en la competición especificada
    INSERT INTO CLASIFICACION (ID_COMPETICION, ID_EQUIPO)
    VALUES (competicion_id, equipo_id);
END equipo_en_competiciones;


