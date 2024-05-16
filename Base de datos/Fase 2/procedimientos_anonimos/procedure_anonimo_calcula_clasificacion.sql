DECLARE
    v_id_jor_comp        NUMBER := 1;
    v_id_visitante       NUMBER := 2;
    v_id_local           NUMBER := 3;
    v_resultado_local    NUMBER := 2;
    v_resultado_visitante NUMBER := 1;
    v_id_competicion     NUMBER := 1;
BEGIN
    INSERT INTO JUEGO (NOMBRE, EMPRESA, FECHA_LANZAMIENTO) 
    VALUES ('Juego de Prueba', 'Empresa de Prueba', TO_DATE('2023-01-01', 'YYYY-MM-DD'))
    RETURNING ID_JUEGO INTO v_id_competicion;

    INSERT INTO COMPETICION (NOMBRE, FECHA_INICIO, FECHA_FIN, ESTADO, ID_JUEGO)
    VALUES ('Competición de Prueba', TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 'abierto', v_id_competicion);

    INSERT INTO JORNADA (ID_JORNADA, ID_COMPETICION, FECHA_ENFRENTAMIENTO)
    VALUES (1, v_id_competicion, TO_DATE('2023-05-01', 'YYYY-MM-DD'))
    RETURNING ID_JOR_COMP INTO v_id_jor_comp;

    INSERT INTO PATROCINADOR (NOMBRE)
    VALUES ('Patrocinador de Prueba');

    INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR)
    VALUES ('Equipo Local', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 1)
    RETURNING ID_EQUIPO INTO v_id_local;

    INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR)
    VALUES ('Equipo Visitante', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 1)
    RETURNING ID_EQUIPO INTO v_id_visitante;

    INSERT INTO CLASIFICACION (ID_COMPETICION, ID_EQUIPO, PUNTOS)
    VALUES (v_id_competicion, v_id_local, 0);

    INSERT INTO CLASIFICACION (ID_COMPETICION, ID_EQUIPO, PUNTOS)
    VALUES (v_id_competicion, v_id_visitante, 0);

    INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_LOCAL, ID_VISITANTE, HORA,resultado_local,resultado_visitante)
    VALUES (v_id_jor_comp, v_id_local, v_id_visitante, '18:00',3,5);

    calcular_puntos(
        p_id_jor_comp => v_id_jor_comp,
        p_id_visitante => v_id_visitante,
        p_id_local => v_id_local,
        p_resultado_local => v_resultado_local,
        p_resultado_visitante => v_resultado_visitante
    );

    DECLARE
        v_puntos_local      NUMBER;
        v_puntos_visitante  NUMBER;
        v_resultado_local_verif      NUMBER;
        v_resultado_visitante_verif  NUMBER;
    BEGIN
        SELECT PUNTOS
        INTO v_puntos_local
        FROM CLASIFICACION
        WHERE ID_COMPETICION = v_id_competicion
        AND ID_EQUIPO = v_id_local;

        SELECT PUNTOS
        INTO v_puntos_visitante
        FROM CLASIFICACION
        WHERE ID_COMPETICION = v_id_competicion
        AND ID_EQUIPO = v_id_visitante;

        DBMS_OUTPUT.PUT_LINE('Puntos Local: ' || v_puntos_local);
        DBMS_OUTPUT.PUT_LINE('Puntos Visitante: ' || v_puntos_visitante);

        SELECT RESULTADO_LOCAL, RESULTADO_VISITANTE
        INTO v_resultado_local_verif, v_resultado_visitante_verif
        FROM ENFRENTAMIENTO
        WHERE ID_JOR_COMP = v_id_jor_comp
        AND ID_LOCAL = v_id_local
        AND ID_VISITANTE = v_id_visitante;

        -- Imprimir resultados para verificación
        DBMS_OUTPUT.PUT_LINE('Resultado Local: ' || v_resultado_local_verif);
        DBMS_OUTPUT.PUT_LINE('Resultado Visitante: ' || v_resultado_visitante_verif);
    END;
END;