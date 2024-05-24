package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;

import Modelo.*;


import Modelo.Equipo;
import Modelo.Usuario;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVUsuario cvu;
    private ControladorVLogin cvlogin;
    private ControladorVEditar cveditar;
    private ControladorVP cvp;
    private ControladorVPatrocinador cvpatrocinador;
    private ControladorVEquipo cvequipo;
    private ControladorVJugador cvjugador;
    private ControladorVJuego cvjuego;
    private ControladorVStaff cvstaff;
    private ControladorVConsultas cvconsultas;
    private ControladorVCompeticion cvcompeticion;
    private ControladorVClasificacion cvclasificacion;
    private ControladorVInsertResultados cvInsertResultados;
    private ControladorVUltJornada cvultJornada;
    private Usuario usuario=null;



    public ControladorVista(ControladorPrincipal cp) throws Exception {
        this.cp = cp;
        cvp=new ControladorVP(this);
        crearMostrarVinicioSesion();
    }
    public void crearMostrarVinicioSesion() {
        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();

    }
    public void crearMostrarVP() {
        cvp.crearMostrar(usuario);
    }
    public void crearMostrarVPlogin(Usuario user) {
        usuario=user;
        cvp.crearMostrar(user);
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
    public void crearMostrarUltJornada() {
        cvultJornada=new ControladorVUltJornada(this);
        cvultJornada.crearMostrar();
    }
    public void crearMostrarPatrocinador() {
        cvpatrocinador=new ControladorVPatrocinador(this);
        cvpatrocinador.crearMostrar();
    }
    public void crearConsultas() {
        cvconsultas=new ControladorVConsultas(this);
        cvconsultas.crearMostrar();
    }
    public void crearMostrarEquipos() throws Exception {
        cvequipo=new ControladorVEquipo(this);
        cvequipo.crearMostrar();
    }
    public void crearMostrarUsuario() {
        cvu=new ControladorVUsuario(this);
        cvu.crearMostrarUsuario();
    }
    public void crearMostrarJugadores() {
        cvjugador=new ControladorVJugador(this);
        cvjugador.crearMostrar();
    }
    public void crearMostrarEntrenador() {
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

    public Usuario buscarUsuario(String user, String password) throws Exception {
        return cp.buscarUsuario(user, password);
    }
    public Equipo buscarEquipo(String nombre) throws Exception {
        return cp.buscarEquipo(nombre);
    }



    public Integer cantidadEquipos() throws Exception {
        return cp.cantidadEquipos();
    }
    public List<Equipo> llenarEquipos() throws Exception {
        return cp.llenarEquipos();
    }
    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return cp.llenarJugadores(x);
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

    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        cp.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
        return null;
    }
    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cp.selectEquipo(nombre);
    }
    public List<Juego> buscarJuegos() throws SQLException {return cp.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return cp.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{cp.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {return cp.buscarCompeticiones();}

    public List<Jugador> llenarJugadoresNombre(String equiposelecionado) throws SQLException
    {
        return  cp.llenarJugadoresNombre(equiposelecionado);
    }
    public List<String> buscarPatrocinador() throws SQLException {
        return cp.buscarPatrocinador();
    }
    public void eliminarJugador(String nombre,String equipo) throws Exception {
        cp.eliminarJugador(nombre,equipo);
    }
    public Usuario crearUsuario(String nombre,String contrasena,String tipoUsuario) throws Exception {
        cp.crearUsuario(nombre,contrasena, tipoUsuario);
        return null;
    }

    public Jugador actualizarJugador(String nombre, String equipo) throws Exception {
        cp.actualizarJugador( nombre, equipo);
        Jugador buscarDatos = cp.actualizarJugador(nombre, equipo);

        return buscarDatos;
    }

    public void editarJugadorConfir(String nombre,String primerApellido,String segundoApellido,double sueldo,String nacionalidad,LocalDate fechaNacimiento,String nickname,String rol,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {


        cp.editarJugadorConfir(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }

    public void nombreequipo(String nombre){
        cvjugador.nombreEquipo(nombre);
    }
    public void borrarEquipo(String nombre) throws SQLException {
        cp.borrarEquipo(nombre);
    }



    public List<Jugador> llenarJugadoresS(String tipo) throws Exception {
        cp.llenarJugadoresS(tipo);
        List<Jugador> listaJugadores = cp.llenarJugadoresS(tipo);
        return listaJugadores;
    }
    public List<Equipo> llenarEquiposS(String tipo) throws Exception {
        cp.llenarEquiposS(tipo);
        List<Equipo> listaEquipo = cp.llenarEquiposS(tipo);
        return listaEquipo;
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
    public List<Competicion> llenarCompeticion(String tipo) throws Exception {
        cp.llenarCompeticion(tipo);
        List<Competicion> listaCompeticion = cp.llenarCompeticion(tipo);
        return listaCompeticion;
    }
    public List<Juego> llenarJuegos(String tipo) throws Exception {
        cp.llenarJuegos(tipo);
        List<Juego> listaJuego = cp.llenarJuegos(tipo);
        return listaJuego;
    }


    public void crearEquipo(String nombre, LocalDate fecha, Patrocinador patrocinador, Competicion competicion) throws Exception {
        cp.crearEquipo(nombre, fecha, patrocinador,competicion);
    }
    public void editarEquipo(String nombreAntiguo,String nombreNuevo,LocalDate fechacambio,String VincularNuevo,String Desvincular) throws Exception {
        cp.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
    }



    public void editarPatrocinadorConfir(String nombre,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {

        cp.editarPatrocinadorConfir(nombre, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }
    public Patrocinador actualizarPatrocinador(String nombre, String equipo) throws Exception {
        cp.actualizarPatrocinador( nombre, equipo);
        Patrocinador buscarDatosPatrocinador = cp.actualizarPatrocinador(nombre, equipo);

        return buscarDatosPatrocinador;
    }

    public List<Patrocinador> llenarPatrocinadorNombre(String equiposelecionado) throws SQLException
    {
        return  cp.llenarPatrocinadorNombre(equiposelecionado);
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
    public void guardarResultados(Integer idEnfJor, int resultLocal, int resultVisitante) throws Exception {
        cp.guardarResultados(idEnfJor, resultLocal, resultVisitante);
    }

    public void actualizarTablaClasificacion(Enfrentamiento enfrentamiento, int resultLocal, int resultVisitante) throws Exception {
        cp.actualizarTablaClasificacion(enfrentamiento,resultLocal, resultVisitante );
    }

    public void llenarResultados(JTextField resultadoLocal, JTextField resultadoVisitante, Integer idEnfJor) {
        cp.llenarResultados(resultadoLocal, resultadoVisitante, idEnfJor);
    }

    public List<Clasificacion> obtenerClasificacionesPorCompeticion(Integer idCompeticion) throws Exception {
        return cp.obtenerClasificacionesPorCompeticion(idCompeticion);
    }

    public Integer buscarUltimaJornada(Integer competicionID) throws Exception {
        return cp.buscarUltimaJornada(competicionID);
    }

    public void eliminarCompeticion(Competicion c) throws Exception {
        cp.eliminarCompeticion(c);
    }

    public void updateEquipoJugador(String nombre, String patrocinador, String competicion, LocalDate fecha) throws Exception {
        cp.updateEquipoJugador(nombre, patrocinador, competicion, fecha);
    }

    public Patrocinador buscarPatrocinadorNombre(String nombrePatrocinador) {
        return  cp.buscarPatrocinadorNombre(nombrePatrocinador);
    }

    public int buscarIdJuegoPorNombre(String nuevoJuego) throws Exception {
        return cp.buscarIdJuegoPorNombre(nuevoJuego);
    }

    public void modificarCompeticion(Integer idCompeticion, String nuevoNombre, Date date, Date date1, String nuevoEstado, int idJuego) throws Exception {
        cp.modificarCompeticion(idCompeticion,nuevoNombre, date, date1, nuevoEstado, idJuego);
    }

    public void modificarJuego(Juego j)throws Exception  {
        cp.modificarJuego(j);
    }

    public void eliminarJuego(Juego j)throws Exception  {
        cp.eliminarJuego(j);
    }

    public void insertarJuego(Juego j) throws Exception {
        cp.insertarJuego(j);
    }

    public List<Competicion> buscarCompeticionesPorJuego(Integer idJuego) throws Exception {
        return cp.buscarCompeticionesPorJuego(idJuego);
    }

    public void vincularJuegoACompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cp.vincularJuegoACompeticion(idJuego, idCompeticion);
    }

    public void desvincularJuegoDeCompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cp.desvincularJuegoDeCompeticion(idJuego, idCompeticion);
    }

    public void insertarPatrocinador(Patrocinador p) throws Exception {cp.insertarPatrocinador(p);}
    public void borrarPatrocinador(String nombre) throws Exception{cp.borrarPatrocinador(nombre);}
    public void editarPatrocinador(String nombreNuevo) throws Exception{cp.editarPatrocinador(nombreNuevo);}
    public Patrocinador buscarPatrocinadorEliminar(String nombre) throws Exception{return cp.buscarPatrocinadorEliminar(nombre);}

}