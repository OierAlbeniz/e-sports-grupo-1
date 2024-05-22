package Controlador.ControladorBD;

import Modelo.Competicion;
import Modelo.Equipo;
import Modelo.Juego;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaCompeticion {
    private Connection con;
    private ControladorTablaJuego ctj;
    private Competicion c;
    private List<Competicion> listaCompeticiones;
    private ControladorBD cb;
    private List<String> listaNombreCompeticiones;

    public ControladorTablaCompeticion(Connection con, ControladorBD cb) {
        this.con = con;
        this.cb = cb;
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
    public List<String> buscarCompeticiones() throws Exception {
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

    public  String buscarCompeticionPorNombre(String nombre) throws Exception {
        String idComp = null;
        String plantilla = "SELECT * FROM competicion WHERE nombre = ?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(1,nombre);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()){
            idComp = resultado.getString("id_competicion");
        }
        else{
            throw  new Exception("No se ha encontrado la competicion");
        }
        sentencia.close();
        return idComp;
    }

    public void borrarCompeticion(int idCompeticion) throws Exception {
        String sql = "{call competicion_pkg.eliminar_competicion(?)}";
        try (CallableStatement stmt = con.prepareCall(sql)) {
            stmt.setInt(1, idCompeticion);
            stmt.execute();
            System.out.println("Competición eliminada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la competición: " + ex.getMessage());
            throw new Exception("Error al eliminar la competición", ex);
        }
    }
    public void modificarCompeticion(int idCompeticion, String nombre, LocalDate fechaInicio, LocalDate fechaFin, String estado, int idJuego) throws Exception {
        String sql = "{call competicion_pkg.modificar_competicion(?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = con.prepareCall(sql)) {
            stmt.setInt(1, idCompeticion);
            stmt.setString(2, nombre);
            stmt.setDate(3, Date.valueOf(fechaInicio));
            stmt.setDate(4, Date.valueOf(fechaFin));
            stmt.setString(5, estado);
            stmt.setInt(6, idJuego);
            stmt.execute();
            System.out.println("Competición modificada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar la competición: " + ex.getMessage());
            throw new Exception("Error al modificar la competición", ex);
        }
    }
    public Competicion obtenerCompeticion(String nombre) throws Exception{
        String plantilla = "SELECT * FROM competicion WHERE nombre = ?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(1,nombre);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()){
            Juego j = cb.buscarJuegoPorNombreCompeticion(nombre);
            LocalDate fechaIni = LocalDate.parse(resultado.getString("fecha_inicio"));
            LocalDate fechaFin = LocalDate.parse(resultado.getString("fecha_fin"));

            c = new Competicion(resultado.getInt("id_competicion"),resultado.getString("nombre"),fechaIni,fechaFin,resultado.getString("estado"),j);
        }
        else{
            throw  new Exception("No se ha encontrado la competicion");
        }
        sentencia.close();
        return c;
    }

}