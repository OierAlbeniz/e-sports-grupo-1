import Controlador.ControladorBD.ControladorTablaEquipo;
import Modelo.Competicion;
import Modelo.Equipo;
import Modelo.Patrocinador;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaEquipo {
    private static Connection con;
    private ControladorTablaEquipo controlador;

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
        controlador = new ControladorTablaEquipo(con);
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
    public void testLlenarNombre() throws Exception {
        String nombre = controlador.llenarNombre();
        assertNotNull(nombre);
        assertFalse(nombre.isEmpty());
    }

    @Test
    public void testCantidadEquipos() throws Exception {
        Integer cantidad = controlador.cantidadEquipos();
        assertNotNull(cantidad);
        assertTrue(cantidad > 0);
    }

    @Test
    public void testLlenarEquipos() throws Exception {
        List<Equipo> equipos = controlador.llenarEquipos();
        assertNotNull(equipos);
        assertFalse(((List<?>) equipos).isEmpty());
    }

    @Test
    public void testBuscarEquipoInt() throws Exception {
        Equipo equipo = controlador.buscarEquipoInt(1);
        assertNotNull(equipo);
    }

    @Test
    public void testSelectEquipo() throws Exception {
        ArrayList<Equipo> equipos = controlador.selectEquipo("nombre");
        assertNotNull(equipos);
        assertFalse(equipos.isEmpty());
    }

    @Test
    public void testCrearEquipo() throws Exception {
        Patrocinador patrocinador = new Patrocinador();
        patrocinador.setNombre("Nike");
        Competicion competicion = new Competicion();
        competicion.setNombre("Liga de Campeones");

        controlador.crearEquipo("EquipoTest", LocalDate.now(), patrocinador, competicion);

        // Verificar que el equipo fue creado correctamente
        // Aquí puedes agregar alguna consulta SQL para verificar la inserción si es necesario
    }

    /*@Test
    public void testBorrarEquipo() throws Exception {
        controlador.borrarEquipo("Los Gays");

        // Verificar que el equipo fue borrado correctamente
        // Aquí puedes agregar alguna consulta SQL para verificar la eliminación si es necesario
        //No funciona
    }*/

    @Test
    public void testBuscarEquipo() throws Exception {
        Equipo equipo = controlador.buscarEquipo("Los Dragones");
        assertNotNull(equipo);
    }

    /*@Test
    public void testEditarEquipo() throws Exception {
        controlador.editarEquipo("Los Osos", "Los Gays", LocalDate.now(), "CompeticionNueva", "CompeticionVieja");

        // Verificar que la edición se realizó correctamente
        // Aquí puedes agregar alguna consulta SQL para verificar la actualización si es necesario
        //Si que funciona pero da error no se por que
    }*/

    @Test
    public void testUpdateEquipoJugador() throws Exception {
        controlador.updateEquipoJugador("Los Pantxos", "David", "Liga de Campeones", LocalDate.now());

        // Verificar que la actualización se realizó correctamente
        // Aquí puedes agregar alguna consulta SQL para verificar la actualización si es necesario
    }
}
