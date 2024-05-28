package Controlador.ControladorVista;

import javax.swing.*;
import java.awt.*;
/**
 * Configuración personalizada para el aspecto visual de los componentes de Swing.
 */
public class UIManagerConfig {
    /**
     * Establece el color de fondo para los cuadros de diálogo de opción.
     *
     * @param color El color a establecer como fondo.
     */
    public static void setOptionPaneBackground(Color color) {
        UIManager.put("OptionPane.background", color);
        UIManager.put("Panel.background", color);
    }
}
