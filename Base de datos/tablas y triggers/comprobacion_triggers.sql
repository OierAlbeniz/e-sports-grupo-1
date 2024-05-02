/*----------COMPROBACIONES DE LOS TRIGGERS-------*/

--bloqueo_competicion_cerrada
--este trigger va a bloquear la insercion de equipos cuando una compticion se ha INICIADO

-- creamos una competicion que este cerrada

INSERT INTO COMPETICION (NOMBRE, FECHA_INICIO, FECHA_FIN, ESTADO, ID_JUEGO) VALUES ('Competicion3', TO_DATE('2022-03-01', 'YYYY-MM-DD'), TO_DATE('2022-03-10', 'YYYY-MM-DD'), 'cerrado', 4);

-- añadimos jornadas a esta competicion 

INSERT INTO JORNADA (ID_JORNADA,ID_COMPETICION, FECHA_ENFENTAMIENTO) VALUES (4,4, TO_DATE('2022-03-01', 'YYYY-MM-DD'));

-- si ejecutamos esta sentencia no tiene que funcionar porque la competicion esta cerrada y no va a dejar que se inserte ningun equipo

INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION) VALUES ('Equipo1', TO_DATE('2000-01-01', 'YYYY-MM-DD'));

