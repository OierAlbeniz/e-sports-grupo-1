package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;

import Modelo.*;


import Modelo.Equipo;
import Modelo.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;

    public ControladorPrincipal() throws Exception {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
    }


    public Integer cantidadEquipos() throws Exception {
        return cb.cantidadEquipos();
    }
    public List<Equipo> llenarEquipos() throws Exception {
        return cb.llenarEquipos();
    }
    public void crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
         cb.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
    }

    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cb.selectEquipo(nombre);

    }

    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return cb.llenarJugadores(x);
    }
    public List<Competicion> llenarCompeticiones() throws Exception {
        return cb.llenarCompeticiones();
    }
    public List<Equipo> llenarEquiposCompeticion(Integer x) throws Exception {
        return cb.llenarEquiposCompeticion(x);
    }
    public void generarCalendario() throws Exception {
        cb.generarCalendario();
    }
    public Usuario buscarUsuario(String user,String password) throws Exception {
        return cb.buscarUsuario(user,password);
    }
    public Equipo buscarEquipo(String nombre) throws Exception {
        return cb.buscarEquipo(nombre);
    }

    public List<Juego> buscarJuegos() throws SQLException {return cb.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return  cb.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{cb.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {return cb.buscarCompeticiones();
    }
    public List<Jugador> llenarJugadoresNombre(String equiposelecionado) throws SQLException
    {
        return  cb.llenarJugadoresNombre(equiposelecionado);
    }
    public List<String> buscarPatrocinador() throws SQLException {
        return cb.buscarPatrocinador();
    }


    public Usuario crearUsuario(String nombre,String contrasena,String tipoUsuario) throws Exception {
        cb.crearUsuario(nombre,contrasena,tipoUsuario);
        return null;
    }
    public Jugador actualizarJugador(String nombre, String equipo) throws Exception {
        cb.actualizarJugador( nombre, equipo);
        Jugador buscarDatos = cb.actualizarJugador(nombre, equipo);

        return buscarDatos;
    }
    public void editarJugadorConfir(String nombre,String primerApellido,String segundoApellido,double sueldo,String nacionalidad,LocalDate fechaNacimiento,String nickname,String rol,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {


        cb.editarJugadorConfir(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }
    public void eliminarJugador(String nombre,String equipo) throws Exception {
        cb.eliminarJugador(nombre,equipo);
    }
    public List<Jugador> llenarJugadoresS(String tipo) throws Exception {
        cb.llenarJugadoresS(tipo);
        List<Jugador> listaJugadores = cb.llenarJugadoresS(tipo);
        return listaJugadores;
    }
    public void borrarEquipo(String nombre) throws SQLException {
        cb.borrarEquipo(nombre);
    }
    public List<Equipo> llenarEquiposS(String tipo) throws Exception {
        cb.llenarEquiposS(tipo);
        List<Equipo> listaEquipo = cb.llenarEquiposS(tipo);
        return listaEquipo;
    }
    public List<Entrenador> llenarEntrenador(String tipo) throws Exception {
        cb.llenarEntrenador(tipo);
        List<Entrenador> listaEntrenador = cb.llenarEntrenador(tipo);
        return listaEntrenador;
    }
    public List<Asistente> llenarAsistente(String tipo) throws Exception {
        cb.llenarAsistente(tipo);
        List<Asistente> listaAsistente = cb.llenarAsistente(tipo);
        return listaAsistente;
    }

    public List<Competicion> llenarCompeticion(String tipo) throws Exception {
        cb.llenarCompeticion(tipo);
        List<Competicion> listaCompeticion = cb.llenarCompeticion(tipo);
        return listaCompeticion;
    }
    public List<Juego> llenarJuegos(String tipo) throws Exception {
        cb.llenarJuegos(tipo);
        List<Juego> listaJuego = cb.llenarJuegos(tipo);
        return listaJuego;
    }

    public void crearEquipo(String nombre,LocalDate fecha, String patrocinador, String competicion) throws Exception {
        cb.crearEquipo(nombre, fecha, patrocinador,competicion);
    }
    public void editarEquipo(String nombreAntiguo,String nombreNuevo,LocalDate fechacambio,String VincularNuevo,String Desvincular) throws Exception {
        cb.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
    }
    public Competicion buscarCompeticion(String c) throws Exception {
        return cb.buscarCompeticion(c);
    }
    public List<Integer> buscarJornadas(Integer j) throws Exception {
        return cb.buscarJornadas(j);
    }
    public List<Enfrentamiento> buscarEnfrentamientos(Integer j, Integer c) throws Exception {
        return cb.buscarEnfrentamientos(j,c);
    }
    public void guardarResultados(Integer idEnfJor, int resultLocal, int resultVisitante) throws Exception {
        cb.guardarResultados(idEnfJor, resultLocal, resultVisitante);
    }

    public void actualizarTablaClasificacion(Enfrentamiento enfrentamiento, int resultLocal, int resultVisitante) throws Exception {
        cb.actualizarTablaClasificacion(enfrentamiento,resultLocal, resultVisitante );
    }

    public void llenarResultados(JTextField resultadoLocal, JTextField resultadoVisitante, Integer idEnfJor) {
        cb.llenarResultados(resultadoLocal, resultadoVisitante, idEnfJor);
    }
    public List<Clasificacion> obtenerClasificacionesPorCompeticion(Integer idCompeticion) throws Exception {
        return cb.obtenerClasificacionesPorCompeticion(idCompeticion);
    }
    public Integer buscarUltimaJornada(Integer competicionID) throws Exception {
        return cb.buscarUltimaJornada(competicionID);
    }
    public void insertarPatrocinador(Patrocinador p) throws Exception {cb.insertarPatrocinador(p);}
    public List<String> buscarEquipos() throws SQLException {return cb.buscarEquipos();}
    public void borrarPatrocinador(String nombre) throws Exception{cb.borrarPatrocinador(nombre);}
    public void editarPatrocinador(String nombreNuevo) throws Exception{cb.editarPatrocinador(nombreNuevo);}
    public Patrocinador buscarPatrocinadorEliminar(String nombre) throws Exception{return cb.buscarPatrocinadorEliminar(nombre);}


}
