package Controlador.ControladorBD;

import Modelo.Patrocinador;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaPatrocinador {
    private Connection con;
    private Patrocinador p;

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


    public boolean patrocinadorExiste(Integer idPatrocinador, String nombre) throws SQLException {
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
    public void insertarPatrocinadores(Patrocinador p) throws Exception {
        try {
            String plantilla = "INSERT INTO patrocinador (nombre) VALUES(?)";

            PreparedStatement sentencia = con.prepareStatement(plantilla);

            sentencia.setString(1, p.getNombre());

            int resultado = sentencia.executeUpdate();

            if (resultado != 1) {
                throw new Exception("Error al insertar");
            }
            sentencia.close();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void borrarPatrocinador(String nombre) throws Exception{
        String plantilla = "DELETE FROM patrocinador WHERE nombre = ?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(1,nombre);

        int resultado = sentencia.executeUpdate();

        if (resultado != 1) {
            throw new Exception("Error al borrar");
        }
        sentencia.close();
    }

    public void editarPatrocinador(String nombreNuevo) throws Exception{

        String plantilla = "UPDATE patrocinador SET nombre = ? WHERE nombre = ?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(2,p.getNombre());
        sentencia.setString(1,nombreNuevo);

        int n = sentencia.executeUpdate();

        if(n != 1){
            throw new Exception("Error al actualizar");
        }
        sentencia.close();
    }

    public Patrocinador buscarPatrocinadorEliminar(String nombre) throws Exception{

        String plantilla = "SELECT * FROM patrocinador WHERE nombre=?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(1,nombre);

        ResultSet resultado = sentencia.executeQuery();

        if(resultado.next()){
            p = new Patrocinador(nombre);
        }
        else {
            throw new Exception("Error al buscar al pasajero");
        }
        sentencia.close();
        return p;
    }
}

