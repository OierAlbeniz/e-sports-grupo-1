CREATE OR REPLACE PROCEDURE generar_calendario AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_dias_entre_jornadas NUMBER;
    v_fecha_jornada DATE;
    v_hora_inicio VARCHAR2(5) := '10:00'; -- Hora de inicio de los enfrentamientos
    v_horas_por_jornada INT := 8; -- Número de horas disponibles por jornada
    v_id_jornada INT;
    v_num_enfrentamientos INT;
    v_id_local INT;
    v_enfrentados_anteriormente INT;
    v_id_visitante INT;
    v_num_competiciones INT;
BEGIN
    -- Obtener el número total de competiciones
    SELECT COUNT(*)
    INTO v_num_competiciones
    FROM COMPETICION;

    -- Abrir un cursor que contiene todas las competiciones
    FOR c IN 1..v_num_competiciones LOOP
        -- Obtener el número de equipos participantes en la competición
        SELECT COUNT(*)
        INTO v_num_equipos
        FROM CLASIFICACION
        WHERE ID_COMPETICION = c;

        -- Calcular el número total de jornadas necesarias
        v_num_jornadas := v_num_equipos - 1;

        -- Obtener las fechas de inicio y fin de la competición
        SELECT FECHA_INICIO, FECHA_FIN
        INTO v_fecha_inicio, v_fecha_fin
        FROM COMPETICION
        WHERE ID_COMPETICION = c;

        -- Calcular el número de días entre jornadas
        v_dias_entre_jornadas := (v_fecha_fin - v_fecha_inicio) / v_num_jornadas;

        -- Insertar las jornadas y asignar las fechas
        FOR i IN 1..v_num_jornadas LOOP
            -- Calcular la fecha de la jornada
            v_fecha_jornada := v_fecha_inicio + (i - 1) * v_dias_entre_jornadas;

            -- Insertar la jornada
            INSERT INTO JORNADA (ID_JORNADA, ID_COMPETICION, FECHA_ENFRENTAMIENTO)
            VALUES (i, c, v_fecha_jornada);

            -- Asignar el ID de la jornada actual
            SELECT ID_JOR_COMP into v_id_jornada FROM JORNADA WHERE ID_COMPETICION = c and id_jornada=i;
            v_num_enfrentamientos := v_num_equipos / 2;

            -- Generar los enfrentamientos para la jornada actual
            FOR j IN 1..v_num_enfrentamientos LOOP
                -- Determinar el equipo local para este enfrentamiento
                v_id_local := j;

                -- Verificar si el equipo local se ha enfrentado previamente al equipo visitante
                v_enfrentados_anteriormente := 0;
                
                FOR k IN 1..j LOOP
                    SELECT COUNT(*)
                    INTO v_enfrentados_anteriormente
                    FROM ENFRENTAMIENTO
                    WHERE ((ID_LOCAL = j AND ID_VISITANTE = k) OR (ID_LOCAL = k AND ID_VISITANTE = j))
                    AND ID_JOR_COMP IN (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = c AND ID_JORNADA < i);

                    IF v_enfrentados_anteriormente > 0 THEN
                        EXIT; -- Si ya se han enfrentado, sal del bucle
                    END IF;
                END LOOP;

                -- Si el equipo local se ha enfrentado previamente al equipo visitante, selecciona otro equipo visitante
                IF v_enfrentados_anteriormente= 0 THEN
                    v_id_visitante := j + v_num_enfrentamientos;
                ELSE
                    v_id_visitante := j;
                END IF;

                -- Insertar el enfrentamiento con hora y equipos correspondientes
                INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_ENFRENTAMIENTO, HORA, ID_LOCAL, ID_VISITANTE)
                VALUES (
                    v_id_jornada, -- ID de la jornada actual
                    j, -- ID del enfrentamiento
                    TO_CHAR(TO_DATE(v_hora_inicio, 'HH24:MI') + (j - 1) * (1 / (24 * 60 / v_horas_por_jornada)), 'HH24:MI'), -- Hora del enfrentamiento
                    NULL, -- ID del equipo local
                    NULL -- ID del equipo visitante
                );
            END LOOP;
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



select * from competicion;
select * from clasificacion;
select * from jornada;
select * from enfrentamiento;

execute calcular_puntos(3,7,6,10,3);

select enfrentamiento.* from enfrentamiento
join jornada on enfrentamiento.id_jor_comp = jornada.id_jor_comp
where jornada.id_competicion=1;

EXEC generar_calendario;

DELETE FROM ENFRENTAMIENTO;
DELETE FROM JORNADA;