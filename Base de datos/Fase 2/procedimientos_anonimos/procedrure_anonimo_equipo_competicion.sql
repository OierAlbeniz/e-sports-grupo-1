DECLARE
    nombre_equipo VARCHAR2(100) := 'Los Cocodrilos'; -- Debe ser un equipo existente en la tabla EQUIPO
    id_competicion NUMBER := 1; -- Debe ser una competición existente en la tabla COMPETICION
BEGIN
    equipo_en_competiciones(nombre_equipo, id_competicion);
    DBMS_OUTPUT.PUT_LINE('Procedimiento ejecutado correctamente.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al ejecutar el procedimiento: ' || SQLERRM);
END;


SELECT * FROM CLASIFICACION;


