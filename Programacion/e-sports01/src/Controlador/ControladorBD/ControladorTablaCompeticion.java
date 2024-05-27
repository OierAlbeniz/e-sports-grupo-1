package Controlador.ControladorBD;

import Modelo.Competicion;
import Modelo.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaCompeticion {
    private Connection con;
    private ControladorTablaJuego ctj;
    private Competicion c;
    private List<Competicion> listaCompeticiones;
    private List<String> listaNombreCompeticiones;


    public ControladorTablaCompeticion(Connection con) {
        this.con = con;
    }

    /**
     * Llena una lista con todas las competiciones de la base de datos.
     *
     * @return Una lista de objetos Competicion.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */

    public List<Competicion> llenarCompeticiones() throws Exception {
        List<Competicion> llenarCompeticiones=new ArrayList<>();

        String plantilla = "SELECT * FROM competicion";

        PreparedStatement statement = con.prepareStatement(plantilla);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Competicion competicion=new Competicion();
            competicion.setNombre(rs.getString("nombre"));
            llenarCompeticiones.add(competicion);
            System.out.println(rs.getString("nombre"));
        }

        statement.close();
        return llenarCompeticiones;
    }
    /*
    public void insertarCompeticion(Competicion c) throws Exception{
        try{
            String plantilla = "INSERT INTO competicion VALUES(?,?,?,?)";

            PreparedStatement sentencia = con.prepareStatement(plantilla);

            sentencia.setString(1,c.getNombre());

            // pasar de LocalDate (Modelo) a Date(BD)
            java.sql.Date fechaInicio = java.sql.Date.valueOf(c.getFechaInicio());
            sentencia.setDate(2,fechaInicio);
            java.sql.Date fechaFin = java.sql.Date.valueOf(c.getFechaFin());
            sentencia.setDate(3,fechaFin);
            sentencia.setString(3,c.getEstado());
            //.setString(4, );

            int n = sentencia.executeUpdate();

            if(n != 1){
                throw new Exception("Error al insertar");
            }
            sentencia.close();
        }
        catch (SQLIntegrityConstraintViolationException ex){
            throw new Exception("Ya hay un vuelo con ese codigo");
        }
    }

     */

    /**
     * Busca todas las competiciones y devuelve sus nombres.
     *
     * @return Una lista de nombres de competiciones.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */

    public List<String> buscarCompeticiones() throws SQLException {
        listaNombreCompeticiones = new ArrayList<>();
        String plantilla ="SELECT nombre from competicion";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()){
            listaNombreCompeticiones.add(resultado.getString("nombre"));
        }
        sentencia.close();
        return listaNombreCompeticiones;
    }

    /**
     * Busca una competición por su nombre.
     *
     * @param nombre El nombre de la competición.
     * @return Un objeto Competicion si se encuentra, null en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */

    public Competicion buscarCompeticion(String nombre) throws Exception {
        Competicion c = null;

        String plantilla = "SELECT id_competicion, nombre,fecha_inicio, fecha_fin, estado FROM competicion WHERE nombre=?";

        PreparedStatement buscarCompeticion = con.prepareStatement(plantilla);
        buscarCompeticion.setString(1, nombre);

        ResultSet rs = buscarCompeticion.executeQuery();

        if (rs.next()) {
            c = new Competicion();
            c.setNombre(rs.getString("nombre"));
            c.setIdCompeticion(rs.getInt("id_competicion"));
            c.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            c.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            c.setEstado(rs.getString("estado"));
            System.out.println(rs.getString("estado") + " estado de la competi");
        } else {
            System.out.println("Competicion no encontrada.");
        }
        rs.close();
        buscarCompeticion.close();

        return c;
    }

    /**
     * Busca el juego asociado a una competición por el nombre de la competición.
     *
     * @param nombre El nombre de la competición.
     * @return El nombre del juego asociado a la competición.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */

    public String buscarJuegoCompeticion(String nombre) throws Exception {
        String nombreJuego = null;

        String plantilla = "SELECT id_juego FROM competicion WHERE nombre=?";

        PreparedStatement buscarJuegoCompeticion = con.prepareStatement(plantilla);
        buscarJuegoCompeticion.setString(1, nombre);

        ResultSet rs = buscarJuegoCompeticion.executeQuery();

        if (rs.next()) {
            nombreJuego= rs.getString("id_juego");
        } else {
            System.out.println("Juego no encontrado.");
        }
        rs.close();
        buscarJuegoCompeticion.close();

        return nombreJuego;
    }

    /**
     * Inserta una nueva competición en la base de datos.
     *
     * @param c El objeto Competicion a insertar.
     * @throws Exception Si ocurre un error durante la inserción.
     */

    public void insertarCompeticion(Competicion c) throws Exception {

        String sql = "{call competicion_pkg.crear_competicion(?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = con.prepareCall(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setDate(2, Date.valueOf(c.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(c.getFechaFin()));
            stmt.setString(4, c.getEstado());
            stmt.setInt(5, c.getJuego().getIdJuego());
            stmt.execute();
            System.out.println("Competición creada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al crear la competición: " + ex.getMessage());
            throw new Exception("Error al crear la competición", ex);
        }
    }

    /**
     * Elimina una competición de la base de datos.
     *
     * @param idCompeticion El ID de la competición a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */

    public void eliminarCompeticion(int idCompeticion) throws SQLException {
        CallableStatement cs = null;

        try {
            cs = con.prepareCall("{call competicion_pkg.eliminar_competicion(?)}");
            cs.setInt(1, idCompeticion);

            cs.execute();
            System.out.println("Competición eliminada correctamente.");
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Modifica una competición en la base de datos.
     *
     * @param idCompeticion El ID de la competición a modificar.
     * @param nombre El nuevo nombre de la competición.
     * @param fechaInicio La nueva fecha de inicio de la competición.
     * @param fechaFin La nueva fecha de fin de la competición.
     * @param estado El nuevo estado de la competición.
     * @param idJuego El ID del nuevo juego asociado a la competición.
     * @throws SQLException Si ocurre un error durante la modificación.
     */

    public void modificarCompeticion(int idCompeticion, String nombre, Date fechaInicio, Date fechaFin, String estado, int idJuego) throws SQLException {
        CallableStatement cs = null;

        try {
            cs = con.prepareCall("{call competicion_pkg.modificar_competicion(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, idCompeticion);
            cs.setString(2, nombre);
            cs.setDate(3, fechaInicio);
            cs.setDate(4, fechaFin);
            cs.setString(5, estado);
            cs.setInt(6, idJuego);

            cs.execute();
            System.out.println("Competición modificada correctamente.");
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Busca todas las competiciones asociadas a un juego.
     *
     * @param idJuego El ID del juego.
     * @return Una lista de objetos Competicion.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */

    public List<Competicion> buscarCompeticionesPorJuego(int idJuego) throws SQLException {
        List<Competicion> competiciones = new ArrayList<>();
        String query = "SELECT * FROM competicion WHERE id_juego = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, idJuego);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Competicion competicion = new Competicion();
                    competicion.setIdCompeticion(resultSet.getInt("id_competicion"));
                    competicion.setNombre(resultSet.getString("nombre"));
                    competicion.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate());
                    competicion.setFechaFin(resultSet.getDate("fecha_fin").toLocalDate());
                    competicion.setEstado(resultSet.getString("estado"));
                    // Aquí puedes agregar más atributos según tu modelo de datos
                    competiciones.add(competicion);
                }
            }
        }
        return competiciones;
    }

    /**
     * Vincula un juego a una competición.
     *
     * @param idJuego El ID del juego.
     * @param idCompeticion El ID de la competición.
     * @throws SQLException Si ocurre un error durante la actualización.
     */

    public void vincularJuegoACompeticion(int idJuego, int idCompeticion) throws SQLException {
        String sql = "UPDATE COMPETICION SET ID_JUEGO = ? WHERE ID_COMPETICION = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idJuego);
            stmt.setInt(2, idCompeticion);
            stmt.executeUpdate();
            System.out.println("Juego vinculado a la competición correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al vincular el juego a la competición: " + ex.getMessage());
            throw new SQLException("Error al vincular el juego a la competición", ex);
        }
    }

    /**
     * Desvincula un juego de una competición.
     *
     * @param idJuego El ID del juego.
     * @param idCompeticion El ID de la competición.
     * @throws SQLException Si ocurre un error durante la actualización.
     */

    public void desvincularJuegoDeCompeticion(int idJuego, int idCompeticion) throws SQLException {
        String sql = "UPDATE COMPETICION SET ID_JUEGO = NULL WHERE ID_JUEGO = ? AND ID_COMPETICION = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idJuego);
            stmt.setInt(2, idCompeticion);
            stmt.executeUpdate();
            System.out.println("Juego desvinculado de la competición correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al desvincular el juego de la competición: " + ex.getMessage());
            throw new SQLException("Error al desvincular el juego de la competición", ex);
        }
    }
}