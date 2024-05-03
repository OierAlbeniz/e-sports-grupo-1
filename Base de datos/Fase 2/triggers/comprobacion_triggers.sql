/*----------COMPROBACIONES DE LOS TRIGGERS-------*/




/*---------------------bloqueo_competicion_cerrada----------------------*/
--este trigger va a bloquear la insercion de equipos cuando una compticion se ha INICIADO

-- creamos una competicion que este cerrada


INSERT INTO COMPETICION (NOMBRE, FECHA_INICIO, FECHA_FIN, ESTADO, ID_JUEGO) VALUES ('Competicion3', TO_DATE('2022-03-01', 'YYYY-MM-DD'), TO_DATE('2022-03-10', 'YYYY-MM-DD'), 'cerrado', 6);

-- añadimos jornadas a esta competicion 


INSERT INTO JORNADA (ID_JORNADA,ID_COMPETICION, FECHA_ENFENTAMIENTO) VALUES (4,9, TO_DATE('2022-03-01', 'YYYY-MM-DD'));

--seleccionamos el id de la competicion en la que el estado esta cerrado para poder meter un equipo en la clasificacion

select id_competicion from competicion where estado = 'cerrado'

-- si ejecutamos esta sentencia no tiene que funcionar porque la competicion esta cerrada y no va a dejar que se inserte ningun equipo


INSERT INTO CLASIFICACION (ID_COMPETICION, ID_EQUIPO, PUNTOS) VALUES (9, 1, 0);






/*-----comprobacion de trigger añadir jugador competicion cerrada----------*/

-- buscamos una competicion que este abierta para poder añadir datos
select * from competicion where lower(estado)='abierto'

--insertamos una jornada en la competicion abierta
INSERT INTO JORNADA (ID_JORNADA,ID_COMPETICION, FECHA_ENFENTAMIENTO) VALUES (5,1, TO_DATE('2022-03-01', 'YYYY-MM-DD'));

--comprobamos que se ha añadido la jornada
select * from jornada where id_jornada = 5

--insertamos los equipos 
INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR) VALUES ('Equipo14', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 2);
INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR) VALUES ('Equipo24', TO_DATE('2000-02-01', 'YYYY-MM-DD'), 3);

--comprobamos que estan los equipos
select * from equipo where id_equipo=1;
    select * from equipo where id_equipo=4;

--insertamos en enfrentamiento con los dos equipos que se han creado
INSERT INTO ENFRENTAMIENTO (ID_JOR_COMP, ID_ENFRENTAMIENTO, HORA, RESULTADO_LOCAL, RESULTADO_VISITANTE, ID_LOCAL, ID_VISITANTE) VALUES (5, 1, '12:00', 0, 0, 1, 4);

--cerramos la competicion 1 
UPDATE competicion
SET estado = 'cerrado'
WHERE id_competicion = 1;

-- miramos que esta cerrada
select * from competicion where id_competicion=1

-- vamos a intentar meter un jugador en el equipo que esta en una competicion cerrada
-- por lo que no dejaria meter jugadores
INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO) VALUES ('Jugador1', 'Apellido1', 'Apellido2', 1000, 'Nacionalidad1', TO_DATE('1990-11-01', 'YYYY-MM-DD'), 'Nick1', 'Rol1', 4);






/*-----comprobacion de trigger minimo un entrenador por equipo----------*/

--borramos el entrenador de un equipo en el cual solo hay un entrenador

DELETE FROM ENTRENADOR 
WHERE ID_EQUIPO = 3;

--cambiamos de equipo a un entrenador cuando en ese equipo solo esta ese entrenador

UPDATE ENTRENADOR
SET ID_EQUIPO = 2
WHERE ID_INTEGRANTE = 1;




/*-----comprobacion de trigger sueldo_maximo ----------*/

--actualizamos el sueldo de un jugador haciendo que la suma de los sueldo del equipo de dicho jugador sume una cntidad mayor que 200.000€

UPDATE JUGADOR
SET SUELDO = 199000
WHERE ID_INTEGRANTE = 1;

--insertamos un jugador cuyo sueldo sumado al sueldo del  resto de los jugadores de su equipo sume una cntidad mayor que 200.000€

INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Alex', 'Gonzalez', 'Martinez', 199000, 'EspaÃ±a', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'Alex', 'Delantero', 1);








/*-----comprobacion de trigger sueldo_minimo ----------*/

--actualizamos el sueldo de un jugador haciendo que su sueldo sea menor al sueldo minimo interprofesional(1.134€)

UPDATE JUGADOR
SET SUELDO = 112
WHERE ID_INTEGRANTE = 1;

--insertamos un jugador cuyo sueldo sea menor al sueldo minimo interprofesional(1.134€)

INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('Alex', 'Gonzalez', 'Martinez', 112, 'EspaÃ±a', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'Alex', 'Delantero', 1);


/*-----comprobacion de trigger que el numero maximo de jugadores en un equipo sea 6 ----------*/

--cambiamos de equipo a un jugador a otro equipo en el que ya hay 6 jugadores

UPDATE JUGADOR
SET ID_EQUIPO = 1
WHERE ID_INTEGRANTE = 7;

--insertamos un jugador en un equipo en el que ya hay 6 jugadores

INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO)
VALUES ('PEPE', 'Gonzalez', 'Martinez', 1135, 'EspaÃ±a', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'Alex', 'Delantero', 1);