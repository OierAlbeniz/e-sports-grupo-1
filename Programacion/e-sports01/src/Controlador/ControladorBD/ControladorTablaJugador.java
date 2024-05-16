package Controlador.ControladorBD;

import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class ControladorTablaJugador {
    private Connection con;
    public ControladorTablaJugador(Connection con) {
        this.con = con;
    }

    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        System.out.println(nombre + primerApellido + segundoApellido + sueldo + nacionalidad + fechaNacimiento +nickname + rol + equipo );


        String plantilla = "INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO) VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
        PreparedStatement crearJugador = con.prepareStatement(plantilla);
        crearJugador.setString(1, nombre);
        crearJugador.setString(2, primerApellido);
        crearJugador.setString(3, segundoApellido);
        crearJugador.setString(4, String.valueOf(sueldo));
        crearJugador.setString(5, nacionalidad);
        crearJugador.setString(6, String.valueOf(fechaNacimiento));
        crearJugador.setString(7, nickname);
        crearJugador.setString(8, rol);
        crearJugador.setString(9, equipo);


        return null;
    }
    }