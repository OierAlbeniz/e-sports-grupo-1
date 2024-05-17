package Controlador.ControladorBD;

import Controlador.ControladorPrincipal;

import Controlador.ControladorVista.ControladorVJuego;
import Modelo.*;

import Modelo.Equipo;
import Modelo.Usuario;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.time.LocalDate;

import java.util.ArrayList;
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

    public ControladorBD() {

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



    public Integer cantidadEquipos() throws Exception {
        return ctequipo.cantidadEquipos();
    }

    public List<Equipo> llenarEquipos() throws Exception {
        return ctequipo.llenarEquipos();
    }

    public Equipo buscarEquipo(Integer equipo) throws Exception {
        return ctequipo.buscarEquipo(equipo);
    }

    public Patrocinador buscarPatrocinador(Integer idpatrocinador) throws Exception {
        return ctpatrocinador.buscarPatrocinador(idpatrocinador);
    }

    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return ctjugador.llenarJugadores(x);
    }

    public List<Competicion> llenarCompeticiones() throws Exception {
        return ctcompeticion.llenarCompeticiones();
    }

    public List<Equipo> llenarEquiposCompeticion(Integer competicion) throws Exception {
        List<String> idEquipos = new ArrayList<>();
        idEquipos = ctclasificacion.llenarEquiposCompeticion(competicion);
        List<Equipo> listaEquipos = new ArrayList<>();
        for (int x = 0; x < idEquipos.size(); x++) {
            listaEquipos = ctequipo.llenarEquiposporID(idEquipos.get(x));
        }
        return listaEquipos;
    }

    public void generarCalendario() throws Exception {
        try {
            java.sql.CallableStatement stmt = con.prepareCall("{call generar_calendario}");
            stmt.execute();
            System.out.println("Calendario generado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al generar el calendario: " + ex.getMessage());
        }
    }

    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol, String equipo) throws Exception {
        return ctjugador.crearJugador(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, equipo);
    }

    public ArrayList selectEquipo(String nombre) throws Exception {
        return ctequipo.selectEquipo(nombre);
    }

    public Usuario buscarUsuario(String user) throws Exception {
        return ctUsuario.buscarUsuario(user);
    }

    public List<Juego> buscarJuegos() throws SQLException {
        return ctjuego.buscarJuegos();
    }

    public Juego buscarJuego(String nombre) throws Exception {
        return ctjuego.buscarJuego(nombre);
    }

    public void insertarCompeticion(Competicion c) throws Exception {
        ctcompeticion.insertarCompeticion(c);
    }

    public List<String> buscarCompeticiones() throws SQLException {

        return ctcompeticion.buscarCompeticiones();
    }
}