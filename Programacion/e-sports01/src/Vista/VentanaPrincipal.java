package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame{
    private JPanel panel1;
    private JButton CLASIFICACIONButton;
    private JButton ULTIMAJORNADAButton;
    private JButton CERRARINSCRIPCIONESButton;
    private JButton editarButton;
    private JButton CERRARSESIONButton;
    private JButton SALIRButton;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;

    public VentanaPrincipal() {
        setContentPane(panel1);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public void initComponets()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Panel 1: 15% alto, 10% ancho

        p1.setBackground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.15;
        panel1.add(p1, gbc);

        // Panel 2: 15% alto, 90% ancho

        p2.setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        gbc.weighty = 0.15;
        panel1.add(p2, gbc);

        // Panel 3: 85% alto, 10% ancho

        p3.setBackground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.85;
        panel1.add(p3, gbc);

        // Panel 4: 15% alto, 90% ancho

        p4.setBackground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        gbc.weighty = 0.15;
        panel1.add(p4, gbc);

    }
}
