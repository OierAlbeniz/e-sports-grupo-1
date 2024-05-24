package Vista;

import javax.swing.*;

public class VentanaPerfil extends JFrame {
    private JPanel pPrincipal;
    private JButton bAyuda;
    private JLabel tfIniciarSesion;
    private JTextField txtUser;
    private JTextField txtPassword;
    private JButton bSalir;
    private JButton bSesion;
    public VentanaPerfil(){
        setContentPane(pPrincipal);
        setSize(400,300);
        setLocationRelativeTo(null);
    }

}
