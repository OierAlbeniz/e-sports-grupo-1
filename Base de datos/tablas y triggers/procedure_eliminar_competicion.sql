/*------eliminar competicion------*/

--en este procedure se va a borrar la competicion con sus equipos correspondientes
--tambien se borraran las jornadas y los enfrentamientos de esa competicion
--ademas de borrar la clasificacion de esa competicion



CREATE OR REPLACE PROCEDURE eliminar_competicion (p_id_competicion IN NUMBER) IS
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
EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20002, 'Error al eliminar la competición especificada');
END;


select * from  competicion
select * from clasificacion where id_equipo=11
select * from jornada 
select * from enfrentamiento
execute eliminar_competicion (1);