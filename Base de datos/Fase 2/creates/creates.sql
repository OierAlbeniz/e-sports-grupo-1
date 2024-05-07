/* ELIMINACION DE TABLAS TABLAS */
DROP TABLE COMPETICION CASCADE CONSTRAINTS;
DROP TABLE JUEGO CASCADE CONSTRAINTS;
DROP TABLE JORNADA CASCADE CONSTRAINTS;
DROP TABLE ENFRENTAMIENTO CASCADE CONSTRAINTS;
DROP TABLE EQUIPO CASCADE CONSTRAINTS;
DROP TABLE PATROCINADOR CASCADE CONSTRAINTS;
DROP TABLE USUARIO CASCADE CONSTRAINTS;
DROP TABLE JUGADOR CASCADE CONSTRAINTS;
DROP TABLE ENTRENADOR CASCADE CONSTRAINTS;
DROP TABLE ASISTENTE CASCADE CONSTRAINTS;
DROP TABLE CLASIFICACION CASCADE CONSTRAINTS;



/*---------CREACION DE TABLA JUEGO--------*/

    CREATE TABLE JUEGO(
        ID_JUEGO NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30) not null,
        EMPRESA VARCHAR2(30) not null,
        FECHA_LANZAMIENTO DATE,
        CONSTRAINT PK_JUEGO PRIMARY KEY (ID_JUEGO)
    );

    /*---------CREACION DE TABLA COMPETICION--------*/

    CREATE TABLE COMPETICION(
        ID_COMPETICION NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR(40) not null,
        FECHA_INICIO DATE not null,
        FECHA_FIN DATE not null,
        ESTADO VARCHAR2(20) CHECK (ESTADO IN ('abierto', 'cerrado')) not null,
        ID_JUEGO NUMBER(3),
        CONSTRAINT PK_COMPETICION PRIMARY KEY (ID_COMPETICION),
        CONSTRAINT FK_JUEGO FOREIGN KEY (ID_JUEGO) REFERENCES JUEGO(ID_JUEGO)
    );

    /*---------CREACION DE TABLA JORNADA--------*/

    CREATE TABLE JORNADA(
        ID_JOR_COMP NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        ID_JORNADA NUMBER(3),
        ID_COMPETICION NUMBER(3),
        FECHA_ENFRENTAMIENTO DATE,
        CONSTRAINT PK_JORNADA PRIMARY KEY(ID_JOR_COMP),
        CONSTRAINT FK_COMPETICIONN FOREIGN KEY (ID_COMPETICION) REFERENCES COMPETICION(ID_COMPETICION)
    );

    /*---------CREACION DE TABLA PATROCINADOR--------*/

    CREATE TABLE PATROCINADOR(
        ID_PATROCINADOR NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30) not null,
        CONSTRAINT PATRO_ID_PK PRIMARY KEY (ID_PATROCINADOR)
    );

    /*---------CREACION DE TABLA EQUIPO--------*/

    CREATE TABLE EQUIPO(
        ID_EQUIPO NUMBER(3)GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30) not null,
        FECHA_FUNDACION DATE,
        ID_PATROCINADOR NUMBER(3),
        CONSTRAINT EQUI_ID_PK PRIMARY KEY (ID_EQUIPO),
        CONSTRAINT FK_PATROCINADOR FOREIGN KEY (ID_PATROCINADOR) REFERENCES PATROCINADOR(ID_PATROCINADOR)
    );

    /*---------CREACION DE TABLA clasificacion--------*/

    CREATE TABLE CLASIFICACION(
        ID_CLASIFICACION NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        ID_COMPETICION NUMBER(3),
        ID_EQUIPO NUMBER(3),
        PUNTOS NUMBER(3) DEFAULT 0,
        CONSTRAINT ID_CLASIFICACION PRIMARY KEY (ID_CLASIFICACION),
        CONSTRAINT FK_ID_COMPETICION FOREIGN KEY (ID_COMPETICION) REFERENCES COMPETICION(ID_COMPETICION),
        CONSTRAINT FK_ID_EQUIPO FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
    );

    /*---------CREACION DE TABLA ENFRENTAMIENTO--------*/

    CREATE TABLE ENFRENTAMIENTO (
        ID_ENF_JOR NUMBER(3)  GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        ID_JOR_COMP NUMBER(3),
        ID_ENFRENTAMIENTO NUMBER(3),--ESTA PROGRAMARLA EN JAVA
        HORA VARCHAR2(5),
        RESULTADO_LOCAL NUMBER(2),
        RESULTADO_VISITANTE NUMBER(2),
        ID_LOCAL NUMBER(3),
        ID_VISITANTE NUMBER(3),
        CONSTRAINT PK_ID_ENFRENTAMIENTO PRIMARY KEY (ID_ENF_JOR),
        CONSTRAINT FK_ID_JOR_COMP FOREIGN KEY (ID_JOR_COMP) REFERENCES JORNADA(ID_JOR_COMP),
        CONSTRAINT FK_EQUIPO_LOCAL FOREIGN KEY (ID_LOCAL) REFERENCES EQUIPO(ID_EQUIPO),
        CONSTRAINT FK_EQUIPO_VISITANTE FOREIGN KEY (ID_VISITANTE) REFERENCES EQUIPO(ID_EQUIPO)
    );  

    /*---------CREACION DE TABLA USUARIO--------*/

    CREATE TABLE USUARIO(
        ID_USUARIO NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30)not null,
        CONTRASENA VARCHAR2(50)not null,
        TIPO VARCHAR2(30) CHECK (tipo IN ('administrador', 'usuario')),
        CONSTRAINT USU_ID_PK PRIMARY KEY (ID_USUARIO)
    );

    /*---------CREACION DE TABLA JUGADOR--------*/

    CREATE TABLE JUGADOR(
        ID_INTEGRANTE NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30) not null,
        APELLIDO1 VARCHAR2(30) not null,
        APELLIDO2 VARCHAR2(30) not null,
        SUELDO NUMBER(6) not null,
        NACIONALIDAD VARCHAR2(30)not null,
        FECHA_NACIMIENTO DATE not null,
        NICKNAME VARCHAR2(30) not null,
        ROL VARCHAR2(30) not null,
        ID_EQUIPO NUMBER(3),
        CONSTRAINT JUGA_ID_PK PRIMARY KEY (ID_INTEGRANTE),
        CONSTRAINT JUGA_ID_EQUI_FK FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
    );

    /*---------CREACION DE TABLA ENTRENADOR--------*/

    CREATE TABLE ENTRENADOR(
        ID_INTEGRANTE NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30) not null,
        APELLIDO1 VARCHAR2(30) not null,
        APELLIDO2 VARCHAR2(30) not null,
        SUELDO NUMBER(6) not null,
        ID_EQUIPO NUMBER(3),
        CONSTRAINT ENTRE_ID_PK PRIMARY KEY (ID_INTEGRANTE),
        CONSTRAINT ENTRE_ID_EQUI_FK FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
    );

    /*---------CREACION DE TABLA ASISTENTE--------*/

    CREATE TABLE ASISTENTE(
        ID_INTEGRANTE NUMBER(3) GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
        NOMBRE VARCHAR2(30),
        APELLIDO1 VARCHAR2(30),
        APELLIDO2 VARCHAR2(30),
        SUELDO NUMBER(6),
        ID_EQUIPO NUMBER(3),
        CONSTRAINT ASIST_ID_PK PRIMARY KEY (ID_INTEGRANTE),
        CONSTRAINT ASIST_ID_EQUI_FK FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
    );
