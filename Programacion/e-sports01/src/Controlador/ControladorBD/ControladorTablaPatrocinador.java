package Controlador.ControladorBD;

import Modelo.Equipo;
import Modelo.Patrocinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
