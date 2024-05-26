import Controlador.ControladorBD.ControladorTablaJugador;
import Modelo.Jugador;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaJugador {
    private static Connection con;
    private ControladorTablaJugador controlador;

    @BeforeAll
    public static void setup() throws SQLException {
        abrirConexion();
    }

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
        controlador = new ControladorTablaJugador(con);
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
    public void testLlenarJugadores() throws Exception {
        List<Jugador> jugadores = controlador.llenarJugadores(1);
        assertNotNull(jugadores);
        assertFalse(jugadores.isEmpty());
        assertEquals(5, jugadores.size()); // Suponiendo que hay 5 jugadores en el equipo 1
    }

    @Test
    public void testCrearJugador() throws Exception {
        LocalDate fechaNacimiento = LocalDate.of(1995, 5, 15);
        controlador.crearJugador("Carlos", "Perez", "Lopez", 2500, "España", fechaNacimiento, "Carlitos", "Delantero", "1");

        List<Jugador> jugadores = controlador.llenarJugadores(1);
        boolean jugadorCreado = jugadores.stream().anyMatch(j -> j.getNombre().equals("Carlos") && j.getApellido1().equals("Perez"));
        assertTrue(jugadorCreado);
    }

    @Test
    public void testEliminarJugador() throws Exception {
        controlador.eliminarJugador("Alex", "Los Dragones");
        List<Jugador> jugadores = controlador.llenarJugadores(1);
        boolean jugadorEliminado = jugadores.stream().noneMatch(j -> j.getNombre().equals("Alex"));
        assertTrue(jugadorEliminado);
    }

    @Test
    public void testActualizarJugador() throws Exception {
        Jugador jugador = controlador.actualizarJugador("Sophia", "Los Dragones");
        assertNotNull(jugador);
        assertEquals("Sophia", jugador.getNombre());
        assertEquals("Lopez", jugador.getApellido1());
    }

    @Test
    public void testEditarJugadorConfir() throws Exception {
        LocalDate nuevaFechaNacimiento = LocalDate.of(1992, 8, 20);
        controlador.editarJugadorConfir("Sophie", "Lopez", "Rodriguez", 2000, "Francia", nuevaFechaNacimiento, "Sophy", "Defensa", "Los Leones", "Sophia", "Los Dragones");

        Jugador jugador = controlador.actualizarJugador("Sophie", "Los Leones");
        assertNotNull(jugador);
        assertEquals("Sophie", jugador.getNombre());
        assertEquals("Lopez", jugador.getApellido1());
        assertEquals("Rodriguez", jugador.getApellido2());
        assertEquals("Francia", jugador.getNacionalidad());
    }
}
