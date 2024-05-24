package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaUltJornada extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JComboBox cbCompeticiones;
    private JButton bInicio;
    private JPanel panelEnfrentamiento;
    private JLabel equipoLocal;
    private JLabel guion;
    private JLabel equipoVisitante;
    private JComboBox comboBox1;
    private JLabel equipo1;
    private JLabel equipo2;
    private JLabel resul1;
    private JLabel resul2;

    public VentanaUltJornada() {
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JPanel getPanelEnfrentamiento() {
        return panelEnfrentamiento;
    }

    public void setPanelEnfrentamiento(JPanel panelEnfrentamiento) {
        this.panelEnfrentamiento = panelEnfrentamiento;
    }


    public JButton getbInicio() {
        return bInicio;
    }

    public void setbInicio(JButton bInicio) {
        this.bInicio = bInicio;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JComboBox getCbCompeticiones() {
        return cbCompeticiones;
    }

    public void setCbCompeticiones(JComboBox cbCompeticiones) {
        this.cbCompeticiones = cbCompeticiones;
    }


    public JLabel getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(JLabel equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public JLabel getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(JLabel equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }


    public void addInicio(ActionListener al) {
        bInicio.addActionListener(al);
    }
    public void addCBCompeticion(ActionListener al) {
        cbCompeticiones.addActionListener(al);
    }

    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }




    public void agregarPanel(JPanel panel) {
        panelEnfrentamiento.add(panel);
    }

    public void limpiarPaneles() {
        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
    }


    public JLabel getGuion() {
        return guion;
    }

    public void setGuion(JLabel guion) {
        this.guion = guion;
    }

    public void actualizarVista() {
        panel2.revalidate();
        panel2.repaint();
    }

}
