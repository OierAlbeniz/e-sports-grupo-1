/*--modificar competicion-----*/

/*en este procedure se van a pasar los datos de la competicion que se 
quiere modificar*/
/*en el proyecto java habra un desplegable para seleccionar la competicion
y cuando selecciones se guardara en una variable del id que
servira para poder identificar la competicion y poder usar este procedure*/

CREATE OR REPLACE PROCEDURE modificar_competicion (
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
END;
