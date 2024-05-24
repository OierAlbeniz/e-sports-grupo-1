package Controlador.ControladorBD;

import Modelo.Patrocinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaPatrocinador {
    private Connection con;
    private Patrocinador p;

    public ControladorTablaPatrocinador(Connection con) {
        this.con = con;
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

