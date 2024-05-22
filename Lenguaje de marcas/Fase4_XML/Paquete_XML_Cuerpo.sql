CREATE OR REPLACE PACKAGE BODY PKG_XML
AS
PROCEDURE obtener_xml_clasificacion AS
  v_result CLOB;
  V_FECHA_EXPIRACION DATE;
BEGIN
  
  SELECT 
    XMLELEMENT("COMPETICIONES",
      XMLAGG(
        XMLELEMENT("COMPETICION",
          XMLATTRIBUTES(c.ID_COMPETICION AS "ID"),
              XMLELEMENT("NOMBRE_COMPETICION", c.NOMBRE),
		  XMLELEMENT("JUEGO", j.NOMBRE),
		  XMLELEMENT("FECHA_INICIO", c.FECHA_INICIO),
		  XMLELEMENT("FECHA_FIN", c.FECHA_FIN),
		  XMLELEMENT("LISTA_EQUIPOS",
                  XMLAGG(
                        XMLELEMENT("EQUIPO",
                          XMLATTRIBUTES(e.ID_EQUIPO AS "ID"),
                              XMLELEMENT("NOMBRE_EQUIPO", e.NOMBRE),
                              XMLELEMENT("PUNTOS", cl.PUNTOS)
              )ORDER BY cl.PUNTOS DESC
            )
          )
        )
      )
    ).getClobVal() -- para transformar el xml en clob y poder guardarlo en la variable v_result
  INTO v_result
  FROM COMPETICION c,  CLASIFICACION cl, EQUIPO e, JUEGO j
  WHERE  c.ID_COMPETICION = cl.ID_COMPETICION AND cl.ID_EQUIPO = e.ID_EQUIPO 
  AND c.ID_JUEGO=j.ID_JUEGO
  GROUP BY c.ID_COMPETICION, c.NOMBRE,j.NOMBRE,c.FECHA_INICIO,c.FECHA_FIN;

   SELECT SYSDATE + 7 INTO v_fecha_expiracion
   FROM DUAL; 

  -- Insertar el XML  en una tabla temporal junto con la fecha de expiracion
  INSERT INTO TEMP_XML_CLASIFICACION VALUES(v_result, v_fecha_expiracion);

  -- Mostrar el resultado (para probar)
  DBMS_OUTPUT.put_line(v_result);

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error: ' || SQLERRM);
END obtener_xml_clasificacion;

PROCEDURE obtener_xml_todas_jornadas AS
  v_result CLOB;
  v_fecha_expiracion DATE;
BEGIN
  -- Construir XML de competiciones y jornadas
  SELECT XMLELEMENT("COMPETICIONES",
           XMLAGG(
             XMLELEMENT("COMPETICION",
               XMLATTRIBUTES(c.ID_COMPETICION AS "ID"),
               XMLELEMENT("NOMBRE_COMPETICION", c.NOMBRE),
               XMLELEMENT("JORNADAS",
						(SELECT XMLAGG(
							XMLELEMENT("JORNADA", 
                                XMLATTRIBUTES(j.ID_JORNADA AS "ID", j.FECHA_ENFRENTAMIENTO),
								XMLELEMENT("ENFRENTAMIENTOS",
								      ( SELECT XMLAGG(
											XMLELEMENT("ENFRENTAMIENTO",
												XMLATTRIBUTES(ef.ID_ENF_JOR AS "ID", ef.HORA),
                                                XMLELEMENT("EQUIPO_LOCAL",
                                                (SELECT e.NOMBRE
                                                 FROM EQUIPO e
                                                 WHERE e.ID_EQUIPO = ef.ID_LOCAL)),
                                                XMLELEMENT("EQUIPO_VISITANTE",
                                                (SELECT e.NOMBRE
                                                 FROM EQUIPO e
                                                 WHERE e.ID_EQUIPO = ef.ID_VISITANTE)),
                                                XMLELEMENT("RESULTADO_LOCAL",ef.resultado_local),
                                                XMLELEMENT("RESULTADO_VISITANTE",ef.resultado_visitante)
											) 
										) 
									FROM ENFRENTAMIENTO ef
									WHERE ef.ID_JOR_COMP=j.ID_JOR_COMP
									) 
									) 
								) 
							 )
						FROM JORNADA j
						WHERE j.ID_COMPETICION = c.ID_COMPETICION) 
                   )
                 ) 
               ) 
             ).getClobVal() 
  INTO v_result
  FROM COMPETICION c;

  -- Establecer fecha de expiración (7 días después de la fecha actual)
  SELECT SYSDATE + 7 INTO v_fecha_expiracion FROM DUAL;

  -- Insertar el XML resultante en una tabla temporal 
  INSERT INTO TEMP_XML_TODAS_JORNADAS VALUES(v_result, v_fecha_expiracion);

  -- Mostrar el resultado
  DBMS_OUTPUT.put_line(v_result);

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error: ' || SQLERRM);
END obtener_xml_todas_jornadas;

PROCEDURE obtener_xml_ultima_jornada 
AS
  v_result CLOB;
  v_fecha_expiracion DATE;
BEGIN
  -- Construir XML de competiciones y jornadas
  SELECT XMLELEMENT("COMPETICIONES",
           XMLAGG(
             XMLELEMENT("COMPETICION",
               XMLATTRIBUTES(c.ID_COMPETICION AS "ID"),
               XMLELEMENT("NOMBRE_COMPETICION", c.NOMBRE),
               XMLELEMENT("ULTIMA_JORNADA",
						(SELECT XMLAGG(
							XMLELEMENT("JORNADA", 
                                XMLATTRIBUTES(j.ID_JORNADA AS "ID", j.FECHA_ENFRENTAMIENTO),
								XMLELEMENT("ENFRENTAMIENTOS",
								    ( SELECT XMLAGG(
											XMLELEMENT("ENFRENTAMIENTO",
												XMLATTRIBUTES(ef.ID_ENF_JOR AS "ID", ef.HORA),
                                                XMLELEMENT("EQUIPO_LOCAL",
                                                (SELECT e.NOMBRE
                                                 FROM EQUIPO e
                                                 WHERE e.ID_EQUIPO = ef.ID_LOCAL)),
                                                XMLELEMENT("EQUIPO_VISITANTE",
                                                (SELECT e.NOMBRE
                                                 FROM EQUIPO e
                                                 WHERE e.ID_EQUIPO = ef.ID_VISITANTE)),
                                                XMLELEMENT("RESULTADO_LOCAL",ef.resultado_local),
                                                XMLELEMENT("RESULTADO_VISITANTE",ef.resultado_visitante)
											) 
										) 
									FROM ENFRENTAMIENTO ef
									WHERE ef.ID_JOR_COMP=j.ID_JOR_COMP
									) 
									) 
								) 
							 )
						FROM JORNADA j
						WHERE j.ID_COMPETICION = c.ID_COMPETICION 
                        AND j.FECHA_ENFRENTAMIENTO=(SELECT max(FECHA_ENFRENTAMIENTO)
                                                    from jornada))
                   )
                 ) 
               ) 
             ).getClobVal() 
  INTO v_result
  FROM COMPETICION c;

  -- Establecer fecha de expiración (7 días después de la fecha actual)
  SELECT SYSDATE + 7 INTO v_fecha_expiracion FROM DUAL;

  -- Insertar el XML resultante en una tabla temporal 
  INSERT INTO TEMP_XML_ULTIMA_JORNADA VALUES(v_result, v_fecha_expiracion);

  -- Mostrar el resultado
  DBMS_OUTPUT.put_line(v_result);

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error: ' || SQLERRM);
END obtener_xml_ultima_jornada;

END PKG_XML;