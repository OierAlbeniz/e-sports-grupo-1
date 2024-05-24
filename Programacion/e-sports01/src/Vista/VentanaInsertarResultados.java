package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class VentanaInsertarResultados extends JFrame {
    private JPanel panel2;
    private JButton bVolver;
    private JButton bInicio;
    private JTextField resultadoLocal;
    private JPanel panel1;
    private JComboBox cbCompeticiones;
    private JComboBox<Integer> cbJornadas;
    private JLabel equipoLocal;
    private JLabel equipoVisitante;
    private JTextField resultadoVisitante;
    private JPanel panelEnfrentamiento;
    private JLabel guion;
    private JPanel respuestaPanel;
    private JButton equipo2;
    private JLabel resultado1;
    private JButton equipo1;
    private JLabel resultado2;


    public VentanaInsertarResultados() {
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

    public JButton getbVolver() {
        return bVolver;
    }

    public void setbVolver(JButton bVolver) {
        this.bVolver = bVolver;
    }

    public JButton getbInicio() {
        return bInicio;
    }

    public void setbInicio(JButton bInicio) {
        this.bInicio = bInicio;
    }

    public JTextField getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(JTextField resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
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

    public JComboBox<Integer> getCbJornadas() {
        return cbJornadas;
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

    public JTextField getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(JTextField resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public void addVolver(ActionListener al) {
        bVolver.addActionListener(al);
    }
    public void addInicio(ActionListener al) {
        bInicio.addActionListener(al);
    }
    public void addCBCompeticion(ActionListener al) {
        cbCompeticiones.addActionListener(al);
    }
    public void addCBJornada(ActionListener al) {
        cbJornadas.addActionListener(al);
    }
    public void addCBJornadaFL(FocusListener fl){cbJornadas.addFocusListener(fl);}
    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }


    public JPanel getRespuestaPanel() {
        return respuestaPanel;
    }

    public void setRespuestaPanel(JPanel respuestaPanel) {
        this.respuestaPanel = respuestaPanel;
    }

    public JButton getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(JButton equipo2) {
        this.equipo2 = equipo2;
    }

    public JLabel getResultado1() {
        return resultado1;
    }

    public void setResultado1(JLabel resultado1) {
        this.resultado1 = resultado1;
    }

    public JButton getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(JButton equipo1) {
        this.equipo1 = equipo1;
    }

    public JLabel getResultado2() {
        return resultado2;
    }

    public void setResultado2(JLabel resultado2) {
        this.resultado2 = resultado2;
    }

    public void agregarPanel(JPanel panel) {
        panelEnfrentamiento.add(panel);
    }

    public void limpiarPaneles() {
        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
    }


    public void setCbJornadas(JComboBox<Integer> cbJornadas) {
        this.cbJornadas = cbJornadas;
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
