/*-------generar todo------*/

--en este procedure se crearan jornadas automaticamente 
--se insertan el id_competicion y calcual los equipos que hay
--y genera el numero de jornadas para que cada equipo se enferente
--una vez con otro en un competicion, asignados fechas

CREATE OR REPLACE PROCEDURE gestionar_enfrentamientos(
    p_id_competicion IN COMPETICION.ID_COMPETICION%TYPE
) AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_fecha_jornada DATE;
    v_hora VARCHAR2(5);
    v_id_equipo INT;
    v_enfrentados NUMBER := 0;
BEGIN
    -- Obtener el número de equipos participantes en la competición
    SELECT COUNT(*)
    INTO v_num_equipos
    FROM CLASIFICACION
    WHERE ID_COMPETICION = p_id_competicion;

    -- Obtener la fecha de inicio y fin de la competición
    SELECT FECHA_INICIO, FECHA_FIN
    INTO v_fecha_inicio, v_fecha_fin
    FROM COMPETICION
    WHERE ID_COMPETICION = p_id_competicion;

    -- Calcular el número de jornadas
    v_num_jornadas := CEIL((v_fecha_fin - v_fecha_inicio) / 7);

    -- Eliminar los enfrentamientos existentes para esta competición
    DELETE FROM ENFRENTAMIENTO
    WHERE ID_JOR_COMP IN (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = p_id_competicion);

    -- Generar los enfrentamientos para cada jornada
    FOR i IN 1..v_num_jornadas LOOP
        -- Calcular la fecha de la jornada
        v_fecha_jornada := v_fecha_inicio + ((i - 1) * 7);

        -- Insertar la jornada en la tabla JORNADA
        INSERT INTO JORNADA (ID_JORNADA, ID_COMPETICION, FECHA_ENFRENTAMIENTO)
        VALUES (i, p_id_competicion, v_fecha_jornada);

        -- Generar los enfrentamientos para cada equipo en la jornada
        FOR j IN 1..v_num_equipos LOOP
            -- Generar una hora aleatoria entre las 10:00 am y las 20:00 pm
            v_hora := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(0, 12)) + 10, 'FM00') || ':00';

            -- Obtener el ID del equipo visitante
            SELECT ID_EQUIPO INTO v_id_equipo
            FROM CLASIFICACION
            WHERE ID_COMPETICION = p_id_competicion
            AND ID_EQUIPO NOT IN (
                SELECT ID_VISITANTE
                FROM ENFRENTAMIENTO
                WHERE ID_JOR_COMP = i
                AND ID_LOCAL = j
            )
            AND ROWNUM = 1
            AND ID_EQUIPO <> j;

            -- Comprobar si el equipo visitante ya se ha enfrentado al equipo local en la competición
            SELECT COUNT(*) INTO v_enfrentados
            FROM ENFRENTAMIENTO
            WHERE ID_JOR_COMP = i
            AND (ID_LOCAL = j AND ID_VISITANTE = v_id_equipo OR ID_LOCAL = v_id_equipo AND ID_VISITANTE = j);

            -- Insertar el enfrentamiento solo si el equipo visitante no se ha enfrentado al equipo local en la competición
            IF v_enfrentados = 0 THEN
               INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_ENFRENTAMIENTO, HORA, ID_LOCAL, ID_VISITANTE)
                VALUES (i, j, v_hora, j, v_id_equipo);
            ELSE
                -- Si el equipo visitante ya se ha enfrentado al equipo local en la competición,
                -- reiniciar el bucle para encontrar un nuevo equipo visitante
                j := j - 1;
            END IF;
        END LOOP;
    END LOOP;

    -- Confirmar los cambios realizados
    COMMIT;
END;


