package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaEditar extends JFrame {
    private JPanel panelEditar;
    private JButton bEditJugadores;
    private JButton bEditEquipos;
    private JButton bEditCompeticiones;
    private JButton bEditPatrocinadores;
    private JButton bEditStaff;
    private JButton bEditJuegos;

    public VentanaEditar() {
        setContentPane(panelEditar);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public JPanel getPanelEditar() {
        return panelEditar;
    }

    public void setPanelEditar(JPanel panelEditar) {
        this.panelEditar = panelEditar;
    }

    public JButton getbEditJugadores() {
        return bEditJugadores;
    }

    public void setbEditJugadores(JButton bEditJugadores) {
        this.bEditJugadores = bEditJugadores;
    }

    public JButton getbEditEquipos() {
        return bEditEquipos;
    }

    public void setbEditEquipos(JButton bEditEquipos) {
        this.bEditEquipos = bEditEquipos;
    }

    public JButton getbEditCompeticiones() {
        return bEditCompeticiones;
    }

    public void setbEditCompeticiones(JButton bEditCompeticiones) {
        this.bEditCompeticiones = bEditCompeticiones;
    }

    public JButton getbEditPatrocinadores() {
        return bEditPatrocinadores;
    }

    public void setbEditPatrocinadores(JButton bEditPatrocinadores) {
        this.bEditPatrocinadores = bEditPatrocinadores;
    }

    public JButton getbEditStaff() {
        return bEditStaff;
    }

    public void setbEditStaff(JButton bEditStaff) {
        this.bEditStaff = bEditStaff;
    }

    public JButton getbEditJuegos() {
        return bEditJuegos;
    }

    public void setbEditJuegos(JButton bEditJuegos) {
        this.bEditJuegos = bEditJuegos;
    }
    public void addCompeticiones(ActionListener al) {
        bEditCompeticiones.addActionListener(al);
    }
    public void addEquipos(ActionListener al) {
        bEditEquipos.addActionListener(al);
    }
    public void addJugadores(ActionListener al) {
        bEditJugadores.addActionListener(al);
    }
    public void addStaff(ActionListener al) {
        bEditStaff.addActionListener(al);
    }
    public void addPatrocinadores(ActionListener al) {
        bEditPatrocinadores.addActionListener(al);
    }
    public void addJuegos(ActionListener al) {
        bEditJuegos.addActionListener(al);
    }
}