

/*-----creacion de trigger bloqueo de equipos cuando la competicion esta iniciada-----*/
CREATE OR REPLACE TRIGGER lock_equipo_table
BEFORE INSERT ON clasificacion
FOR EACH ROW
DECLARE
  v_estado VARCHAR2(20);
BEGIN
  SELECT estado
  INTO v_estado
  FROM competicion
  WHERE id_competicion = :NEW.id_competicion;

  IF v_estado = 'cerrado' THEN
    RAISE_APPLICATION_ERROR(-20001, 'No se puede agregar un equipo a una competición cerrada');
  END IF;
END lock_equipo_table;
/
