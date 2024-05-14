DROP TRIGGER BORRAR_EQ_CLASI_COMP_CERRADA;
DROP TRIGGER competicion_cerrada_jugador;
DROP TRIGGER comrpobacion_triggers;
DROP TRIGGER entrenador;
DROP TRIGGER ins_up_clas_comp_cerrada;
DROP TRIGGER mayor_de_6;
DROP TRIGGER modificacion_equipos;
DROP TRIGGER modificacion_jugadores;
DROP TRIGGER sueldo_maximo;
DROP TRIGGER sueldo_minimo;
DROP TRIGGER TR_DELETE_EQUIPOS;
DROP TRIGGER TR_INSERTAR_EQUIPOS;




CREATE OR REPLACE TRIGGER TR_ENTRENADOR
FOR DELETE OR UPDATE ON ENTRENADOR
COMPOUND TRIGGER
    V_CONTADOR_ENTRENADORES NUMBER;
    OLD_ID_EQUIPO ENTRENADOR.ID_EQUIPO%TYPE;
    E_NUMENTRENADORES EXCEPTION;
BEFORE EACH ROW IS
BEGIN 
    OLD_ID_EQUIPO := :OLD.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS 
BEGIN
    SELECT COUNT(ID_INTEGRANTE) INTO V_CONTADOR_ENTRENADORES 
    FROM ENTRENADOR
    WHERE ID_EQUIPO = OLD_ID_EQUIPO;
        
    IF V_CONTADOR_ENTRENADORES <1 THEN
        RAISE E_NUMENTRENADORES;
    END IF;
EXCEPTION
    WHEN E_NUMENTRENADORES THEN 
        RAISE_APPLICATION_ERROR(-20003, 'ERROR: DEBE HABER AL MENOS 1 ENTRENADOR');
    WHEN TOO_MANY_ROWS THEN 
        RAISE_APPLICATION_ERROR(-20001, 'HAY M�S DE UN VALOR');
    WHEN NO_DATA_FOUND THEN 
        RAISE_APPLICATION_ERROR(-20002, 'VALOR NO ENCONTRADO');
    WHEN OTHERS THEN 
        RAISE_APPLICATION_ERROR(-20004, 'ERROR INESPERADO');
END AFTER STATEMENT;
END TR_ENTRENADOR;
--------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_NUM_JUGADORES_MAYOR6
FOR INSERT OR UPDATE ON JUGADOR
COMPOUND TRIGGER
    V_NUMJUGADORES NUMBER(2);
    V_ESTADO COMPETICION.ESTADO%TYPE;
    E_NUMJUGADORINCORRECTO EXCEPTION;
    NEW_ID_EQUIPO JUGADOR.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    NEW_ID_EQUIPO := :NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN 
    SELECT COUNT(*) INTO V_NUMJUGADORES
    FROM JUGADOR
    WHERE ID_EQUIPO = NEW_ID_EQUIPO;

    IF V_NUMJUGADORES > 6 THEN 
        RAISE E_NUMJUGADORINCORRECTO;
    END IF; 
EXCEPTION 
    WHEN TOO_MANY_ROWS THEN 
        RAISE_APPLICATION_ERROR(-20001,'HAY MAS DE UN VALOR');
    WHEN NO_DATA_FOUND THEN 
        RAISE_APPLICATION_ERROR(-20002,'VALOR NO ENCONTRADO');
    WHEN E_NUMJUGADORINCORRECTO THEN 
        RAISE_APPLICATION_ERROR(-20003,'EL NUMERO DE JUGADORES ES INCORRECTO');
    WHEN OTHERS THEN 
        RAISE_APPLICATION_ERROR(-20004,'ERROR INESPERADO');
END AFTER STATEMENT;
END TR_NUM_JUGADORES_MAYOR6;

<<<<<<< Updated upstream

<<<<<<< Updated upstream
CREATE OR REPLACE TRIGGER TR_NO_INGR_JUGADORES
BEFORE INSERT OR UPDATE OR DELETE ON JUGADOR
FOR EACH ROW
DECLARE
  v_estado VARCHAR2(20);
  E_NO_INGR_JUG EXCEPTION;
BEGIN
  SELECT c.estado INTO v_estado

/*---CREACION DE TRIGGER QUE MIRA SI SE PUEDEN MoDIFICAR LOS JUGADORES CUANDO LA COMPETICION SE HA INICIADO----*/
CREATE OR REPLACE TRIGGER lock_jugador_table
BEFORE INSERT OR UPDATE or delete  ON jugador
=======
------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_INSERTAR_JUGADORES
BEFORE INSERT OR UPDATE ON JUGADOR
>>>>>>> Stashed changes
=======


CREATE OR REPLACE TRIGGER TR_INSERTAR_JUGADORES
BEFORE INSERT OR UPDATE ON JUGADOR
>>>>>>> Stashed changes
FOR EACH ROW
DECLARE
    v_estado_competicion COMPETICION.ESTADO%TYPE;
BEGIN
<<<<<<< Updated upstream
    -- Verificar si alguna competici�n en la que el equipo de este jugador participa est� cerrada
=======
    -- Verificar si alguna competiciï¿½n en la que el equipo de este jugador participa estï¿½ cerrada
>>>>>>> Stashed changes
    SELECT MAX(c.estado) INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO = :NEW.ID_EQUIPO;

    IF v_estado_competicion = 'cerrado' THEN
<<<<<<< Updated upstream
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operaci�n. El equipo de este jugador est� en una competici�n cerrada.');
=======
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operacin. El equipo de este jugador esta en una competicion cerrada.');
>>>>>>> Stashed changes
    END IF;
END;
------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_BORRAR_JUGADORES
BEFORE DELETE ON JUGADOR
FOR EACH ROW
DECLARE
    v_estado_competicion COMPETICION.ESTADO%TYPE;
BEGIN
<<<<<<< Updated upstream
    -- Verificar si alguna competici�n en la que el equipo de este jugador participa est� cerrada
=======
    -- Verificar si alguna competiciï¿½n en la que el equipo de este jugador participa estï¿½ cerrada
>>>>>>> Stashed changes
    SELECT MAX(c.estado) INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO = :OLD.ID_EQUIPO;
<<<<<<< Updated upstream
=======

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operacionn. El equipo de este jugador esta en una competicion cerrada.');
    END IF;
END;


>>>>>>> Stashed changes

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operaci�n. El equipo de este jugador est� en una competici�n cerrada.');
    END IF;
END;
--------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_INSERTAR_EQUIPOS
BEFORE INSERT OR UPDATE ON EQUIPO
FOR EACH ROW
DECLARE
    v_estado_competicion COMPETICION.ESTADO%TYPE;
BEGIN
    -- Verificar si alguna competición en la que el equipo estó participando estó cerrada
    SELECT c.ESTADO INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO IN :NEW.ID_EQUIPO AND c.ESTADO = 'cerrado';

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operación. El equipo estó en una competición cerrada.');
    END IF;
EXCEPTION
    -- Capturar excepci�n cuando no hay ninguna competici�n cerrada para este equipo
    WHEN NO_DATA_FOUND THEN
        NULL; -- No hacer nada si no hay competiciones cerradas
END;
---------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_VALIDAR_SUELDO_MINIMO
BEFORE INSERT OR UPDATE ON JUGADOR
FOR EACH ROW
DECLARE
    E_SUELDO_BAJO EXCEPTION;
BEGIN
    IF :NEW.SUELDO < 1134 THEN
        RAISE E_SUELDO_BAJO;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN 
        RAISE_APPLICATION_ERROR(-20022, 'VALOR NO ENCONTRADO');
    WHEN E_SUELDO_BAJO THEN
        RAISE_APPLICATION_ERROR(-20023, 'EL SUELDO NO PUEDE SER MENOR AL MINIMO INTERPROFESIONAL');
    WHEN OTHERS THEN 
        RAISE_APPLICATION_ERROR(-20024, 'ERROR INESPERADO');
END;

----------------------------------------------------------------

CREATE OR REPLACE TRIGGER TR_MAX_SAL
FOR INSERT OR UPDATE ON JUGADOR
COMPOUND TRIGGER
    V_SUELDO_TOTAL_EQUIPO JUGADOR.SUELDO%TYPE;
    E_SUELDOMAXINCORRECTO EXCEPTION;
    NEW_ID_EQUIPO JUGADOR.ID_EQUIPO%TYPE;
    NEW_SUELDO JUGADOR.SUELDO%TYPE;
    OLD_SUELDO JUGADOR.SUELDO%TYPE;
BEFORE EACH ROW IS
BEGIN 
    NEW_ID_EQUIPO := :NEW.ID_EQUIPO;
    NEW_SUELDO := :NEW.SUELDO;
    OLD_SUELDO := :OLD.SUELDO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN 
    SELECT SUM(SUELDO) INTO V_SUELDO_TOTAL_EQUIPO
    FROM JUGADOR
    WHERE ID_EQUIPO = NEW_ID_EQUIPO;
    
    IF V_SUELDO_TOTAL_EQUIPO + NVL(NEW_SUELDO, 0) - NVL(OLD_SUELDO, 0) > 200000 THEN
        RAISE E_SUELDOMAXINCORRECTO;
    END IF;
    
EXCEPTION
    WHEN TOO_MANY_ROWS THEN 
        RAISE_APPLICATION_ERROR(-20001,'HAY MAS DE UN VALOR');
    WHEN NO_DATA_FOUND THEN 
        RAISE_APPLICATION_ERROR(-20002,'VALOR NO ENCONTRADO');
    WHEN E_SUELDOMAXINCORRECTO THEN 
        RAISE_APPLICATION_ERROR(-20003,'LA SUMA DE LOS SUELDOS DE UN MISMO EQUIPO NO PUEDE SUPERAR LOS 200.000€');
    WHEN OTHERS THEN 
        RAISE_APPLICATION_ERROR(-20004,'ERROR INESPERADO'); 
END AFTER STATEMENT;
END;
---------------------------------------------------------------
CREATE OR REPLACE TRIGGER BORRAR_EQ_CLASI_COMP_CERRADA
FOR DELETE ON CLASIFICACION
COMPOUND TRIGGER
    v_estado_competencia COMPETICION.ESTADO%TYPE;
    OLD_ID_EQUIPO EQUIPO.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    OLD_ID_EQUIPO:= :OLD.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN
    SELECT c.ESTADO INTO v_estado_competencia
    FROM COMPETICION c
    INNER JOIN CLASIFICACION cl ON c.ID_COMPETICION = cl.ID_COMPETICION
    WHERE cl.ID_EQUIPO = OLD_ID_EQUIPO
    AND c.ESTADO = 'cerrado';

    IF v_estado_competencia = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede borrar el equipo porque está asociado a una competición cerrada.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
END AFTER STATEMENT;
END BORRAR_EQ_CLASI_COMP_CERRADA;

---------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_DELETE_EQUIPOS
BEFORE DELETE ON EQUIPO
FOR EACH ROW
DECLARE
    v_estado_competicion COMPETICION.ESTADO%TYPE;
BEGIN
    -- Verificar si alguna competición en la que el equipo estó participando estó cerrada
    SELECT MAX(c.ESTADO) INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO IN :OLD.ID_EQUIPO AND c.ESTADO = 'cerrado';

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operación. El equipo estó en una competición cerrada.');
    END IF;
EXCEPTION
    -- Capturar excepción cuando no hay ninguna competición cerrada para este equipo
    WHEN NO_DATA_FOUND THEN
        NULL; -- No hacer nada si no hay competicionesócerradas
END;
-----------------------------------------------------------------------

CREATE OR REPLACE TRIGGER TR_INSERT_CLASIFICACION
FOR INSERT OR UPDATE ON CLASIFICACION
COMPOUND TRIGGER
    v_estado_competicion COMPETICION.ESTADO%TYPE;
    NEW_ID_EQUIPO CLASIFICACION.ID_EQUIPO%TYPE;
BEFORE EACH ROW IS
BEGIN
    NEW_ID_EQUIPO := :NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
BEGIN
    -- Verificar si alguna competición en la que el equipo estó participando estó cerrada
    SELECT c.ESTADO INTO v_estado_competicion
    FROM CLASIFICACION cl
    JOIN COMPETICION c ON cl.ID_COMPETICION = c.ID_COMPETICION
    WHERE cl.ID_EQUIPO IN NEW_ID_EQUIPO AND c.ESTADO = 'cerrado';

    IF v_estado_competicion = 'cerrado' THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar la operación. El equipo estó en una competición cerrada.');
    END IF;
EXCEPTION
    -- Capturar excepción cuando no hay ninguna competición cerrada para este equipo
    WHEN NO_DATA_FOUND THEN
        NULL; -- No hacer nada si no hay competicionesócerradas
END AFTER STATEMENT;
END;



<<<<<<< Updated upstream
CREATE OR REPLACE TRIGGER menor_a_2
AFTER UPDATE OF ESTADO ON COMPETICION
FOR EACH ROW
DECLARE
menos_de_2 NUMBER := 0;
BEGIN
  IF :NEW.ESTADO = 'cerrado' THEN
    FOR equipo IN (SELECT ID_EQUIPO FROM CLASIFICACION WHERE ID_COMPETICION = :NEW.ID_COMPETICION GROUP BY ID_EQUIPO HAVING COUNT(*) < 2)
    LOOP
      menos_de_2 := menos_de_2 + 1;
    END LOOP;

    IF  menos_de_2  > 0 THEN
      RAISE_APPLICATION_ERROR(-20001, 'Hay ' ||   menos_de_2  || ' equipo(s) con menos de 2 jugadores.');
    END IF;
  END IF;
END;
=======
>>>>>>> Stashed changes
