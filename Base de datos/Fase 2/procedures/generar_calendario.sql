CREATE OR REPLACE PROCEDURE generar_calendario AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_fecha_jornada DATE;
    v_hora_inicio VARCHAR2(5) := '10:00'; -- Hora de inicio de los enfrentamientos
    v_horas_por_jornada INT := 8; -- Número de horas disponibles por jornada
    v_id_jornada INT;
    v_num_enfrentamientos INT;
    v_id_local INT;
    v_id_visitante INT;
    v_num_competiciones INT;
    equipos_mezclados SYS.ODCINUMBERLIST; -- Declarar la lista de equipos mezclados
BEGIN
    -- Obtener el número total de competiciones
    SELECT COUNT(*)
    INTO v_num_competiciones
    FROM COMPETICION;

    -- Abrir un cursor que contiene todas las competiciones
    FOR c IN (SELECT ID_COMPETICION FROM COMPETICION) LOOP
        -- Obtener el número de equipos participantes en la competición
        SELECT COUNT(*)
        INTO v_num_equipos
        FROM CLASIFICACION
        WHERE ID_COMPETICION = c.ID_COMPETICION;

        -- Calcular el número total de jornadas necesarias
        v_num_jornadas := v_num_equipos - 1;

        -- Obtener las fechas de inicio y fin de la competición
        SELECT FECHA_INICIO, FECHA_FIN
        INTO v_fecha_inicio, v_fecha_fin
        FROM COMPETICION
        WHERE ID_COMPETICION = c.ID_COMPETICION;

        -- Calcular el número de días entre jornadas
        v_fecha_jornada := v_fecha_inicio;
        
        -- Insertar las jornadas y asignar las fechas
        FOR i IN 1..v_num_jornadas LOOP
            -- Insertar la jornada
            INSERT INTO JORNADA (ID_JORNADA, ID_COMPETICION, FECHA_ENFRENTAMIENTO)
            VALUES (i, c.ID_COMPETICION, v_fecha_jornada);

            -- Asignar el ID de la jornada actual
            SELECT ID_JOR_COMP
            INTO v_id_jornada
            FROM JORNADA
            WHERE ID_COMPETICION = c.ID_COMPETICION
              AND ID_JORNADA = i;

            v_num_enfrentamientos := v_num_equipos / 2;

            -- Mezclar aleatoriamente los equipos para esta jornada
            SELECT ID_EQUIPO
            BULK COLLECT INTO equipos_mezclados
            FROM (SELECT ID_EQUIPO FROM CLASIFICACION WHERE ID_COMPETICION = c.ID_COMPETICION ORDER BY DBMS_RANDOM.VALUE);

            -- Generar los enfrentamientos para la jornada actual
            FOR j IN 1..v_num_enfrentamientos LOOP
                v_id_local := equipos_mezclados(j);
                v_id_visitante := equipos_mezclados(j + v_num_enfrentamientos);

                -- Insertar el enfrentamiento con hora y equipos correspondientes
                INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_ENFRENTAMIENTO, HORA, ID_LOCAL, ID_VISITANTE)
                VALUES (
                    v_id_jornada, -- ID de la jornada actual
                    j, -- ID del enfrentamiento
                    TO_CHAR(TO_DATE(v_hora_inicio, 'HH24:MI') + ((j - 1) * (1 / (24 * 60 / v_horas_por_jornada))), 'HH24:MI'), -- Hora del enfrentamiento
                    v_id_local, -- ID del equipo local
                    v_id_visitante -- ID del equipo visitante
                );
            END LOOP;
            
            -- Avanzar la fecha de la jornada siguiente
            v_fecha_jornada := v_fecha_jornada + 7; -- Avanzar 7 días para la próxima jornada
        END LOOP;
    END LOOP;

    -- Confirmar los cambios realizados
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Manejar errores
        DBMS_OUTPUT.PUT_LINE('Error al generar el calendario: ' || SQLERRM);
        ROLLBACK;
END;