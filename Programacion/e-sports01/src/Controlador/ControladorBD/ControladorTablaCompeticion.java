package Controlador.ControladorBD;

import Modelo.Competicion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ControladorTablaCompeticion {
    private Connection con;
    private ControladorTablaJuego ctj;
    private Competicion c;
    private List<Competicion> listaCompeticiones;
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
            sentencia.setString(4, );

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
}
