/*-----Procedure creacion de enfrentamientos------*/

--en este procedure se van a generar los enfrentamientos de cada
--uno de los equipos añadiendoles una hora y un id_enfrentamiento

CREATE OR REPLACE PROCEDURE generar_enfrentamientos(
    p_id_competicion IN COMPETICION.ID_COMPETICION%TYPE
) AS
    v_num_equipos INT;
    v_fecha_jornada DATE; -- Fecha de cada jornada
    v_id_equipo INT; -- ID del equipo visitante
    v_hora VARCHAR2(5); -- Hora del enfrentamiento
    v_num_enfrentamientos INT; -- Número de enfrentamientos con la misma hora en la jornada
BEGIN
    -- Obtener el número de equipos participantes en la competición
    SELECT COUNT(*)
    INTO v_num_equipos
    FROM CLASIFICACION
    WHERE ID_COMPETICION = p_id_competicion;

    -- Eliminar los enfrentamientos existentes para esta competición
    DELETE FROM ENFRENTAMIENTO
    WHERE ID_JOR_COMP IN (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = p_id_competicion);

    -- Generar los enfrentamientos para cada jornada
    FOR i IN 1..v_num_equipos LOOP
        FOR j IN i+1..v_num_equipos LOOP
            -- Calcular la fecha de la jornada
            SELECT FECHA_ENFRENTAMIENTO INTO v_fecha_jornada
            FROM JORNADA
            WHERE ID_COMPETICION = p_id_competicion AND ID_JORNADA = i;

            -- Generar una hora aleatoria entre las 10:00 am y las 20:00 pm
            v_hora := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(0, 12)) + 10, 'FM00') || ':00';

            -- Obtener el ID del equipo visitante
            SELECT ID_EQUIPO INTO v_id_equipo
            FROM CLASIFICACION
            WHERE ID_COMPETICION = p_id_competicion AND ROWNUM = 1 AND ID_EQUIPO <> i
            AND ID_EQUIPO NOT IN (SELECT ID_VISITANTE FROM ENFRENTAMIENTO WHERE ID_JOR_COMP = (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = p_id_competicion AND ID_JORNADA = i));

            -- Insertar el enfrentamiento
            INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_ENFRENTAMIENTO, HORA, ID_LOCAL, ID_VISITANTE)
            VALUES (
                (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = p_id_competicion AND ID_JORNADA = i),
                j-i, -- Utilizar una secuencia para generar el ID automáticamente
                v_hora,
                i,
                v_id_equipo
            );
        END LOOP;
    END LOOP;
END;

EXEC generar_enfrentamientos(1);
EXEC generar_enfrentamientos(2);
EXEC generar_enfrentamientos(3);
EXEC generar_enfrentamientos(4);

DELETE FROM ENFRENTAMIENTO;
