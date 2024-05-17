package Controlador.ControladorBD;

import Modelo.Usuario;
import Vista.VentanaJugadores;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ControladorTablaJugador {
    private Connection con;
    private VentanaJugadores vj;
    public ControladorTablaJugador(Connection con) {
        this.con = con;
    }

    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        System.out.println(nombre + primerApellido + segundoApellido + sueldo + nacionalidad + fechaNacimiento + nickname + rol + equipo );

        // Verificar si el jugador ya existe en la base de datos antes de insertarlo
        if (jugadorExiste(nombre, primerApellido, segundoApellido, fechaNacimiento)) {
            JOptionPane.showMessageDialog(null, "El jugador ya existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Si el jugador no existe, proceder con la inserción
        String plantilla = "INSERT INTO JUGADOR (NOMBRE, APELLIDO1, APELLIDO2, SUELDO, NACIONALIDAD, FECHA_NACIMIENTO, NICKNAME, ROL, ID_EQUIPO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement crearJugador = con.prepareStatement(plantilla);
        crearJugador.setString(1, nombre);
        crearJugador.setString(2, primerApellido);
        crearJugador.setString(3, segundoApellido);
        crearJugador.setInt(4, sueldo);
        crearJugador.setString(5, nacionalidad);
        crearJugador.setDate(6, Date.valueOf(fechaNacimiento));
        crearJugador.setString(7, nickname);
        crearJugador.setString(8, rol);
        crearJugador.setString(9, equipo);

        int filasAfectadas = crearJugador.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El jugador " + nombre + " ha sido insertado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al insertar el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        crearJugador.close();
        return null;
    }

    // Método para verificar si un jugador ya existe en la base de datos
    private boolean jugadorExiste(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento) throws Exception {
        String consulta = "SELECT COUNT(*) FROM JUGADOR WHERE NOMBRE = ? AND APELLIDO1 = ? AND APELLIDO2 = ? AND FECHA_NACIMIENTO = ?";
        PreparedStatement stmt = con.prepareStatement(consulta);
        stmt.setString(1, nombre);
        stmt.setString(2, primerApellido);
        stmt.setString(3, segundoApellido);
        stmt.setDate(4, Date.valueOf(fechaNacimiento));

        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        stmt.close();

        return count > 0;
    }
    }