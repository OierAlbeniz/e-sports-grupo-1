package Vista;

import javax.swing.*;
import java.awt.event.*;

public class VentanaInicioSesion extends JDialog {
    private JPanel contentPane;
    private JButton bSalir;
    private JButton bSesion;
    private JTextField txtUser;
    private JTextField txtPassword;
    private JButton bAyuda;
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
        return txtUser;
    }

    public void setTextField1(JTextField textField1) {
        this.txtUser = textField1;
    }

    public JTextField getTextField2() {
        return txtPassword;
    }

    public void setTextField2(JTextField textField2) {
        this.txtPassword = textField2;
    }

    public JButton getButton1() {
        return bAyuda;
    }

    public void setButton1(JButton button1) {
        this.bAyuda = button1;
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
    public void addAyuda(ActionListener al){
        bAyuda.addActionListener(al);
    }
    public void addCerrar(ActionListener al){
        bSalir.addActionListener(al);
    }
}
