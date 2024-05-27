package Controlador.ControladorBD;

import Modelo.Patrocinador;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Controlador para la tabla de patrocinadores en la base de datos.
 */
public class ControladorTablaPatrocinador {
    private Connection con;
    private Patrocinador p;
    /**
     * Constructor del controlador de la tabla de patrocinadores.
     *
     * @param con La conexión a la base de datos.
     */
    public ControladorTablaPatrocinador(Connection con) {
        this.con = con;
    }

    /**
     * Busca todos los patrocinadores en la base de datos.
     *
     * @return Una lista de nombres de patrocinadores.
     * @throws SQLException Si ocurre un error durante la búsqueda.
     */
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

    /**
     * Verifica si un patrocinador ya existe en la base de datos.
     *
     * @param idPatrocinador El ID del patrocinador.
     * @param nombre         El nombre del patrocinador.
     * @return true si el patrocinador existe, false en caso contrario.
     * @throws SQLException Si ocurre un error durante la verificación.
     */
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
    /**
     * Llena una lista de patrocinadores basados en el nombre del equipo.
     *
     * @param equipoSeleccionado El nombre del equipo seleccionado.
     * @return Una lista de patrocinadores.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
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
    /**
     * Actualiza la información de un patrocinador.
     *
     * @param nombre El nombre del patrocinador.
     * @param equipo El nombre del equipo.
     * @return El objeto Patrocinador actualizado.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
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
    /**
     * Edita la información de un patrocinador y la confirma.
     *
     * @param nombre        El nuevo nombre del patrocinador.
     * @param nuevoEquipo   El nuevo equipo del patrocinador.
     * @param nombreAntiguo El nombre antiguo del patrocinador.
     * @param equipoAntiguo El equipo antiguo del patrocinador.
     * @throws SQLException Si ocurre un error durante la edición.
     */
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
    /**
     * Busca un patrocinador por su nombre en la base de datos.
     *
     * @param nombre El nombre del patrocinador a buscar.
     * @return El patrocinador encontrado, o null si no se encuentra ningún patrocinador con ese nombre.
     * @throws RuntimeException Si ocurre un error durante la búsqueda o si no se encuentra ningún patrocinador con el nombre proporcionado.
     */
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
    /**
     * Inserta un nuevo patrocinador en la base de datos.
     *
     * @param p El patrocinador a insertar.
     * @throws Exception Si ocurre un error durante la inserción, como violación de restricciones de integridad.
     */
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
    /**
     * Elimina un patrocinador de la base de datos por su nombre.
     *
     * @param nombre El nombre del patrocinador a borrar.
     * @throws Exception Si ocurre un error durante el borrado, como un nombre de patrocinador inexistente.
     */
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
    /**
     * Edita el nombre de un patrocinador en la base de datos.
     *
     * @param nombreNuevo El nuevo nombre para el patrocinador.
     * @throws Exception Si ocurre un error durante la edición, como un nombre de patrocinador inexistente.
     */
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
    /**
     * Busca un patrocinador por su nombre en la base de datos para su eliminación.
     *
     * @param nombre El nombre del patrocinador a buscar.
     * @return El patrocinador encontrado para su eliminación.
     * @throws Exception Si ocurre un error durante la búsqueda o si no se encuentra ningún patrocinador con el nombre proporcionado.
     */
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

