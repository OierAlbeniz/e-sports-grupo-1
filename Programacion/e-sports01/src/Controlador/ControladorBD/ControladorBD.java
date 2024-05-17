package Controlador.ControladorBD;

import Controlador.ControladorPrincipal;
import Controlador.ControladorVista.ControladorVJuego;
import Modelo.Competicion;
import Modelo.Juego;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ControladorBD {
    private ControladorTablaAsistente ctasistente;
    private ControladorTablaClasificacion ctclasificacion;
    private ControladorTablaCompeticion ctcompeticion;
    private ControladorTablaEnfrentamiento ctenfrentamiento;
    private ControladorTablaEntrenador ctentrenador;
    private ControladorTablaEquipo ctequipo;
    private ControladorTablaJornada ctjornada;
    private ControladorTablaJuego ctjuego;
    private ControladorTablaJugador ctjugador;
    private ControladorTablaPatrocinador ctpatrocinador;
    private ControladorTablaUsuario ctUsuario;
    private ControladorPrincipal cp;
    private Connection con;
    public ControladorBD(ControladorPrincipal cp) {
        abrirConexion();
        this.cp = cp;
        ctUsuario = new ControladorTablaUsuario(con);
        ctpatrocinador = new ControladorTablaPatrocinador(con);
        ctjugador = new ControladorTablaJugador(con);
        ctjuego = new ControladorTablaJuego(con);
        ctjornada = new ControladorTablaJornada(con);
        ctequipo = new ControladorTablaEquipo(con);
        ctentrenador = new ControladorTablaEntrenador(con);
        ctenfrentamiento = new ControladorTablaEnfrentamiento(con);
        ctcompeticion = new ControladorTablaCompeticion(con);
        ctclasificacion = new ControladorTablaClasificacion(con);
        ctasistente = new ControladorTablaAsistente(con);
    }
    public void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
            String user = "equipo16";
            String passwd = "equipo16";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("conexion abierta");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("conexion erronea");
        } catch (SQLException e) {
        }
    }

    public Usuario buscarUsuario(String user ) throws Exception {
        return ctUsuario.buscarUsuario(user);
    }

    public List<Juego> buscarJuegos() throws SQLException {return  ctjuego.buscarJuegos();}
    public Juego buscarJuego(String nombre) throws Exception{return ctjuego.buscarJuego(nombre);}
    public void insertarCompeticion(Competicion c) throws Exception{ctcompeticion.insertarCompeticion(c);}
    public List<String> buscarCompeticiones() throws SQLException {

        return ctcompeticion.buscarCompeticiones(); }

    }
