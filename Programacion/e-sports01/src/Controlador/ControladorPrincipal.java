package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;
import Modelo.Competicion;
import Modelo.Juego;
import Modelo.Equipo;
import Modelo.Usuario;

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
    public Usuario buscarUsuario(String user) throws Exception {
        return cb.buscarUsuario(user);
    }
    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        return cb.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
    }

    public List<Juego> buscarJuegos() throws SQLException {return cb.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return  cb.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{cb.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {return cb.buscarCompeticiones(); }
    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cb.selectEquipo(nombre);
    }

}
