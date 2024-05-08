CREATE OR REPLACE PROCEDURE generar_calendario AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_dias_entre_jornadas NUMBER;
    v_fecha_jornada DATE;
    v_hora_inicio VARCHAR2(5) := '10:00'; -- Hora de inicio de los enfrentamientos
    v_horas_por_jornada INT := 8; -- N�mero de horas disponibles por jornada
    v_id_jornada INT;
    v_num_enfrentamientos INT;
    v_id_local INT;
    v_enfrentados_anteriormente INT;
    v_id_visitante INT;
    v_num_competiciones INT;
BEGIN
    -- Obtener el n�mero total de competiciones
    SELECT COUNT(*)
    INTO v_num_competiciones
    FROM COMPETICION;

    -- Abrir un cursor que contiene todas las competiciones
    FOR c IN 1..v_num_competiciones LOOP
        -- Obtener el n�mero de equipos participantes en la competici�n
        SELECT COUNT(*)
        INTO v_num_equipos
        FROM CLASIFICACION
        WHERE ID_COMPETICION = c;

        -- Calcular el n�mero total de jornadas necesarias
        v_num_jornadas := v_num_equipos - 1;

        -- Obtener las fechas de inicio y fin de la competici�n
        SELECT FECHA_INICIO, FECHA_FIN
        INTO v_fecha_inicio, v_fecha_fin
        FROM COMPETICION
        WHERE ID_COMPETICION = c;

        -- Calcular el n�mero de d�as entre jornadas
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






CREATE OR REPLACE PROCEDURE calcular_puntos (
    p_id_jor_comp IN NUMBER,
    p_id_visitante IN NUMBER,
    p_id_local IN NUMBER,
    p_resultado_local IN NUMBER,
    p_resultado_visitante IN NUMBER
) IS
    v_puntos_local NUMBER(3) := 0;
    v_puntos_visitante NUMBER(3) := 0;  
BEGIN

       
    -- Calculate points for local team
    IF p_resultado_local < p_resultado_visitante THEN
        v_puntos_local := 3;
    ELSIF p_resultado_local = p_resultado_visitante THEN
        v_puntos_local := 1;
        v_puntos_visitante := 1;
    END IF;

    -- Calculate points for visiting team
    IF p_resultado_visitante < p_resultado_local THEN
        v_puntos_visitante := 3;
    ELSIF p_resultado_visitante = p_resultado_local THEN
        v_puntos_visitante := 1;
        v_puntos_local := 1;
    END IF;

    -- Update the CLASIFICACION table for local team
    UPDATE CLASIFICACION
    SET PUNTOS = PUNTOS + v_puntos_local
    WHERE ID_COMPETICION = (
        SELECT ID_COMPETICION
        FROM JORNADA
        WHERE ID_JOR_COMP = p_id_jor_comp
    )
    AND ID_EQUIPO = p_id_local;
   

    -- Update the CLASIFICACION table for visiting team
    UPDATE CLASIFICACION
    SET PUNTOS = PUNTOS + v_puntos_visitante
    WHERE ID_COMPETICION = (
        SELECT ID_COMPETICION
        FROM JORNADA
        WHERE ID_JOR_COMP = p_id_jor_comp
    )
    AND ID_EQUIPO = p_id_visitante;
 
    -- Update the ENFRENTAMIENTO table with the match results
    begin
    ejecutar_puntos(p_id_jor_comp,p_id_local, p_id_visitante,p_resultado_local, p_resultado_visitante);
    end;
END calcular_puntos;


create or replace procedure ejecutar_puntos(   
p_id_jor_comp IN NUMBER,
    p_id_visitante IN NUMBER,
    p_id_local IN NUMBER,
    p_resultado_local IN NUMBER,
    p_resultado_visitante IN NUMBER
) IS
begin

UPDATE ENFRENTAMIENTO
    SET RESULTADO_LOCAL = p_resultado_local,
        RESULTADO_VISITANTE = p_resultado_visitante
    WHERE ID_VISITANTE = p_id_visitante
    AND ID_LOCAL = p_id_local
      AND ID_JOR_COMP = p_id_jor_comp;
end ejecutar_puntos;




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




CREATE OR REPLACE PROCEDURE generar_calendario AS
    v_num_equipos INT;
    v_num_jornadas INT;
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_dias_entre_jornadas NUMBER;
    v_fecha_jornada DATE;
    v_hora_inicio VARCHAR2(5) := '10:00'; -- Hora de inicio de los enfrentamientos
    v_horas_por_jornada INT := 8; -- N�mero de horas disponibles por jornada
    v_id_jornada INT;
    v_num_enfrentamientos INT;
    v_id_local INT;
    v_enfrentados_anteriormente INT;
    v_id_visitante INT;
    v_num_competiciones INT;
BEGIN
    -- Obtener el n�mero total de competiciones
    SELECT COUNT(*)
    INTO v_num_competiciones
    FROM COMPETICION;

    -- Abrir un cursor que contiene todas las competiciones
    FOR c IN 1..v_num_competiciones LOOP
        -- Obtener el n�mero de equipos participantes en la competici�n
        SELECT COUNT(*)
        INTO v_num_equipos
        FROM CLASIFICACION
        WHERE ID_COMPETICION = c;

        -- Calcular el n�mero total de jornadas necesarias
        v_num_jornadas := v_num_equipos - 1;

        -- Obtener las fechas de inicio y fin de la competici�n
        SELECT FECHA_INICIO, FECHA_FIN
        INTO v_fecha_inicio, v_fecha_fin
        FROM COMPETICION
        WHERE ID_COMPETICION = c;

        -- Calcular el n�mero de d�as entre jornadas
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

