package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VistaPerfil extends JFrame {
    private JButton bCrear;
    private JButton bCancelar;
    private JTextField txtNombre;
    private JTextField txtContrasena;
    private JPanel Pprincipal;
    private JRadioButton RdbUsuario;
    private JRadioButton RdbAdmin;

    public VistaPerfil(){
        setContentPane(Pprincipal);
        setSize(500,500);
        setLocationRelativeTo(null);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JRadioButton getRdbUsuario() {
        return RdbUsuario;
    }

    public void setRdbUsuario(JRadioButton rdbUsuario) {
        RdbUsuario = rdbUsuario;
    }

    public JRadioButton getRdbAdmin() {
        return RdbAdmin;
    }

    public void setRdbAdmin(JRadioButton rdbAdmin) {
        RdbAdmin = rdbAdmin;
    }

    public JTextField getTextField1() {
        return txtNombre;
    }

    public void setTextField1(JTextField textField1) {
        this.txtNombre = textField1;
    }

    public JTextField getTextField2() {
        return txtContrasena;
    }

    public void setTextField2(JTextField textField2) {
        this.txtContrasena = textField2;
    }

    public JPanel getPprincipal() {
        return Pprincipal;
    }

    public void setPprincipal(JPanel pprincipal) {
        Pprincipal = pprincipal;
    }

    public void addBCancelar (ActionListener al){
        bCancelar.addActionListener(al);
    }
    public void addCrearUsuario (ActionListener al){
        bCrear.addActionListener(al);
    }
}
