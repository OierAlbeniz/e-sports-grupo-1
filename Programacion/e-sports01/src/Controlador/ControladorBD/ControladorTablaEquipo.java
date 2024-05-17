package Controlador.ControladorBD;

import Modelo.Equipo;

import Modelo.Patrocinador;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorTablaEquipo {
    private Connection con;
    private ControladorBD cb;
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
    public Integer cantidadEquipos() throws Exception {
        Integer cantidad = 0;

        String plantilla = "SELECT COUNT(*) AS CANTIDAD FROM EQUIPO";

        PreparedStatement statement = con.prepareStatement(plantilla);
        ResultSet rs = statement.executeQuery();

        // Verificar si hay algún resultado
        if (rs.next()) {
            cantidad = rs.getInt("CANTIDAD");
            System.out.println(rs.getString("CANTIDAD"));
        }

        statement.close();
        return cantidad;
    }
    public List<Equipo> llenarEquipos() throws Exception {
        List<Equipo> llenarEquipos=new ArrayList<>();

        String plantilla = "SELECT id_equipo, nombre, fecha_fundacion, id_patrocinador FROM EQUIPO";

        PreparedStatement statement = con.prepareStatement(plantilla);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Equipo equipo=new Equipo();
            equipo.setIdEquipo(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
            llenarEquipos.add(equipo);
            System.out.println(rs.getString("nombre"));
            System.out.println(rs.getString("id_equipo"));
        }

        statement.close();
        return llenarEquipos;
    }
    public List<Equipo> llenarEquiposporID(String idequipo) throws Exception {
        List<Equipo> llenarEquipos=new ArrayList<>();

        String plantilla = "SELECT id_equipo, nombre, fecha_fundacion, id_patrocinador FROM EQUIPO WHERE id_equipo=?";

        PreparedStatement statement = con.prepareStatement(plantilla);
        statement.setString(1, idequipo);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Equipo equipo=new Equipo();
            equipo.setIdEquipo(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
            llenarEquipos.add(equipo);
            System.out.println(rs.getString("nombre"));
            System.out.println(rs.getString("id_equipo"));
        }

        statement.close();
        return llenarEquipos;
    }
    public Equipo buscarEquipo(Integer idequipo) throws Exception {
        Equipo equipo=new Equipo();

        String plantilla = "SELECT id_equipo, nombre, fecha_fundacion, id_patrocinador FROM EQUIPO WHERE id_equipo=?";

        PreparedStatement statement = con.prepareStatement(plantilla);
        statement.setInt(1, idequipo);
        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            equipo.setIdEquipo(rs.getInt("id_Equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
            Integer id_patrocinador = rs.getInt("id_patrocinadpr");
            //equipo.setPatrocinador(cb.buscarPatrocinador(id_patrocinador));
        }

        statement.close();
        return equipo;
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


