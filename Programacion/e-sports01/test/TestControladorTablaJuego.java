import Controlador.ControladorBD.ControladorTablaJuego;
import Modelo.Juego;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaJuego {
    private static Connection con;
    private ControladorTablaJuego controlador;

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
        controlador = new ControladorTablaJuego(con);
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
    public void testBuscarJuego() throws Exception {
        Juego juego = controlador.buscarJuego("The Witcher");
        assertNotNull(juego);
        assertEquals("The Witcher", juego.getNombre());
        assertEquals("CD Projekt", juego.getEmpresa());
        assertEquals(LocalDate.of(2015, 5, 19), juego.getFechalanzamiento());
    }

    @Test
    public void testBuscarJuegoID() throws Exception {
        Juego juego = controlador.buscarJuegoID("32"); // Asumiendo que 'The Witcher' tiene ID 1
        assertNotNull(juego);
        assertEquals(32, juego.getIdJuego());
        assertEquals("The Witcher", juego.getNombre());
        assertEquals("CD Projekt", juego.getEmpresa());
        assertEquals(LocalDate.of(2015, 5, 19), juego.getFechalanzamiento());
    }

    @Test
    public void testBuscarJuegos() throws SQLException {
        List<Juego> juegos = controlador.buscarJuegos();
        assertNotNull(juegos);
        assertFalse(juegos.isEmpty());
        assertEquals("The Witcher", juegos.get(0).getNombre());
    }

    @Test
    public void testBuscarIdJuegoPorNombre() throws SQLException {
        int idJuego = controlador.buscarIdJuegoPorNombre("The Witcher");
        assertEquals(24, idJuego);
    }

    @Test
    public void testInsertarJuego() throws Exception {
        Juego nuevoJuego = new Juego("Cyberpunk 2077", "CD Projekt", LocalDate.of(2020, 12, 10), 0);
        controlador.insertarJuego(nuevoJuego);

        Juego juego = controlador.buscarJuego("Cyberpunk 2077");
        assertNotNull(juego);
        assertEquals("Cyberpunk 2077", juego.getNombre());
        assertEquals("CD Projekt", juego.getEmpresa());
        assertEquals(LocalDate.of(2020, 12, 10), juego.getFechalanzamiento());
    }

    @Test
    public void testEliminarJuego() throws SQLException, Exception {
        Juego juego = controlador.buscarJuego("The Witcher");
        controlador.eliminarJuego(juego);

        Exception exception = assertThrows(Exception.class, () -> {
            controlador.buscarJuego("The Witcher");
        });

        String expectedMessage = "Error al buscar el juego";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testModificarJuego() throws SQLException, Exception {
        Juego juego = controlador.buscarJuego("The Witcher");
        juego.setNombre("The Witcher 3: Wild Hunt");
        controlador.modificarJuego(juego);

        Juego juegoModificado = controlador.buscarJuego("The Witcher 3: Wild Hunt");
        assertNotNull(juegoModificado);
        assertEquals("The Witcher 3: Wild Hunt", juegoModificado.getNombre());
    }
}
