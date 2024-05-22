package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;

import Modelo.*;


import Modelo.Equipo;
import Modelo.Usuario;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVLogin cvlogin;
    private ControladorVEditar cveditar;
    private ControladorVClasificacion cvclasificacion;
    private ControladorVInsertResultados cvInsertResultados;
    private ControladorVP cvp;
    private ControladorVPatrocinador cvpatrocinador;
    private ControladorVEquipo cvequipo;
    private ControladorVJugador cvjugador;
    private ControladorVJuego cvjuego;
    private ControladorVStaff cvstaff;
    private ControladorVCompeticion cvcompeticion;

    public ControladorVista(ControladorPrincipal cp) throws Exception {
        this.cp = cp;
        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();
        cvp=new ControladorVP(this);
    }

    public void crearMostrarVP() {
        cvp.crearMostrar();
    }
    public void crearMostrarEditar() {
        cveditar=new ControladorVEditar(this);
        cveditar.crearMostrar();
    }
    public void crearMostrarClasificacion() {
        cvclasificacion=new ControladorVClasificacion(this);
        cvclasificacion.crearMostrar();
    }
    public void crearMostrarInsertResultados() {
        cvInsertResultados=new ControladorVInsertResultados(this);
        cvInsertResultados.crearMostrar();
    }
    public void crearMostrarPatrocinador() {
        cvpatrocinador=new ControladorVPatrocinador(this);
        cvpatrocinador.crearMostrar();
    }
    public void crearMostrarEquipos() {
        cvequipo=new ControladorVEquipo(this);
        cvequipo.crearMostrar();
    }
    public List<Competicion> llenarCompeticion(String tipo) throws Exception {
        cp.llenarCompeticion(tipo);
        List<Competicion> listaCompeticion = cp.llenarCompeticion(tipo);
        return listaCompeticion;
    }
    public void crearMostrarJugadores() {
        cvjugador=new ControladorVJugador(this);
        cvjugador.crearMostrar();
    }
    public void crearMostrarJuegos() {
        cvjuego=new ControladorVJuego(this);
        cvjuego.crearMostrar();
    }
    public void crearMostrarStaff() {
        cvstaff=new ControladorVStaff(this);
        cvstaff.crearMostrar();
    }
    public void crearMostrarCompeticiones() {
        cvcompeticion=new ControladorVCompeticion(this);
        cvcompeticion.crearMostrar();
    }

    public Usuario buscarUsuario(String user) throws Exception {
        return cp.buscarUsuario(user);
    }
    public Competicion buscarCompeticion(String c) throws Exception {
        return cp.buscarCompeticion(c);
    }
    public List<Integer> buscarJornadas(Integer j) throws Exception {
        return cp.buscarJornadas(j);
    }
    public List<Enfrentamiento> buscarEnfrentamientos(Integer j, Integer c) throws Exception {
        return cp.buscarEnfrentamientos(j,c);
    }

    public Integer cantidadEquipos() throws Exception {
        return cp.cantidadEquipos();
    }
    public List<Equipo> llenarEquipos() throws Exception {
        return cp.llenarEquipos();
    }
    public void crearEquipo(String nombre, LocalDate fecha, String patrocinador, String competicion) throws Exception {
        cp.crearEquipo(nombre, fecha, patrocinador,competicion);
    }
    public void editarEquipo(String nombreAntiguo,String nombreNuevo,LocalDate fechacambio,String VincularNuevo,String Desvincular) throws Exception {
        cp.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
    }
    public List<Entrenador> llenarEntrenador(String tipo) throws Exception {
        cp.llenarEntrenador(tipo);
        List<Entrenador> listaEntrenador = cp.llenarEntrenador(tipo);
        return listaEntrenador;
    }
    public List<Asistente> llenarAsistente(String tipo) throws Exception {
        cp.llenarAsistente(tipo);
        List<Asistente> listaAsistente = cp.llenarAsistente(tipo);
        return listaAsistente;
    }
    public List<Juego> llenarJuegos(String tipo) throws Exception {
        cp.llenarJuegos(tipo);
        List<Juego> listaJuego = cp.llenarJuegos(tipo);
        return listaJuego;
    }
    public List<Equipo> llenarEquiposS(String tipo) throws Exception {
        cp.llenarEquiposS(tipo);
        List<Equipo> listaEquipo = cp.llenarEquiposS(tipo);
        return listaEquipo;
    }
    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return cp.llenarJugadores(x);
    }
    public List<Jugador> llenarJugadoresS(String tipo) throws Exception {
        cp.llenarJugadoresS(tipo);
        List<Jugador> listaJugadores = cp.llenarJugadoresS(tipo);
        return listaJugadores;
    }
    public List<Competicion> llenarCompeticiones() throws Exception {
        return cp.llenarCompeticiones();
    }
    public List<Equipo> llenarEquiposCompeticion(Integer x) throws Exception {
        return cp.llenarEquiposCompeticion(x);
    }
    public void generarCalendario() throws Exception {
        cp.generarCalendario();
    }
    public void cerrarCompeticiones() throws Exception {
        cp.cerrarCompeticiones();
    }


    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        return cp.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
    }
    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cp.selectEquipo(nombre);
    }
    public void borrarEquipo(String nombre) throws SQLException {
        cp.borrarEquipo(nombre);
    }
    public List<String> buscarPatrocinador() throws SQLException {
        return cp.buscarPatrocinador();
    }
    public Equipo buscarEquipo(String nombre) throws Exception {
        return cp.buscarEquipo(nombre);
    }

    public List<Juego> buscarJuegos() throws SQLException {return cp.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return cp.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{cp.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {return cp.buscarCompeticiones();}

    public void asignarEquiposEnfrentamientos() throws Exception{
        cp.asignarEquiposEnfrentamientos();
    }

    public void guardarResultados(Integer idEnfJor, int resultLocal, int resultVisitante) throws Exception {
        cp.guardarResultados(idEnfJor, resultLocal, resultVisitante);
    }

    public void actualizarTablaClasificacion(Enfrentamiento enfrentamiento, int resultLocal, int resultVisitante) throws Exception {
    cp.actualizarTablaClasificacion(enfrentamiento,resultLocal, resultVisitante );
    }

    public void llenarResultados(JTextField resultadoLocal, JTextField resultadoVisitante, Integer idEnfJor) {
        cp.llenarResultados(resultadoLocal, resultadoVisitante, idEnfJor);
    }



    public int obtenerJornadasJugadas(Integer idEquipo, Integer idCompeticion) throws Exception {
        return cp.obtenerJornadasJugadas(idEquipo, idCompeticion);
    }

    public List<Clasificacion> obtenerClasificacionesPorCompeticion(Integer idCompeticion) throws Exception {
        return cp.obtenerClasificacionesPorCompeticion(idCompeticion);
    }
}
