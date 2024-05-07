/*-----------------package con los procedures de competicion----------*/

--en este package se reunen la alta baja y modificacion de un equipo

CREATE OR REPLACE PACKAGE competicion_pkg AS
    -- Procedimiento para crear una competición
    PROCEDURE crear_competicion (
        p_nombre IN VARCHAR2,
        p_fecha_inicio IN DATE,
        p_fecha_fin IN DATE,
        p_estado IN VARCHAR2,
        p_id_juego IN NUMBER
    );

    -- Procedimiento para eliminar una competición
    PROCEDURE eliminar_competicion (p_id_competicion IN NUMBER);

    -- Procedimiento para modificar una competición
    PROCEDURE modificar_competicion (
        p_id_competicion IN NUMBER,
        p_nombre IN VARCHAR2,
        p_fecha_inicio IN DATE,
        p_fecha_fin IN DATE,
        p_estado IN VARCHAR2,
        p_id_juego IN NUMBER
    );
END competicion_pkg;
/

CREATE OR REPLACE PACKAGE BODY competicion_pkg AS
    -- Procedimiento para crear una competición
    PROCEDURE crear_competicion (
        p_nombre IN VARCHAR2,
        p_fecha_inicio IN DATE,
        p_fecha_fin IN DATE,
        p_estado IN VARCHAR2,
        p_id_juego IN NUMBER
    ) AS
    BEGIN
        -- Insertar la competición en la tabla COMPETICION
        INSERT INTO COMPETICION (NOMBRE, FECHA_INICIO, FECHA_FIN, ESTADO, ID_JUEGO)
        VALUES (p_nombre, p_fecha_inicio, p_fecha_fin, p_estado, p_id_juego);

        DBMS_OUTPUT.PUT_LINE('Competición creada correctamente.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END crear_competicion;

    -- Procedimiento para eliminar una competición
    PROCEDURE eliminar_competicion (p_id_competicion IN NUMBER) IS
        -- Declaración de variables
        v_num_equipos NUMBER(3);
    BEGIN
        -- Comprobar si la competición existe
        SELECT COUNT(*) INTO v_num_equipos FROM COMPETICION WHERE ID_COMPETICION = p_id_competicion;
        IF v_num_equipos = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'La competición especificada no existe');
            RETURN;
        END IF;

        -- Eliminar las jornadas de la competición
        DELETE FROM JORNADA WHERE ID_COMPETICION = p_id_competicion;

        -- Eliminar los enfrentamientos de la competición
        DELETE FROM ENFRENTAMIENTO WHERE ID_JOR_COMP IN (SELECT ID_JOR_COMP FROM JORNADA WHERE ID_COMPETICION = p_id_competicion);

        -- Eliminar la clasificación de la competición
        DELETE FROM CLASIFICACION WHERE ID_COMPETICION = p_id_competicion;

        -- Eliminar los equipos de la competición
        DELETE FROM EQUIPO
        WHERE ID_EQUIPO IN (SELECT ID_EQUIPO FROM CLASIFICACION WHERE ID_COMPETICION = p_id_competicion)
        AND ID_EQUIPO NOT IN (SELECT ID_EQUIPO FROM CLASIFICACION WHERE ID_COMPETICION <> p_id_competicion);

        -- Eliminar la competición
        DELETE FROM COMPETICION WHERE ID_COMPETICION = p_id_competicion;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20002, 'Error al eliminar la competición especificada');
    END eliminar_competicion;

    -- Procedimiento para modificar una competición
    PROCEDURE modificar_competicion (
        p_id_competicion IN NUMBER,
        p_nombre IN VARCHAR2,
        p_fecha_inicio IN DATE,
        p_fecha_fin IN DATE,
        p_estado IN VARCHAR2,
        p_id_juego IN NUMBER
    ) AS
    BEGIN
        -- Actualizar la competición en la tabla COMPETICION
        UPDATE COMPETICION
        SET NOMBRE = p_nombre,
            FECHA_INICIO = p_fecha_inicio,
            FECHA_FIN = p_fecha_fin,
            ESTADO = p_estado,
            ID_JUEGO = p_id_juego
        WHERE ID_COMPETICION = p_id_competicion;

        DBMS_OUTPUT.PUT_LINE('Competición modificada correctamente.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END modificar_competicion;
END competicion_pkg;




 


