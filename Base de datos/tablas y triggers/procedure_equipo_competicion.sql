/*-------procedure equipos competicion-------*/

--en este procedure se va a insertar un equipo a las competiciones que se necesite
--se van a pasar los parametros de el id de la competicion y el nombre de el equipo

3
CREATE OR REPLACE PROCEDURE almacenar_equipo_en_competiciones(
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
END almacenar_equipo_en_competiciones;


INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR) VALUES ('Los Teeentos', TO_DATE('2000-12-01', 'YYYY-MM-DD'), 3);

execute almacenar_equipo_en_competiciones('Los Teeentos', 2 );
execute almacenar_equipo_en_competiciones('Los Teeentos', 3 );
select * from equipo where nombre = 'Los Teeentos';

 select * from clasificacion 