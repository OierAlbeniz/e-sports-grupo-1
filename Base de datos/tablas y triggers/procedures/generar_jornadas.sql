/*-------generar jornadas------*/

--en este procedure se crearan jornadas automaticamente 
--se insertan el id_competicion y calcual los equipos que hay
--y genera el numero de jornadas para que cada equipo se enferente
--una vez con otro en un aocmpeticion, asignados fechas

CREATE OR REPLACE PROCEDURE generar_jornadas(
    p_id_competicion IN COMPETICION.ID_COMPETICION%TYPE
) AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_dias_entre_jornadas NUMBER; -- Número de días entre jornadas
    v_fecha_jornada DATE; -- Fecha de cada jornada
BEGIN
    -- Obtener el número de equipos participantes en la competición
    SELECT COUNT(*)
    INTO v_num_equipos
    FROM CLASIFICACION
    WHERE ID_COMPETICION = p_id_competicion;

    -- Calcular el número de jornadas necesarias
    v_num_jornadas := v_num_equipos - 1;

    -- Obtener las fechas de inicio y fin de la competición
    SELECT FECHA_INICIO, FECHA_FIN
    INTO v_fecha_inicio, v_fecha_fin
    FROM COMPETICION
    WHERE ID_COMPETICION = p_id_competicion;

    -- Calcular el número de días entre jornadas
    v_dias_entre_jornadas := (v_fecha_fin - v_fecha_inicio) / v_num_jornadas;

    -- Insertar las jornadas y asignar las fechas
    FOR i IN 1..v_num_jornadas LOOP
        -- Calcular la fecha de la jornada
        v_fecha_jornada := v_fecha_inicio + (i - 1) * v_dias_entre_jornadas;

        -- Insertar la jornada
        INSERT INTO JORNADA (ID_JORNADA, ID_COMPETICION, FECHA_ENFRENTAMIENTO)
        VALUES (i, p_id_competicion, v_fecha_jornada);
    END LOOP;

    -- Confirmar los cambios realizados
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Manejar errores
        DBMS_OUTPUT.PUT_LINE('Error al generar las jornadas: ' || SQLERRM);
        ROLLBACK;
END;

EXEC generar_jornadas(2);
EXEC generar_jornadas(1);
EXEC generar_jornadas(3);
EXEC generar_jornadas(4);