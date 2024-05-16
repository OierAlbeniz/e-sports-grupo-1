package Controlador.ControladorBD;

import Modelo.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaEquipo {
    private Connection con;
    public ControladorTablaEquipo(Connection con) {
        this.con = con;
    }


    public String llenarNombre() throws Exception {
        String nombre = null;

        String plantilla = "SELECT nombre FROM equipo WHERE id_equipo=1";

        PreparedStatement statement = con.prepareStatement(plantilla);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {

            nombre = rs.getString("nombre");
        }

        statement.close();

        return nombre;
    }



        public ArrayList selectEquipo(String nombre) throws Exception {
            ArrayList<Equipo> equipos = new ArrayList<>();

            String plantilla = "SELECT nombre FROM equipo";
            PreparedStatement selectEquipos = con.prepareStatement(plantilla);
            ResultSet rs = selectEquipos.executeQuery();

            while (rs.next()) {
                Equipo equipo = new Equipo(); // Crear un nuevo objeto Equipo en cada iteración
                equipo.setNombre(rs.getString("nombre"));
                equipos.add(equipo);

            }
            rs.close();
            selectEquipos.close();

            return equipos;

        }
    }


