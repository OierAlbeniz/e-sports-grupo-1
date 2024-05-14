package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaEquipos extends JFrame{
    private JRadioButton rbNuevo;
    private JRadioButton rbEliminar;
    private JRadioButton rbEditar;
    private JPanel pNuevo;
    private JTextField tfNombre;
    private JComboBox cbFechaFundacion;
    private JPanel pEditar;
    private JComboBox cbEquipos;
    private JRadioButton rbEditarNombre;
    private JTextField tfNuevoNombre;
    private JRadioButton rbVincular;
    private JRadioButton rbDesvincular;
    private JComboBox cbEditFecha;
    private JComboBox cbVincular;
    private JPanel pEliminar;
    private JButton bInicio;
    private JButton bVolver;
    private JPanel pEquipos;
    private JComboBox cbPatrocinador;
    private JComboBox cbCompeticion;
    private JButton bJugadores;
    private JButton bEntrenador;
    private JComboBox cbEquipo;
    private JComboBox cbDesvincular;

    public VentanaEquipos() {
        setContentPane(pEquipos);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public JRadioButton getRbNuevo() {
        return rbNuevo;
    }

    public void setRbNuevo(JRadioButton rbNuevo) {
        this.rbNuevo = rbNuevo;
    }

    public JRadioButton getRbEliminar() {
        return rbEliminar;
    }

    public void setRbEliminar(JRadioButton rbEliminar) {
        this.rbEliminar = rbEliminar;
    }

    public JRadioButton getRbEditar() {
        return rbEditar;
    }

    public void setRbEditar(JRadioButton rbEditar) {
        this.rbEditar = rbEditar;
    }

    public JPanel getpNuevo() {
        return pNuevo;
    }

    public void setpNuevo(JPanel pNuevo) {
        this.pNuevo = pNuevo;
    }

    public JTextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(JTextField tfNombre) {
        this.tfNombre = tfNombre;
    }

    public JComboBox getCbFechaFundacion() {
        return cbFechaFundacion;
    }

    public void setCbFechaFundacion(JComboBox cbFechaFundacion) {
        this.cbFechaFundacion = cbFechaFundacion;
    }

    public JPanel getpEditar() {
        return pEditar;
    }

    public void setpEditar(JPanel pEditar) {
        this.pEditar = pEditar;
    }

    public JComboBox getCbEquipos() {
        return cbEquipos;
    }

    public void setCbEquipos(JComboBox cbEquipos) {
        this.cbEquipos = cbEquipos;
    }

    public JRadioButton getRbEditarNombre() {
        return rbEditarNombre;
    }

    public void setRbEditarNombre(JRadioButton rbEditarNombre) {
        this.rbEditarNombre = rbEditarNombre;
    }

    public JTextField getTfNuevoNombre() {
        return tfNuevoNombre;
    }

    public void setTfNuevoNombre(JTextField tfNuevoNombre) {
        this.tfNuevoNombre = tfNuevoNombre;
    }

    public JRadioButton getRbVincular() {
        return rbVincular;
    }

    public void setRbVincular(JRadioButton rbVincular) {
        this.rbVincular = rbVincular;
    }

    public JRadioButton getRbDesvincular() {
        return rbDesvincular;
    }

    public void setRbDesvincular(JRadioButton rbDesvincular) {
        this.rbDesvincular = rbDesvincular;
    }

    public JComboBox getCbEditFecha() {
        return cbEditFecha;
    }

    public void setCbEditFecha(JComboBox cbEditFecha) {
        this.cbEditFecha = cbEditFecha;
    }

    public JComboBox getCbVincular() {
        return cbVincular;
    }

    public void setCbVincular(JComboBox cbVincular) {
        this.cbVincular = cbVincular;
    }

    public JPanel getpEliminar() {
        return pEliminar;
    }

    public void setpEliminar(JPanel pEliminar) {
        this.pEliminar = pEliminar;
    }

    public JButton getbInicio() {
        return bInicio;
    }

    public void setbInicio(JButton bInicio) {
        this.bInicio = bInicio;
    }

    public JButton getbVolver() {
        return bVolver;
    }

    public void setbVolver(JButton bVolver) {
        this.bVolver = bVolver;
    }

    public JPanel getpEquipos() {
        return pEquipos;
    }

    public void setpEquipos(JPanel pEquipos) {
        this.pEquipos = pEquipos;
    }

    public JComboBox getCbPatrocinador() {
        return cbPatrocinador;
    }

    public void setCbPatrocinador(JComboBox cbPatrocinador) {
        this.cbPatrocinador = cbPatrocinador;
    }

    public JComboBox getCbCompeticion() {
        return cbCompeticion;
    }

    public void setCbCompeticion(JComboBox cbCompeticion) {
        this.cbCompeticion = cbCompeticion;
    }

    public JButton getbJugadores() {
        return bJugadores;
    }

    public void setbJugadores(JButton bJugadores) {
        this.bJugadores = bJugadores;
    }

    public JButton getbEntrenador() {
        return bEntrenador;
    }

    public void setbEntrenador(JButton bEntrenador) {
        this.bEntrenador = bEntrenador;
    }

    public JComboBox getCbEquipo() {
        return cbEquipo;
    }

    public void setCbEquipo(JComboBox cbEquipo) {
        this.cbEquipo = cbEquipo;
    }

    public JComboBox getCbDesvincular() {
        return cbDesvincular;
    }

    public void setCbDesvincular(JComboBox cbDesvincular) {
        this.cbDesvincular = cbDesvincular;
    }
    public void addVolver(ActionListener al) {
        bVolver.addActionListener(al);
    }
    public void addInicio(ActionListener al) {
        bInicio.addActionListener(al);
    }


    public void addrbNuevoAL(ActionListener al) {
        rbNuevo.addActionListener(al);
    }
    public void addrbEditarAL(ActionListener al) {
        rbEditar.addActionListener(al);
    }
    public void addrbEliminarAL(ActionListener al) {
        rbEliminar.addActionListener(al);
    }


    public void limpiar() {
        tfNombre.setText("");
        cbFechaFundacion.setSelectedIndex(-1);
        cbPatrocinador.setSelectedIndex(-1);
        cbCompeticion.setSelectedIndex(-1);
        cbEquipo.setSelectedIndex(-1);
        cbEquipos.setSelectedIndex(-1);
        tfNuevoNombre.setText("");
        cbEditFecha.setSelectedIndex(-1);
        cbVincular.setSelectedIndex(-1);
        cbDesvincular.setSelectedIndex(-1);
    }
}
