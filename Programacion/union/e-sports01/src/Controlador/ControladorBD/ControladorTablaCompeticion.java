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