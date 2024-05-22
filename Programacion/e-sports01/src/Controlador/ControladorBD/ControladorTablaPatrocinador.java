package Controlador.ControladorBD;

import Modelo.Equipo;
import Modelo.Patrocinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaPatrocinador {
    private Connection con;
    public ControladorTablaPatrocinador(Connection con) {
        this.con = con;
    }

    public Patrocinador buscarPatrocinador(Integer idpatrocinador) throws Exception {
        Patrocinador patro=new Patrocinador();

        String plantilla = "SELECT id_patrocinador, nombre FROM patrocinador WHERE id_patrocinador=?";

        PreparedStatement statement = con.prepareStatement(plantilla);
        statement.setInt(1, idpatrocinador);
        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            patro.setIdPatrocinador(rs.getInt("id_patrocinador"));
            patro.setNombre(rs.getString("nombre"));
            System.out.println("nombre");
        }

        statement.close();
        return patro;

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
}
