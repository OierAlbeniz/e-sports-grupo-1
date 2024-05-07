/*--------procedure crear COMPETICION------*/

/*este procedure sirve para poder crear de manera mas rapida una competicion pasando 
los datos necesarios*/


CREATE OR REPLACE PROCEDURE crear_competicion (
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
END;



EXECUTE crear_competicion('prue_comp', TO_DATE('2025-02-12', 'YYYY-MM-DD'), TO_DATE('2023-07-31', 'YYYY-MM-DD'), 'abierto', 4);

select * from competicion