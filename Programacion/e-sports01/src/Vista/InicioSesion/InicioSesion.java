package Vista.InicioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class InicioSesion extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JLabel tfIniciarSesion;

    public InicioSesion() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
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

        // Aplicar la tipografía personalizada al JLabel tfIniciarSesion
        try {
            InputStream fontStream = InicioSesion.class.getResourceAsStream("/fonts/blaster.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            Font customFontSized = customFont.deriveFont(24f);
            tfIniciarSesion.setFont(customFontSized);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
