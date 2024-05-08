/*----------comprobacion de procedures---------*/

/*----calcular clasificacion----*/

--ejecutamos el procedure
execute calcular_puntos(33,1,3,5,1); --con los datos "id_jor_com", "id_local", "id_visitante","resultado_local","reslutado_visitante"

--comprobamos que se han insertado los resultados de el partido 
select enfrentamiento.* from enfrentamiento
join jornada on enfrentamiento.id_jor_comp = jornada.id_jor_comp
where jornada.id_competicion=1;

--miramos si se han sumando los puntos al equipo que gana el partido
select * from clasificacion order by puntos desc;


/*--------insertar equipo en competicion------*/

--insertamos un equipo
INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR) VALUES ('Los Teeentos', TO_DATE('2000-12-01', 'YYYY-MM-DD'), 3);

--ejecutamos para que el equipo se meta en la competicion 2 y 3
execute almacenar_equipo_en_competiciones('Los Teeentos', 2 );
execute almacenar_equipo_en_competiciones('Los Teeentos', 3 );
select * from equipo where nombre = 'Los Teeentos';

--comprobamos que se ha insertado 
 select * from equipos where lower(nombre)='los teeentos'
 --cogemos el id de ese equipo
 selec id_competicion from clasificacion where id_equipo = 5

 /*----------calcular clasificacion------------*/

 
EXEC generar_enfrentamientos(2);
select * from enfrentamiento
DELETE FROM ENFRENTAMIENTOS;

EXEC generar_jornadas(2);
EXEC generar_jornadas(1);
EXEC generar_jornadas(3);
EXEC generar_jornadas(4);

select * from jornada




