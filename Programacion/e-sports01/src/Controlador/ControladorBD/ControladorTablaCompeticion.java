package Controlador.ControladorBD;

import Modelo.Competicion;
import Modelo.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaCompeticion {
    private Connection con;
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
}
