import Controlador.ControladorBD.ControladorTablaUsuario;
import Modelo.Usuario;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaUsuario {
    private static Connection con;
    private ControladorTablaUsuario controlador;

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
        controlador = new ControladorTablaUsuario(con);
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
    public void testBuscarUsuario() throws Exception {
        Usuario usuario = controlador.buscarUsuario("oier", "Jm1234@");
        assertNotNull(usuario);
        assertEquals("oier", usuario.getNombre());
        assertEquals("Jm1234@", usuario.getContrasena());
        assertEquals("usuario", usuario.getTipo());
    }

    @Test
    public void testCrearUsuario() throws Exception {
        Usuario usuario = controlador.crearUsuario("newuser", "password123", "usuario");
        assertNull(usuario); // Porque el usuario no existía antes de la creación

        // Verificar si el usuario se ha creado correctamente
        Usuario nuevoUsuario = controlador.buscarUsuario("newuser", "password123");
        assertNotNull(nuevoUsuario);
        assertEquals("newuser", nuevoUsuario.getNombre());
        assertEquals("password123", nuevoUsuario.getContrasena());
        assertEquals("usuario", nuevoUsuario.getTipo());
    }
}
