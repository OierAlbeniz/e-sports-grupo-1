package Vista;

import Modelo.Equipo;
import Modelo.Patrocinador;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

/**
 * Clase que representa la ventana de patrocinadores.
 */
public class VentanaPatrocinadores extends JFrame {
    private JRadioButton rbNuevo;
    private JRadioButton rbEliminar;
    private JRadioButton rbEditar;
    private JPanel pPatrocinadores;
    private JButton bInicio;
    private JButton bVolver;
    private JPanel pEditar;
    private JTextField tfNombrePatrocinador;
    private JPanel pEliminar;
    private JPanel pNuevo;
    private JComboBox cbEditarPatrocinadores;
    private JRadioButton rbEditarNombre;
    private JRadioButton rbVincular;
    private JRadioButton rbDesvincular;
    private JTextField tfNuevoNombre;
    private JComboBox cbEliminarPatrocinador;
    private JButton bAceptarNuevo;
    private JButton bAceptarEliminar;
    private JButton bAceptarEditar;

    public VentanaPatrocinadores() {
        setContentPane(pPatrocinadores);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public JPanel getpPatrocinadores() {
        return pPatrocinadores;
    }

    public void setpPatrocinadores(JPanel pPatrocinadores) {
        this.pPatrocinadores = pPatrocinadores;
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

    public JPanel getpEditar() {
        return pEditar;
    }

    public void setpEditar(JPanel pEditar) {
        this.pEditar = pEditar;
    }

    public JTextField getTfNombrePatrocinador() {
        return tfNombrePatrocinador;
    }

    public void setTfNombrePatrocinador(JTextField tfNombrePatrocinador) {
        this.tfNombrePatrocinador = tfNombrePatrocinador;
    }


    public JPanel getpEliminar() {
        return pEliminar;
    }

    public void setpEliminar(JPanel pEliminar) {
        this.pEliminar = pEliminar;
    }

    public JPanel getpNuevo() {
        return pNuevo;
    }

    public void setpNuevo(JPanel pNuevo) {
        this.pNuevo = pNuevo;
    }

    public JComboBox getCbEditarPatrocinadores() {
        return cbEditarPatrocinadores;
    }

    public void setCbEditarPatrocinadores(JComboBox cbEditarPatrocinadores) {
        this.cbEditarPatrocinadores = cbEditarPatrocinadores;
    }

    public JRadioButton getRbEditarNombre() {
        return rbEditarNombre;
    }

    public void setRbEditarNombre(JRadioButton rbEditarNombre) {
        this.rbEditarNombre = rbEditarNombre;
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

    public JTextField getTfNuevoNombre() {
        return tfNuevoNombre;
    }

    public void setTfNuevoNombre(JTextField tfNuevoNombre) {
        this.tfNuevoNombre = tfNuevoNombre;
    }

    public JComboBox getCbEliminarPatrocinador() {
        return cbEliminarPatrocinador;
    }

    public void setCbEliminarPatrocinador(JComboBox cbEliminarPatrocinador) {
        this.cbEliminarPatrocinador = cbEliminarPatrocinador;
    }

    public JButton getbAceptarNuevo() {
        return bAceptarNuevo;
    }

    public void setbAceptarNuevo(JButton bAceptarNuevo) {
        this.bAceptarNuevo = bAceptarNuevo;
    }

    public JButton getbAceptarEliminar() {
        return bAceptarEliminar;
    }

    public void setbAceptarEliminar(JButton bAceptarEliminar) {
        this.bAceptarEliminar = bAceptarEliminar;
    }

    public JButton getbAceptarEditar() {
        return bAceptarEditar;
    }

    public void setbAceptarEditar(JButton bAceptarEditar) {
        this.bAceptarEditar = bAceptarEditar;
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
    public void addAceptarNuevoAL(ActionListener al) {
        bAceptarNuevo.addActionListener(al);
    }
    public void addAceptarEliminarAL(ActionListener al) {
        bAceptarEliminar.addActionListener(al);
    }
    public void addAceptarEditarAL(ActionListener al) {
        bAceptarEditar.addActionListener(al);
    }
    public void addCbEditarPatrocinadoresFl(FocusListener fl){cbEditarPatrocinadores.addFocusListener(fl);}

    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }

    public void limpiar() {
        tfNombrePatrocinador.setText("");
        cbEliminarPatrocinador.setSelectedIndex(-1);
        cbEditarPatrocinadores.setSelectedIndex(-1);
        tfNuevoNombre.setText("");
    }

}
