package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;
import Modelo.Competicion;
import Modelo.Juego;
import Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;

    public ControladorPrincipal() {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
    }
    public Usuario buscarUsuario(String user) throws Exception {
        return cb.buscarUsuario(user);
    }

    public List<Juego> buscarJuegos() throws SQLException {return cb.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return  cb.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{cb.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {return cb.buscarCompeticiones(); }


    }
