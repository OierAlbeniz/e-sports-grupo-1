
/*---CREACION DE TRIGGER QUE MIRA SI SE PUEDEN MoDIFICAR LOS JUGADORES CUANDO LA COMPETICION SE HA INICIADO----*/
CREATE OR REPLACE TRIGGER lock_jugador_table
BEFORE INSERT OR UPDATE or delete  ON jugador
FOR EACH ROW
DECLARE
  v_estado VARCHAR2(20);
BEGIN
  SELECT c.estado
  INTO v_estado
  FROM competicion c
  JOIN clasificacion cl ON c.id_competicion = cl.id_competicion
  WHERE cl.id_equipo = :NEW.id_equipo;

  IF v_estado = 'cerrado' THEN
    RAISE_APPLICATION_ERROR(-20002, 'No se puede agregar o actualizar un jugador a un equipo que participa en una competición cerrada');
  END IF;
END lock_jugador_table;

