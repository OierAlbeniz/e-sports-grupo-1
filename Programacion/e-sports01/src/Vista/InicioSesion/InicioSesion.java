package Vista.InicioSesion;

import javax.swing.*;
import java.awt.event.*;

public class InicioSesion extends JFrame {
    private JPanel contentPane;
    private JButton btSalir;
    private JButton btIniciar;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btduda;



    public InicioSesion() {
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setVisible(true);

        setSize(400,700);

        btSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        InicioSesion dialog = new InicioSesion();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public void btsalir (ActionListener al){
        btSalir.addActionListener(al);
    }
    public void btIniciarSesion (ActionListener al){
        btIniciar.addActionListener(al);
    }
    public void btduda (ActionListener al){
        btduda.addActionListener(al);
    }
}
