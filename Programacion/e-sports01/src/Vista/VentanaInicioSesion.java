package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class VentanaInicioSesion extends JDialog {
    private JPanel contentPane;
    private JButton bSalir;
    private JButton bSesion;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JLabel tfIniciarSesion;
    private JPanel pPrincipal;

    public VentanaInicioSesion() {
        setContentPane(pPrincipal);
        setSize(500,700);
        setLocationRelativeTo(null);
    }

    public JButton getbSalir() {
        return bSalir;
    }

    public void setbSalir(JButton bSalir) {
        this.bSalir = bSalir;
    }

    public JButton getbSesion() {
        return bSesion;
    }

    public void setbSesion(JButton bSesion) {
        this.bSesion = bSesion;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JLabel getTfIniciarSesion() {
        return tfIniciarSesion;
    }

    public void setTfIniciarSesion(JLabel tfIniciarSesion) {
        this.tfIniciarSesion = tfIniciarSesion;
    }

    public void addSesion(ActionListener al) {
        bSesion.addActionListener(al);
    }
}
