import Controlador.ControladorBD.ControladorTablaJornada;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaJornada {
    private static Connection con;
    private ControladorTablaJornada controlador;

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
        controlador = new ControladorTablaJornada(con);
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
    public void testBuscarJornadas() throws Exception {
        List<Integer> jornadas = controlador.buscarJornadas(1);
        assertNotNull(jornadas);
        assertEquals(2, jornadas.size());
        assertEquals(2, jornadas.get(0));
    }

    @Test
    public void testBuscarIdJorComp() throws Exception {
        Integer idJorComp = controlador.buscarIdJorComp(1, 1);
        assertNotNull(idJorComp);
        assertEquals(1, idJorComp);
    }

    @Test
    public void testObtenerIdCompeticionDesdeJornada() throws SQLException {
        Integer idCompeticion = controlador.obtenerIdCompeticionDesdeJornada(1);
        assertNotNull(idCompeticion);
        assertEquals(1, idCompeticion);
    }

    @Test
    public void testBuscarUltimaJornada() throws Exception {
        Integer ultimaJornada = controlador.buscarUltimaJornada(1);
        assertNotNull(ultimaJornada);
        assertEquals(1, ultimaJornada);
    }
}
