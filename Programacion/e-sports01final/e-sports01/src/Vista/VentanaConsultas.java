package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaConsultas extends JFrame{

    private JComboBox cbSelect;
    private JPanel pPrincipal;
    private JTextPane txaResultado;
    private JButton btSalir;

    public VentanaConsultas() {
        setTitle("Consultas");
        setSize(500, 700);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


    public JTextPane getTxaResultado() {
        return txaResultado;
    }

    public void setTxaResultado(JTextPane txaResultado) {
        this.txaResultado = txaResultado;
    }

    public JComboBox getCbSelect() {
        return cbSelect;
    }

    public void setCbSelect(JComboBox cbSelect) {
        this.cbSelect = cbSelect;
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public void setpPrincipal(JPanel pPrincipal) {
        this.pPrincipal = pPrincipal;
    }

    public JButton getBtSalir() {
        return btSalir;
    }

    public void setBtSalir(JButton btSalir) {
        this.btSalir = btSalir;
    }

    public void addbtSlir (ActionListener al) {
        btSalir.addActionListener(al);
    }

}
