/*-----------creacion de views-------*/


/*--view en la que se visualiza el nombre de el equipo el id y el resultado de un enfrentamiento--*/

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



/*--------View en la que se visualiuza el ranking de los equipos de una competicion---------*/

CREATE OR REPLACE VIEW VIEW_3_EUIPOS_MAS_PUNTOS AS
SELECT c.ID_COMPETICION,
       cl.ID_EQUIPO,
       e.NOMBRE AS NOMBRE_EQUIPO,
       cl.PUNTOS,
       ROW_NUMBER() OVER (PARTITION BY c.ID_COMPETICION ORDER BY cl.PUNTOS DESC) AS ranking
FROM CLASIFICACION cl
JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
JOIN EQUIPO e ON cl.ID_EQUIPO = e.ID_EQUIPO;

SELECT *
FROM VIEW_3_EUIPOS_MAS_PUNTOS
WHERE ID_COMPETICION = 1;