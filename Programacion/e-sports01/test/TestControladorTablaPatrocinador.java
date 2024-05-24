import Controlador.ControladorBD.ControladorTablaPatrocinador;
import Modelo.Patrocinador;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaPatrocinador {
    private static Connection con;
    private ControladorTablaPatrocinador controlador;

    @BeforeAll
    public static void setup() throws SQLException {
        abrirConexion();
    }

    private static void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
            String user = "equipo16";
            String passwd = "equipo16";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("conexion abierta");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("conexion erronea");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("conexion erronea");
        }
    }

    @BeforeEach
    public void init() throws SQLException {
        con.setAutoCommit(false); // Deshabilitar autocommit para usar transacciones
        controlador = new ControladorTablaPatrocinador(con);
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
    public void testBuscarPatrocinador() throws SQLException {
        List<String> patrocinadores = controlador.buscarPatrocinador();
        assertNotNull(patrocinadores);
        assertFalse(patrocinadores.isEmpty());
    }

    @Test
    public void testPatrocinadorExiste() throws SQLException {
        boolean existe = controlador.patrocinadorExiste(3, "Nike");
        assertTrue(existe);
    }

    @Test
    public void testLlenarPatrocinadorNombre() throws SQLException {
        List<Patrocinador> patrocinadores = controlador.llenarPatrocinadorNombre("Equipo1");
        assertNotNull(patrocinadores);
        assertFalse(patrocinadores.isEmpty());
    }

    @Test
    public void testActualizarPatrocinador() throws SQLException {
        Patrocinador patrocinador = controlador.actualizarPatrocinador("Patrocinador1", "Equipo1");
        assertNotNull(patrocinador);
        assertEquals("Patrocinador1", patrocinador.getNombre());
    }

    @Test
    public void testBuscarPatrocinadorNombre() {
        Patrocinador patrocinador = controlador.buscarPatrocinadorNombre("Patrocinador2");
        assertNotNull(patrocinador);
        assertEquals("Patrocinador2", patrocinador.getNombre());
    }

    @Test
    public void testInsertarPatrocinadores() throws Exception {
        Patrocinador nuevoPatrocinador = new Patrocinador();
        nuevoPatrocinador.setNombre("PatrocinadorNuevo");
        controlador.insertarPatrocinadores(nuevoPatrocinador);
        Patrocinador patrocinador = controlador.buscarPatrocinadorNombre("PatrocinadorNuevo");
        assertEquals("PatrocinadorNuevo", patrocinador.getNombre());
    }

    @Test
    public void testBorrarPatrocinador() throws Exception {
        controlador.borrarPatrocinador("Patrocinador2");
        Exception exception = assertThrows(RuntimeException.class, () -> controlador.buscarPatrocinadorNombre("Patrocinador2"));
        assertEquals("No se encontró ningún patrocinador con el nombre proporcionado.", exception.getMessage());
    }

    @Test
    public void testEditarPatrocinador() throws Exception {
        Patrocinador p = new Patrocinador();
        p.setNombre("Colacao");
        controlador.editarPatrocinador("PatrocinadorActualizado");
        Patrocinador patrocinador = controlador.buscarPatrocinadorNombre("PatrocinadorActualizado");
        assertEquals("PatrocinadorActualizado", patrocinador.getNombre());
    }

    @Test
    public void testBuscarPatrocinadorEliminar() throws Exception {
        Patrocinador patrocinador = controlador.buscarPatrocinadorEliminar("Nike");
        assertNotNull(patrocinador);
        assertEquals("Nike", patrocinador.getNombre());
    }
}
