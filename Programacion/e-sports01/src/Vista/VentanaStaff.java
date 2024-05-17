package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaStaff extends JFrame{
    private JRadioButton rbNuevo;
    private JRadioButton rbEliminar;
    private JRadioButton rbEditar;
    private JPanel pNuevo;
    private JTextField tfNombre;
    private JPanel pEliminar;
    private JComboBox cbStaff;
    private JButton bInicio;
    private JButton bVolver;
    private JTextField tfApellido1;
    private JTextField tfApellido2;
    private JTextField tfSueldo;
    private JPanel pStaff;
    private JPanel pEditar;
    private JRadioButton rbEditarNombre;
    private JTextField tfNuevoNombre;
    private JRadioButton rbVincular;
    private JRadioButton rbDesvincular;
    private JComboBox cbNuevoEquipo;
    private JLabel labelStaffM;
    private JComboBox cbEditStaff;
    private JTextField tfNuevoApellido1;
    private JTextField tfNuevoApellido2;
    private JTextField tfNuevoSueldo;
    private JLabel labelStaff;
    private JRadioButton rbEntrenadorM;
    private JRadioButton rbAsistenteM;
    private JRadioButton rbEntrenador;
    private JRadioButton rbAsistente;
    private JComboBox cbEquipo;
    public VentanaStaff() {
        setContentPane(pStaff);
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

    public JPanel getpEliminar() {
        return pEliminar;
    }

    public void setpEliminar(JPanel pEliminar) {
        this.pEliminar = pEliminar;
    }

    public JComboBox getCbStaff() {
        return cbStaff;
    }

    public void setCbStaff(JComboBox cbStaff) {
        this.cbStaff = cbStaff;
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

    public JTextField getTfApellido1() {
        return tfApellido1;
    }

    public void setTfApellido1(JTextField tfApellido1) {
        this.tfApellido1 = tfApellido1;
    }

    public JTextField getTfApellido2() {
        return tfApellido2;
    }

    public void setTfApellido2(JTextField tfApellido2) {
        this.tfApellido2 = tfApellido2;
    }

    public JTextField getTfSueldo() {
        return tfSueldo;
    }

    public void setTfSueldo(JTextField tfSueldo) {
        this.tfSueldo = tfSueldo;
    }

    public JPanel getpStaff() {
        return pStaff;
    }

    public void setpStaff(JPanel pStaff) {
        this.pStaff = pStaff;
    }

    public JPanel getpEditar() {
        return pEditar;
    }

    public void setpEditar(JPanel pEditar) {
        this.pEditar = pEditar;
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

    public JComboBox getCbNuevoEquipo() {
        return cbNuevoEquipo;
    }

    public void setCbNuevoEquipo(JComboBox cbNuevoEquipo) {
        this.cbNuevoEquipo = cbNuevoEquipo;
    }

    public JLabel getLabelStaffM() {
        return labelStaffM;
    }

    public void setLabelStaffM(JLabel labelStaffM) {
        this.labelStaffM = labelStaffM;
    }

    public JComboBox getCbEditStaff() {
        return cbEditStaff;
    }

    public void setCbEditStaff(JComboBox cbEditStaff) {
        this.cbEditStaff = cbEditStaff;
    }

    public JTextField getTfNuevoApellido1() {
        return tfNuevoApellido1;
    }

    public void setTfNuevoApellido1(JTextField tfNuevoApellido1) {
        this.tfNuevoApellido1 = tfNuevoApellido1;
    }

    public JTextField getTfNuevoApellido2() {
        return tfNuevoApellido2;
    }

    public void setTfNuevoApellido2(JTextField tfNuevoApellido2) {
        this.tfNuevoApellido2 = tfNuevoApellido2;
    }

    public JTextField getTfNuevoSueldo() {
        return tfNuevoSueldo;
    }

    public void setTfNuevoSueldo(JTextField tfNuevoSueldo) {
        this.tfNuevoSueldo = tfNuevoSueldo;
    }

    public JLabel getLabelStaff() {
        return labelStaff;
    }

    public void setLabelStaff(JLabel labelStaff) {
        this.labelStaff = labelStaff;
    }

    public JComboBox getCbEquipo() {
        return cbEquipo;
    }

    public void setCbEquipo(JComboBox cbEquipo) {
        this.cbEquipo = cbEquipo;
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
        tfApellido1.setText("");
        tfApellido2.setText("");
        tfSueldo.setText("");
        cbStaff.setSelectedIndex(-1);
        cbEditStaff.setSelectedIndex(-1);
        tfNuevoNombre.setText("");
        tfNuevoApellido1.setText("");
        tfNuevoApellido2.setText("");
        tfNuevoSueldo.setText("");
        cbNuevoEquipo.setSelectedIndex(-1);
    }
}