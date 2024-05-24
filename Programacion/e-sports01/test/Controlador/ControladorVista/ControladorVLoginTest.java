package Controlador.ControladorVista;

import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorVLoginTest {

    private ControladorVLogin controladorVLogin;
    private ControladorVista controladorVista;

    @BeforeEach
    public void setUp() throws Exception {
        controladorVista = new ControladorVista();
        controladorVLogin = new ControladorVLogin(controladorVista);
    }

    @Test
    public void testActionPerformed() {
        // Crear un usuario de prueba
        Usuario user = new Usuario();
        user.setNombre("TestUser");
        user.setContrasena("TestPassword1!");

        // Añadir el usuario de prueba al controlador de vista
        controladorVista.CrearUsuario(user);

        // Configurar los campos de texto de la ventana de inicio de sesión
        controladorVLogin.getVentanaInicioSesion().getTextField1().setText("TestUser");
        controladorVLogin.getVentanaInicioSesion().getTextField2().setText("TestPassword1!");

        // Simular la acción del botón de inicio de sesión
        controladorVLogin.new BSesionAL().actionPerformed(null);

        // Comprobar que la ventana de inicio de sesión se ha cerrado
        assertFalse(controladorVLogin.getVentanaInicioSesion().isVisible());

        // Comprobar que el usuario ha iniciado sesión correctamente
        assertEquals(user, controladorVista.getUsuarioActual());
    }
}
