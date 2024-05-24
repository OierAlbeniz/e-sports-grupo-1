package Controlador.ControladorBD;

import Modelo.Patrocinador;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaPatrocinador {
    private Connection con;

    public ControladorTablaPatrocinador(Connection con) {
        this.con = con;
    }

    public List<String> buscarPatrocinador() throws SQLException {
        List<String> nombresPatrocinadores = new ArrayList<>();
        String query = "SELECT NOMBRE FROM PATROCINADOR";

        Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                nombresPatrocinadores.add(rs.getString("NOMBRE"));
            }
        if (rs.next()) {
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setIdPatrocinador(rs.getInt("id_patrocinador"));
            patrocinador.setNombre(rs.getString("nombre"));
        }

        return nombresPatrocinadores;
    }

    public void crearPatrocinador(Integer idPatrocinador, String nombre) throws SQLException {
        if (patrocinadorExiste(idPatrocinador, nombre)) {
            JOptionPane.showMessageDialog(null, "El patrocinador ya existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String plantilla = "INSERT INTO patrocinador (id_patrocinador, nombre) VALUES (?, ?)";
        PreparedStatement crearPatrocinador = con.prepareStatement(plantilla);
        crearPatrocinador.setInt(1, idPatrocinador);
        crearPatrocinador.setString(2, nombre);

        int filasAfectadas = crearPatrocinador.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El patrocinador " + nombre + " ha sido insertado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al insertar el patrocinador.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        crearPatrocinador.close();
    }

    private boolean patrocinadorExiste(Integer idPatrocinador, String nombre) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM patrocinador WHERE id_patrocinador = ? AND nombre = ?";
        PreparedStatement stmt = con.prepareStatement(consulta);
        stmt.setInt(1, idPatrocinador);
        stmt.setString(2, nombre);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        stmt.close();

        return count > 0;
    }

    public List<Patrocinador> llenarPatrocinadorNombre(String equipoSeleccionado) throws SQLException {
        List<Patrocinador> patrocinadores = new ArrayList<>();
        String consulta = "SELECT p.nombre FROM patrocinador p JOIN equipo e ON p.id_patrocinador = e.id_patrocinador WHERE e.nombre = ?";
        PreparedStatement stmt = con.prepareStatement(consulta);
        stmt.setString(1, equipoSeleccionado);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setNombre(rs.getString("nombre"));
            patrocinadores.add(patrocinador);
        }
        rs.close();
        stmt.close();
        return patrocinadores;
    }

    public void eliminarPatrocinador(String nombre, String equipo) throws SQLException {
        String consulta = "DELETE FROM patrocinador WHERE nombre = ? AND id_patrocinador = (SELECT id_patrocinador FROM equipo WHERE nombre = ?)";
        PreparedStatement stmt = con.prepareStatement(consulta);
        stmt.setString(1, nombre);
        stmt.setString(2, equipo);
        int rowsAffected = stmt.executeUpdate();

        stmt.close();
        if (rowsAffected == 0) {
            throw new SQLException("No se encontró el patrocinador o el equipo especificado.");
        } else {
            JOptionPane.showMessageDialog(null, "El patrocinador se ha borrado exitosamente.");
        }
    }

    public Patrocinador actualizarPatrocinador(String nombre, String equipo) throws SQLException {
        Patrocinador patrocinador;
        String selectQuery = "SELECT p.nombre, p.id_patrocinador FROM patrocinador p JOIN equipo e ON p.id_patrocinador = e.id_patrocinador WHERE p.nombre = ? AND e.nombre = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, nombre);
        selectStatement.setString(2, equipo);
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {
            patrocinador = new Patrocinador();
            patrocinador.setNombre(rs.getString("nombre"));
            patrocinador.setIdPatrocinador(rs.getInt("id_patrocinador"));
        } else {
            throw new SQLException("No se encontró al patrocinador en la base de datos.");
        }

        rs.close();
        selectStatement.close();
        return patrocinador;
    }

    public void editarPatrocinadorConfir(String nombre, String nuevoEquipo, String nombreAntiguo, String equipoAntiguo) throws SQLException {
        String updateQuery = "UPDATE patrocinador SET nombre = ?, id_patrocinador = (SELECT id_patrocinador FROM equipo WHERE nombre = ?) WHERE nombre = ? AND id_patrocinador = (SELECT id_patrocinador FROM equipo WHERE nombre = ?)";
        PreparedStatement updateStatement = con.prepareStatement(updateQuery);

        updateStatement.setString(1, nombre);
        updateStatement.setString(2, nuevoEquipo);
        updateStatement.setString(3, nombreAntiguo);
        updateStatement.setString(4, equipoAntiguo);

        int filasAfectadas = updateStatement.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El patrocinador " + nombre + " ha sido editado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al editar el patrocinador.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        updateStatement.close();
    }
    public Patrocinador buscarPatrocinadorNombre(String nombre) {
        try {
            String plantilla = "SELECT * FROM patrocinador WHERE nombre = ?";
            PreparedStatement sentencia = con.prepareStatement(plantilla);
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                int idPatrocinador = resultado.getInt("id_patrocinador");

                return new Patrocinador(idPatrocinador, nombre);
            } else {
                throw new RuntimeException("No se encontró ningún patrocinador con el nombre proporcionado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el patrocinador en la base de datos: " + e.getMessage());
        }
    }
}

