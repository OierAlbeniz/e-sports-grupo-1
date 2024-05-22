package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;

import Modelo.*;


import Modelo.Equipo;
import Modelo.Usuario;

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
    public void crearMostrarPatrocinador() {
        cvpatrocinador=new ControladorVPatrocinador(this);
        cvpatrocinador.crearMostrar();
    }
    public void crearMostrarEquipos() {
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
    public void crearMostrarJuegos() {
        cvjuego=new ControladorVJuego(this);
        cvjuego.crearMostrar();
    }
    public void crearMostrarStaff() {
        cvstaff=new ControladorVStaff(this);
        cvstaff.crearMostrar();
    }
    public void crearMostrarCompeticiones() throws Exception {
        cvcompeticion=new ControladorVCompeticion(this);
        cvcompeticion.crearMostrar();
    }

    public Usuario buscarUsuario(String user, String password) throws Exception {
        return cp.buscarUsuario(user, password);
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
    public void borrarCompeticion(int idCompeticion) throws Exception {cp.borrarCompeticion(idCompeticion);}
    public String buscarCompeticionPorNombre(String nombre) throws Exception {return  cp.buscarCompeticionPorNombre(nombre);}
    public Competicion obtenerCompeticion(String nombre) throws Exception{return cp.obtenerCompeticion(nombre);}

    public Juego buscarJuegoPorNombreCompeticion(String nombreCompeticion) throws Exception {return cp.buscarJuegoPorNombreCompeticion(nombreCompeticion);}
    public void modificarCompeticion(int idCompeticion, String nombre, LocalDate fechaInicio, LocalDate fechaFin, String estado, int idJuego) throws Exception {cp.modificarCompeticion(idCompeticion,nombre,fechaInicio,fechaFin,estado,idJuego);}

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
    public List<String> buscarCompeticiones() throws Exception {return cp.buscarCompeticiones();}

    public List<Jugador> llenarJugadoresNombre(String equiposelecionado) throws SQLException
    {
        return  cp.llenarJugadoresNombre(equiposelecionado);
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

}
