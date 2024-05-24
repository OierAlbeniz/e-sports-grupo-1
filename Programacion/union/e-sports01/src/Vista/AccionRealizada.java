package Vista;

import javax.swing.*;
import java.awt.event.*;

public class AccionRealizada extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel edicionTexto;


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
