import Controlador.ControladorBD.ControladorTablaAsistente;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaAsistente {
    private static Connection con;
    private ControladorTablaAsistente controlador;

    @BeforeAll
    public static void setup() throws SQLException {
        abrirConexion();
        crearTablas();
        insertarDatos();
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

    private static void crearTablas() throws SQLException {
        Statement stmt = con.createStatement();
        // Crea las tablas necesarias
        // ...
        stmt.close();
    }

    private static void insertarDatos() throws SQLException {
        Statement stmt = con.createStatement();
        // Inserta los datos necesarios
        // ...
        stmt.close();
    }

    @BeforeEach
    public void init() throws SQLException {
        con.setAutoCommit(false); // Deshabilitar autocommit para usar transacciones
        controlador = new ControladorTablaAsistente(con);
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
    public void testCrearAsistente() throws Exception {
        // Llama al método para crear un asistente
        controlador.crearAsistente("Juan", "González", "Pérez", 2000, "Asistente");

        // Verifica que el asistente fue creado correctamente en la base de datos
        // Aquí puedes agregar alguna consulta SQL para verificar la inserción si es necesario
    }

    @Test
    public void testBorrarAsistente() throws Exception {
        // Llama al método para borrar un asistente
        controlador.borrarAsistente("David", "Los Dragones");

        // Verifica que el asistente fue eliminado correctamente de la base de datos
        // Aquí puedes agregar alguna consulta SQL para verificar la eliminación si es necesario
    }

    @Test
    public void testObtenerAsistentesPorEquipo() throws Exception {
        // Llama al método para obtener los asistentes de un equipo
        List<String> asistentes = controlador.obtenerAsistentesPorEquipo("Los Dragones");

        // Verifica que la lista de asistentes no esté vacía
        assertNotNull(asistentes);
        assertFalse(asistentes.isEmpty());
        // Puedes agregar más validaciones según tus necesidades
    }
}
