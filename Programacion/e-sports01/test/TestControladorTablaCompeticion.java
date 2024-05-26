import Controlador.ControladorBD.ControladorTablaCompeticion;
import Modelo.Competicion;
import Modelo.Juego;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaCompeticion {
    private static Connection con;
    private ControladorTablaCompeticion controlador;

    @BeforeAll
    public static void setup() throws SQLException {
        abrirConexion();
    }

    /*private static void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
            String user = "equipo16";
            String passwd = "equipo16";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Conexion abierta");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            fail("Error al abrir la conexion: " + e.getMessage());
        }
    }*/

    private static void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1522:orcl";
            String user = "system";
            String passwd = "Oier2004";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Conexion abierta");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            fail("Error al abrir la conexion: " + e.getMessage());
        }
    }


    @BeforeEach
    public void init() throws SQLException {
        con.setAutoCommit(false); // Deshabilitar autocommit para usar transacciones
        controlador = new ControladorTablaCompeticion(con);
    }

    @AfterEach
    public void rollback() throws SQLException {
        con.rollback(); // Revertir cambios después de cada prueba
    }

    @AfterAll
    public static void teardown() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @Test
    public void testLlenarCompeticiones() throws Exception {
        List<Competicion> competiciones = controlador.llenarCompeticiones();
        assertNotNull(competiciones);
        assertFalse(competiciones.isEmpty());
        assertEquals("Liga de Campeones", competiciones.get(0).getNombre());
    }

    @Test
    public void testBuscarCompeticiones() throws SQLException {
        List<String> competiciones = controlador.buscarCompeticiones();
        assertNotNull(competiciones);
        assertFalse(competiciones.isEmpty());
        assertEquals("Liga de Campeones", competiciones.get(0));
    }

    @Test
    public void testBuscarCompeticion() throws Exception {
        Competicion competicion = controlador.buscarCompeticion("Liga de Campeones");
        assertNotNull(competicion);
        assertEquals("Liga de Campeones", competicion.getNombre());
        assertEquals("abierto", competicion.getEstado());
    }

    @Test
    public void testBuscarJuegoCompeticion() throws Exception {
        String nombreJuego = controlador.buscarJuegoCompeticion("Liga de Campeones");
        assertNotNull(nombreJuego);
        assertEquals("1", nombreJuego);
    }

    @Test
    public void testInsertarCompeticion() throws Exception {
        Competicion nuevaCompeticion = new Competicion();
        nuevaCompeticion.setNombre("Torneo del Rey");
        nuevaCompeticion.setFechaInicio(Date.valueOf("2023-07-01").toLocalDate());
        nuevaCompeticion.setFechaFin(Date.valueOf("2023-07-31").toLocalDate());
        nuevaCompeticion.setEstado("abierto");
        Juego juego = new Juego();
        juego.setIdJuego(1); // Asegúrate de que este ID de juego existe
        nuevaCompeticion.setJuego(juego);
        controlador.insertarCompeticion(nuevaCompeticion);

        Competicion competicion = controlador.buscarCompeticion("Torneo del Rey");
        assertNotNull(competicion);
        assertEquals("Torneo del Rey", competicion.getNombre());
    }

    @Test
    public void testEliminarCompeticion() throws SQLException, Exception {
        controlador.eliminarCompeticion(1);
        Competicion competicion = controlador.buscarCompeticion("Liga de Campeones");
        assertNull(competicion);
    }

    @Test
    public void testModificarCompeticion() throws SQLException, Exception {
        try (CallableStatement stmt = con.prepareCall("{call competicion_pkg.modificar_competicion(?, ?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, 1); // ID de la competición
            stmt.setString(2, "Liga de Campeones Modificado"); // Nuevo nombre
            stmt.setDate(3, Date.valueOf("2023-05-15")); // Nueva fecha de inicio
            stmt.setDate(4, Date.valueOf("2023-06-30")); // Nueva fecha de fin
            stmt.setString(5, "cerrado"); // Nuevo estado
            stmt.setInt(6, 1); // ID del juego

            stmt.execute();
        }

        Competicion competicion = controlador.buscarCompeticion("Liga de Campeones Modificado");
        assertNotNull(competicion);
        assertEquals("Liga de Campeones Modificado", competicion.getNombre());
        assertEquals("cerrado", competicion.getEstado());
    }


    @Test
    public void testBuscarCompeticionesPorJuego() throws SQLException {
        List<Competicion> competiciones = controlador.buscarCompeticionesPorJuego(1);
        assertNotNull(competiciones);
        assertFalse(competiciones.isEmpty());
        assertEquals("Liga de Campeones", competiciones.get(0).getNombre());
    }

}
