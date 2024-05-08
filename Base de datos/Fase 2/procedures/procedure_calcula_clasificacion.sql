/*-------procedure calcualo clasificacion---------*/

/*este procedure clacula el resultado entre un enfrentamiento y asigana los PUNTOS
en la tabla clasificacion, si quedan empate se añaden1 punto a cada equipo
si un equipo gana se lleva 3 puntos*/

CREATE OR REPLACE PROCEDURE calcular_puntos (
    p_id_jor_comp IN NUMBER,
    p_id_visitante IN NUMBER,
    p_id_local IN NUMBER,
    p_resultado_local IN NUMBER,
    p_resultado_visitante IN NUMBER
) IS
    v_puntos_local NUMBER(3) := 0;
    v_puntos_visitante NUMBER(3) := 0;  
BEGIN

       
    -- Calculate points for local team
    IF p_resultado_local < p_resultado_visitante THEN
        v_puntos_local := 3;
    ELSIF p_resultado_local = p_resultado_visitante THEN
        v_puntos_local := 1;
        v_puntos_visitante := 1;
    END IF;

    -- Calculate points for visiting team
    IF p_resultado_visitante < p_resultado_local THEN
        v_puntos_visitante := 3;
    ELSIF p_resultado_visitante = p_resultado_local THEN
        v_puntos_visitante := 1;
        v_puntos_local := 1;
    END IF;

    -- Update the CLASIFICACION table for local team
    UPDATE CLASIFICACION
    SET PUNTOS = PUNTOS + v_puntos_local
    WHERE ID_COMPETICION = (
        SELECT ID_COMPETICION
        FROM JORNADA
        WHERE ID_JOR_COMP = p_id_jor_comp
    )
    AND ID_EQUIPO = p_id_local;
   

    -- Update the CLASIFICACION table for visiting team
    UPDATE CLASIFICACION
    SET PUNTOS = PUNTOS + v_puntos_visitante
    WHERE ID_COMPETICION = (
        SELECT ID_COMPETICION
        FROM JORNADA
        WHERE ID_JOR_COMP = p_id_jor_comp
    )
    AND ID_EQUIPO = p_id_visitante;
 
    -- Update the ENFRENTAMIENTO table with the match results
    begin
    ejecutar_puntos(p_id_jor_comp,p_id_local, p_id_visitante,p_resultado_local, p_resultado_visitante);
    end;
END calcular_puntos;


create or replace procedure ejecutar_puntos(   
p_id_jor_comp IN NUMBER,
    p_id_visitante IN NUMBER,
    p_id_local IN NUMBER,
    p_resultado_local IN NUMBER,
    p_resultado_visitante IN NUMBER
) IS
begin

UPDATE ENFRENTAMIENTO
    SET RESULTADO_LOCAL = p_resultado_local,
        RESULTADO_VISITANTE = p_resultado_visitante
    WHERE ID_VISITANTE = p_id_visitante
    AND ID_LOCAL = p_id_local
      AND ID_JOR_COMP = p_id_jor_comp;
end ejecutar_puntos;





