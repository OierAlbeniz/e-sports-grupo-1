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

        /*public Juego buscarJuego(String nombre) throws Exception {

            String plantilla = "SELECT * FROM juego WHERE nombre = ?";

            PreparedStatement sentencia = con.prepareStatement(plantilla);

            sentencia.setString(1, nombre);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                LocalDate fecha = LocalDate.parse(resultado.getString("fecha_lanzamiento"));
                j = new Juego(nombre, resultado.getString("empresa"), fecha, resultado.getInt("id_juego"));
                System.out.println(j.toString());
            } else {
                throw new Exception("Error al buscar el vuelo");
            }
            sentencia.close();
            return j;
        }

         */

        public Juego buscarJuegoPorNombreCompeticion(String nombreCompeticion) throws Exception {
            String sql = "SELECT j.* FROM juego j JOIN competicion c ON j.id_juego = c.id_juego WHERE c.nombre = ?";

            try (PreparedStatement sentencia = con.prepareStatement(sql)) {
                sentencia.setString(1, nombreCompeticion);
                ResultSet resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    LocalDate fechaLanzamiento = resultado.getDate("fecha_lanzamiento").toLocalDate();
                    Juego juego = new Juego(
                            resultado.getString("nombre"),
                            resultado.getString("empresa"),
                            fechaLanzamiento,
                            resultado.getInt("id_juego")
                    );
                    System.out.println(juego.toString());
                    return juego;
                } else {
                    throw new Exception("No se encontró ningún juego para la competición especificada");
                }
            } catch (SQLException ex) {
                System.out.println("Error al buscar el juego: " + ex.getMessage());
                throw new Exception("Error al buscar el juego", ex);
            }
        }

        public List<Juego> buscarJuegos() throws SQLException {
            listaJuegos = new ArrayList<>();
            String plantilla = "SELECT * from juego";

            PreparedStatement sentencia = con.prepareStatement(plantilla);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                LocalDate fecha = resultado.getDate("fecha_lanzamiento").toLocalDate();
                Juego j = new Juego(resultado.getString("nombre"), resultado.getString("empresa"), fecha, resultado.getInt("id_juego"));
                listaJuegos.add(j);
            }
            System.out.println(listaJuegos.toString());
            sentencia.close();
            return listaJuegos;
        }

        public Juego buscarJuego(String nombreJuego) throws Exception {
            String sql = "SELECT * FROM JUEGO WHERE NOMBRE = ?";
            Juego juego = null;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombreJuego);
            ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    juego = new Juego();
                    juego.setIdJuego(rs.getInt("ID_JUEGO"));
                    juego.setNombre(rs.getString("NOMBRE"));
                    juego.setEmpresa(rs.getString("EMPRESA"));
                    if (rs.getTimestamp("FECHA_LANZAMIENTO") != null) {
                        juego.setFechalanzamiento(rs.getTimestamp("FECHA_LANZAMIENTO").toLocalDateTime().toLocalDate());
                    }
                }
                return juego;
            }
        }
