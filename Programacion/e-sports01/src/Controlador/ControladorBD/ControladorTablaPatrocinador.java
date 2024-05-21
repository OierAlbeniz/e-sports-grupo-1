package Controlador.ControladorBD;

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



        return nombresPatrocinadores;
    }
}

