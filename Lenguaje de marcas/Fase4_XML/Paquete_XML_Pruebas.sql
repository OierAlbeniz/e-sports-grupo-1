--Llamada al procedimiento para crear el XML de clasificacion
BEGIN
  PKG_XML.obtener_xml_clasificacion;
END;

----Llamada al procedimiento para crear el XML de todas las jornadas


BEGIN
  PKG_XML.obtener_xml_todas_jornadas;
END;

--Llamada al procedimiento para crear el XML de la ultima jornada
 
BEGIN
  PKG_XML.obtener_xml_ultima_jornada;
END;