package Controlador.ControladorBD;

import Modelo.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para interactuar con la tabla de juegos en la base de datos.
 */
    public class ControladorTablaJuego {
        private Connection con;
        private Juego j;
        private List<Juego> listaJuegos;
        /**
         * Constructor de la clase ControladorTablaJuego.
         *
         * @param con La conexión a la base de datos.
         */
        public ControladorTablaJuego(Connection con) {
            this.con = con;
        }
        /**
         * Busca un juego por su nombre en la base de datos.
         *
         * @param nombre El nombre del juego a buscar.
         * @return Un objeto Juego que corresponde al juego encontrado.
         * @throws Exception Si ocurre un error al buscar el juego en la base de datos.
         */
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
        /**
         * Busca un juego por su ID en la base de datos.
         *
         * @param nombre El ID del juego a buscar.
         * @return Un objeto Juego que corresponde al juego encontrado.
         * @throws Exception Si ocurre un error al buscar el juego en la base de datos.
         */
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
        /**
         * Busca todos los juegos en la base de datos.
         *
         * @return Una lista de objetos Juego que representan todos los juegos encontrados.
         * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
         */
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
        /**
         * Busca el ID de un juego por su nombre en la base de datos.
         *
         * @param nombreJuego El nombre del juego del que se desea obtener el ID.
         * @return El ID del juego encontrado.
         * @throws SQLException Si ocurre un error al ejecutar la consulta SQL o si no se encuentra el juego con el nombre especificado.
         */
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
        /**
         * Inserta un nuevo juego en la base de datos.
         *
         * @param juego El juego que se desea insertar.
         * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
         */
        public void insertarJuego(Juego juego) throws SQLException {
            String sql = "INSERT INTO juego (nombre, empresa, fecha_lanzamiento) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, juego.getNombre());
                stmt.setString(2, juego.getEmpresa());
                stmt.setDate(3, java.sql.Date.valueOf(juego.getFechalanzamiento()));
                stmt.executeUpdate();
            }
        }
        /**
         * Elimina un juego de la base de datos.
         *
         * @param juego El juego que se desea eliminar.
         * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
         */
        public void eliminarJuego(Juego juego) throws SQLException {
            String sql = "DELETE FROM juego WHERE id_juego = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, juego.getIdJuego());
                stmt.executeUpdate();
            }
        }
        /**
         * Modifica un juego en la base de datos.
         *
         * @param juego El juego que se desea modificar.
         * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
         */
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
