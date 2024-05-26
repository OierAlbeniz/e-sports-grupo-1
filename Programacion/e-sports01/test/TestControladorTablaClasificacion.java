import Controlador.ControladorBD.ControladorTablaClasificacion;
import Modelo.Clasificacion;
import Modelo.Enfrentamiento;
import Modelo.Equipo;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControladorTablaClasificacion {

    private static Connection con;
    private ControladorTablaClasificacion controlador;

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
        controlador = new ControladorTablaClasificacion(con);
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
    public void testLlenarEquiposCompeticion() throws Exception {
        List<String> equipos = controlador.llenarEquiposCompeticion(1);
        assertNotNull(equipos);
        assertFalse(equipos.isEmpty());
        assertEquals("1", equipos.get(0));
    }
    @Test
    public void testActualizarTablaClasificacion() throws Exception {
        Equipo equipoUno = new Equipo();
        equipoUno.setIdEquipo(1);
        Equipo equipoDos = new Equipo();
        equipoDos.setIdEquipo(2); // Asumiendo que hay un segundo equipo para esta prueba

        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setEquipoUno(equipoUno);
        enfrentamiento.setEquipoDos(equipoDos);

        controlador.actualizarTablaClasificacion(enfrentamiento, 1, 1, 0);

        // Verifica que la actualización se realizó correctamente
        String query = "SELECT puntos FROM clasificacion WHERE id_equipo = ? AND id_competicion = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, 1);
        stmt.setInt(2, 1);
        ResultSet rs = stmt.executeQuery();
        int puntos = 0;
        if (rs.next()) {
            puntos = rs.getInt("puntos");
        }
        rs.close();
        stmt.close();

        assertEquals(3, puntos); // Suponiendo que 3 puntos por victoria
    }

    @Test
    public void testObtenerClasificacionesPorCompeticion() throws Exception {
        List<Clasificacion> clasificaciones = controlador.obtenerClasificacionesPorCompeticion(1);
        assertNotNull(clasificaciones);
        assertFalse(clasificaciones.isEmpty());
        // Verifica que el nombre del equipo es correcto
        String nombreEquipo = clasificaciones.get(0).getListaEquipo().get(0).getNombre();
        assertEquals("Los Dragones", nombreEquipo);
    }
}
