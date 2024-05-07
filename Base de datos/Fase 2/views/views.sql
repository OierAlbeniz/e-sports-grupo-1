/*-----------creacion de views-------*/


--view en la que se visualiza el nombre de el equipo
-- el id y el resultado de un enfrentamiento

CREATE OR REPLACE VIEW vista_resultado AS
SELECT
  j.ID_JOR_COMP,
  enf.id_enfrentamiento as id_enfrentamiento,
  e.ID_EQUIPO AS local_id,
  e.NOMBRE AS local_name,
  enf.RESULTADO_LOCAL AS local_result,
  enf.RESULTADO_VISITANTE AS visitante_result,
  ev.NOMBRE AS visitante_name,
  ev.ID_EQUIPO AS visitante_id
  
FROM
  JORNADA j
JOIN
  ENFRENTAMIENTO enf ON j.ID_JORNADA = enf.ID_JOR_COMP
JOIN
  EQUIPO e ON e.ID_EQUIPO = enf.ID_LOCAL
JOIN
  EQUIPO ev ON ev.ID_EQUIPO = enf.ID_VISITANTE;
  
  
select * from vista_resultado