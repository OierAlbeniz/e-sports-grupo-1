package Controlador.ControladorBD;

import Modelo.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


    public class ControladorTablaJuego {
        private Connection con;
        private Juego j;
        private List<Juego> listaJuegos;
        public ControladorTablaJuego(Connection con) {
            this.con = con;
        }

        public Juego buscarJuego(String nombre) throws Exception{
            Juego j = null;
            System.out.println(nombre + " el nombre del juego");
            String plantilla = "SELECT * FROM juego WHERE nombre = ?";
            PreparedStatement sentencia = con.prepareStatement(plantilla);
            sentencia.setString(1, nombre);

            ResultSet resultado = sentencia.executeQuery();

            if(resultado.next()){
                LocalDate fecha = resultado.getDate("fecha_lanzamiento").toLocalDate();
                j = new Juego(nombre, resultado.getString("empresa"), fecha, resultado.getInt("id_juego"));
                System.out.println(j.toString());
            } else {
                throw new Exception("Error al buscar el juego");
            }

            sentencia.close();
            return j;
        }
        public Juego buscarJuegoID(String nombre) throws Exception{
            Juego j = null;
            System.out.println(nombre + " el nombre del juego");
            String plantilla = "SELECT * FROM juego WHERE id_juego = ?";
            PreparedStatement sentencia = con.prepareStatement(plantilla);
            sentencia.setString(1, nombre);

            ResultSet resultado = sentencia.executeQuery();

            if(resultado.next()){
                j=new Juego();
                j.setIdJuego(resultado.getInt("id_juego"));
                j.setNombre(resultado.getString("nombre"));
                j.setEmpresa(resultado.getString("empresa"));
                j.setFechalanzamiento(resultado.getDate("fecha_lanzamiento").toLocalDate());
                System.out.println(j.toString());
            } else {
                throw new Exception("Error al buscar el juego");
            }

            sentencia.close();
            return j;
        }
        public List<Juego> buscarJuegos() throws SQLException {
            listaJuegos = new ArrayList<>();
            String plantilla ="SELECT * from juego";

            PreparedStatement sentencia = con.prepareStatement(plantilla);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()){
                LocalDate fecha = resultado.getDate("fecha_lanzamiento").toLocalDate();
                Juego j = new Juego(resultado.getString("nombre"),resultado.getString("empresa"),fecha,resultado.getInt("id_juego"));
                listaJuegos.add(j);
            }
            System.out.println(listaJuegos.toString());
            sentencia.close();
            return listaJuegos;
        }
        public int buscarIdJuegoPorNombre(String nombreJuego) throws SQLException {
            String sql = "SELECT id_juego FROM juego WHERE nombre = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, nombreJuego);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_juego");
                } else {
                    throw new SQLException("No se encontró un juego con el nombre: " + nombreJuego);
                }
            }
        }
        public void insertarJuego(Juego juego) throws SQLException {
            String sql = "INSERT INTO juego (nombre, empresa, fecha_lanzamiento) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, juego.getNombre());
                stmt.setString(2, juego.getEmpresa());
                stmt.setDate(3, java.sql.Date.valueOf(juego.getFechalanzamiento()));
                stmt.executeUpdate();
            }
        }

        public void eliminarJuego(Juego juego) throws SQLException {
            String sql = "DELETE FROM juego WHERE id_juego = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, juego.getIdJuego());
                stmt.executeUpdate();
            }
        }

        public void modificarJuego(Juego juego) throws SQLException {
            String sql = "UPDATE juego SET nombre = ?, empresa = ?, fecha_lanzamiento = ? WHERE id_juego = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, juego.getNombre());
                stmt.setString(2, juego.getEmpresa());
                stmt.setDate(3, java.sql.Date.valueOf(juego.getFechalanzamiento()));
                stmt.setInt(4, juego.getIdJuego());
                stmt.executeUpdate();
            }
        }
    }
