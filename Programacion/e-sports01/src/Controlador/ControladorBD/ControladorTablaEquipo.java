package Controlador.ControladorBD;

import Modelo.Equipo;


import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
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

    public void crearEquipo(String nombre, LocalDate fecha, String patrocinador, String competicion) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            // Verificar si el equipo ya existe
            String queryCheck = "SELECT COUNT(*) FROM EQUIPO WHERE NOMBRE = ?";
            pstmt = con.prepareStatement(queryCheck);
            pstmt.setString(1, nombre);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                throw new Exception("El equipo ya existe");
            }

            // Obtener ID del patrocinador
            String queryPatrocinador = "SELECT ID_PATROCINADOR FROM PATROCINADOR WHERE NOMBRE = ?";
            pstmt = con.prepareStatement(queryPatrocinador);
            pstmt.setString(1, patrocinador);
            rs = pstmt.executeQuery();

            int idPatrocinador = -1;
            if (rs.next()) {
                idPatrocinador = rs.getInt("ID_PATROCINADOR");
            } else {
                throw new Exception("Patrocinador no encontrado");
            }

            // Obtener ID de la competición
            String queryCompeticion = "SELECT ID_COMPETICION FROM COMPETICION WHERE NOMBRE = ?";
            pstmt = con.prepareStatement(queryCompeticion);
            pstmt.setString(1, competicion);
            rs = pstmt.executeQuery();

            int idCompeticion = -1;
            if (rs.next()) {
                idCompeticion = rs.getInt("ID_COMPETICION");
            } else {
                throw new Exception("Competición no encontrada");
            }

            // Insertar el nuevo equipo
            String queryInsert = "INSERT INTO EQUIPO (NOMBRE, FECHA_FUNDACION, ID_PATROCINADOR) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(queryInsert);
            pstmt.setString(1, nombre);
            pstmt.setDate(2, java.sql.Date.valueOf(fecha)); // convertir LocalDate a java.sql.Date
            pstmt.setInt(3, idPatrocinador);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Eel equipo ha sido creado correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al crear el equipo");
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException ignore) {}
            if (con != null) try { con.close(); } catch (SQLException ignore) {}
        }
    }

    public void borrarEquipo(String nombre) throws SQLException {
        String query = "DELETE FROM EQUIPO WHERE NOMBRE = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró un equipo con el nombre especificado.");
            }else {
                JOptionPane.showMessageDialog(null,"el equipo se borro correctamente ");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al borrar el equipo: " + e.getMessage(), e);
        }
    }

}


