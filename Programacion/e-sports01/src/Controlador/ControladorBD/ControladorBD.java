package Controlador.ControladorBD;

import Controlador.ControladorPrincipal;

import java.sql.Connection;
import java.sql.DriverManager;

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
        this.cp = cp;
        abrirConexion();
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
            // 1. Cargar el controlador de acceso a datos
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Conectarse a la base de datos
            String url = "jdbc:oracle:thin:@SrvOracle:1521:orcl";
            String user = "equipo16";
            String passwd = "equipo16";
            con = DriverManager.getConnection(url, user, passwd);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
