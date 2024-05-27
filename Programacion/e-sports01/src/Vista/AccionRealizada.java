package Vista;

import javax.swing.*;
import java.awt.event.*;
/**
 * La clase AccionRealizada representa un diálogo que muestra un mensaje
 * indicando que una acción ha sido realizada.
 */
public class AccionRealizada extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel edicionTexto;

    /**
     * Constructor de la clase AccionRealizada.
     */
    public AccionRealizada() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(900,200);
        setLocationRelativeTo(null);
    }

    public JLabel getEdicionTexto() {
        return edicionTexto;
    }

    public void setEdicionTexto(JLabel edicionTexto) {
        this.edicionTexto = edicionTexto;
    }

    public void addRealizadaAL (ActionListener al){
        buttonOK.addActionListener(al);
    }
}
