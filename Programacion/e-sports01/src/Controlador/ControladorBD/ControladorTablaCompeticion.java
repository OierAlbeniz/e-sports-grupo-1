package Controlador.ControladorBD;

import Modelo.Competicion;
import Modelo.Juego;

import java.sql.*;
import java.time.LocalDate;
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

    /*
    public void crearCompeticion(String nombre, Date fechaInicio, Date fechaFin, String estado, int idJuego) throws SQLException {
        CallableStatement cs = null;

        try {
            cs = con.prepareCall("{call competicion_pkg.crear_competicion(?, ?, ?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setDate(2, fechaInicio);
            cs.setDate(3, fechaFin);
            cs.setString(4, estado);
            cs.setInt(5, idJuego);

            cs.execute();
            System.out.println("Competición creada correctamente.");
        } finally {
            if (cs != null) {
                cs.close();
            }
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
}
*/
}
