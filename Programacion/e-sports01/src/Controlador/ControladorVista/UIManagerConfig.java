package Controlador.ControladorVista;

import javax.swing.*;
import java.awt.*;

public class UIManagerConfig {

    public static void setOptionPaneBackground(Color color) {
        UIManager.put("OptionPane.background", color);
        UIManager.put("Panel.background", color);
    }
}
