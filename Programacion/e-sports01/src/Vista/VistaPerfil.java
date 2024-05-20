package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VistaPerfil extends JFrame {
    private JButton bCrear;
    private JButton bCancelar;
    private JTextField txtNombre;
    private JTextField txtContrasena;
    private JPanel Pprincipal;

    public VistaPerfil(){
        setContentPane(Pprincipal);
        setSize(400,300);
        setLocationRelativeTo(null);
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
