package Controlador.ControladorBD;

import Modelo.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaJugador {
    private Connection con;
    private ControladorBD cb;
    public ControladorTablaJugador(Connection con) {
        this.con = con;
    }

    public List<Jugador> llenarJugadores(Integer equipo) throws Exception {

        List<Jugador> listaJugadores=new ArrayList<>();
        String plantilla5 = "SELECT id_integrante, nombre, apellido1, apellido2, sueldo, nacionalidad, fecha_nacimiento, nickname, rol, id_equipo FROM jugador WHERE id_equipo=?";

        PreparedStatement statement = con.prepareStatement(plantilla5);
        statement.setInt(1, equipo);
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            Jugador jugador=new Jugador();
            jugador.setIdIntegrante(rs.getInt("id_equipo"));
            jugador.setNombre(rs.getString("nombre"));
            jugador.setApellido1(rs.getString("apellido1"));
            jugador.setApellido2(rs.getString("apellido2"));
            jugador.setSueldo(rs.getDouble("sueldo"));
            jugador.setNacionalidad(rs.getString("nacionalidad"));
            jugador.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            jugador.setNickname(rs.getString("nickname"));
            jugador.setRol(rs.getString("rol"));
            Integer id_Equipo = rs.getInt("id_equipo");
            //jugador.setEquipo(cb.buscarEquipo(id_Equipo));
            listaJugadores.add(jugador);
            System.out.println(rs.getString("nombre"));
            System.out.println(rs.getString("id_equipo"));
        }

        statement.close();
        return listaJugadores;
    }
}