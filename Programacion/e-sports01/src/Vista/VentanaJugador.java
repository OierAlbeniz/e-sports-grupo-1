package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class VentanaJugador extends JDialog {
    private JPanel pPrincipal;
    private JPanel panelConsulta;
    private JTextField tfNombreEquipoConsulta;
    private JTextArea taDatosConsulta;
    private JButton bModificar;
    private JButton bCancelarModificacion;
    private JPanel panelAlta;
    private JTextField tfApellido1;
    private JButton bCancelarAlta;
    private JButton bInscribirJugador;
    private JRadioButton rbAlta;
    private JRadioButton rbModificacion;
    private JRadioButton rbConsulta;
    private JRadioButton rbBaja;
    private JPanel panelBaja;
    private JTextField tfNombreJugadorBaja;
    private JTextArea taDatosJugador;
    private JButton bCancelarBaja;
    private JButton bBorrarJugador;
    private JTextField tfApellido2;
    private JTextField tfNacionalidad;
    private JTextField tfFechaNacimiento;
    private JTextField tfSueldo;
    private JTextField tfNombreJugadorAlta;
    private JTextField tfNickname;
    private JTextField tfRol;
    private JComboBox cbEquipos;
    private JTextField tfNombreJugadorModificacion;
    private JTextField tfApellido1Modificacion;
    private JTextField tfApellido2Modificacion;
    private JTextField tfSueldoModificacion;
    private JTextField tfNacionalidadModificacion;
    private JTextField tfFechaNacimientoModificacion;
    private JTextField tfNicknameModificacion;
    private JTextField tfRolModificacion;
    private JComboBox cb;
    private JButton bSalirConsulta;

    public VentanaJugador() {
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        setSize(500,300);
    }

    public JTextField getTfNombreEquipoConsulta() {
        return tfNombreEquipoConsulta;
    }

    public void setTfNombreEquipoConsulta(JTextField tfNombreEquipoConsulta) {
        this.tfNombreEquipoConsulta = tfNombreEquipoConsulta;
    }

    public JTextArea getTaDatosConsulta() {
        return taDatosConsulta;
    }

    public void setTaDatosConsulta(JTextArea taDatosConsulta) {
        this.taDatosConsulta = taDatosConsulta;
    }

    public JTextField getTfApellido1() {
        return tfApellido1;
    }

    public void setTfApellido1(JTextField tfApellido1) {
        this.tfApellido1 = tfApellido1;
    }

    public JTextField getTfNombreJugadorBaja() {
        return tfNombreJugadorBaja;
    }

    public void setTfNombreJugadorBaja(JTextField tfNombreJugadorBaja) {
        this.tfNombreJugadorBaja = tfNombreJugadorBaja;
    }

    public JTextArea getTaDatosJugador() {
        return taDatosJugador;
    }

    public void setTaDatosJugador(JTextArea taDatosJugador) {
        this.taDatosJugador = taDatosJugador;
    }

    public JTextField getTfApellido2() {
        return tfApellido2;
    }

    public void setTfApellido2(JTextField tfApellido2) {
        this.tfApellido2 = tfApellido2;
    }

    public JTextField getTfNacionalidad() {
        return tfNacionalidad;
    }

    public void setTfNacionalidad(JTextField tfNacionalidad) {
        this.tfNacionalidad = tfNacionalidad;
    }

    public JTextField getTfFechaNacimiento() {
        return tfFechaNacimiento;
    }

    public void setTfFechaNacimiento(JTextField tfFechaNacimiento) {
        this.tfFechaNacimiento = tfFechaNacimiento;
    }

    public JTextField getTfSueldo() {
        return tfSueldo;
    }

    public void setTfSueldo(JTextField tfSueldo) {
        this.tfSueldo = tfSueldo;
    }

    public JTextField getTfNombreJugadorAlta() {
        return tfNombreJugadorAlta;
    }

    public void setTfNombreJugadorAlta(JTextField tfNombreJugadorAlta) {
        this.tfNombreJugadorAlta = tfNombreJugadorAlta;
    }

    public JTextField getTfNickname() {
        return tfNickname;
    }

    public void setTfNickname(JTextField tfNickname) {
        this.tfNickname = tfNickname;
    }

    public JTextField getTfRol() {
        return tfRol;
    }

    public void setTfRol(JTextField tfRol) {
        this.tfRol = tfRol;
    }

    public JComboBox getCbEquipos() {
        return cbEquipos;
    }

    public void setCbEquipos(JComboBox cbEquipos) {
        this.cbEquipos = cbEquipos;
    }

    public JTextField getTfNombreJugadorModificacion() {
        return tfNombreJugadorModificacion;
    }

    public void setTfNombreJugadorModificacion(JTextField tfNombreJugadorModificacion) {
        this.tfNombreJugadorModificacion = tfNombreJugadorModificacion;
    }

    public JTextField getTfApellido1Modificacion() {
        return tfApellido1Modificacion;
    }

    public void setTfApellido1Modificacion(JTextField tfApellido1Modificacion) {
        this.tfApellido1Modificacion = tfApellido1Modificacion;
    }

    public JTextField getTfApellido2Modificacion() {
        return tfApellido2Modificacion;
    }

    public void setTfApellido2Modificacion(JTextField tfApellido2Modificacion) {
        this.tfApellido2Modificacion = tfApellido2Modificacion;
    }

    public JTextField getTfSueldoModificacion() {
        return tfSueldoModificacion;
    }

    public void setTfSueldoModificacion(JTextField tfSueldoModificacion) {
        this.tfSueldoModificacion = tfSueldoModificacion;
    }

    public JTextField getTfNacionalidadModificacion() {
        return tfNacionalidadModificacion;
    }

    public void setTfNacionalidadModificacion(JTextField tfNacionalidadModificacion) {
        this.tfNacionalidadModificacion = tfNacionalidadModificacion;
    }

    public JTextField getTfFechaNacimientoModificacion() {
        return tfFechaNacimientoModificacion;
    }

    public void setTfFechaNacimientoModificacion(JTextField tfFechaNacimientoModificacion) {
        this.tfFechaNacimientoModificacion = tfFechaNacimientoModificacion;
    }

    public JTextField getTfNicknameModificacion() {
        return tfNicknameModificacion;
    }

    public void setTfNicknameModificacion(JTextField tfNicknameModificacion) {
        this.tfNicknameModificacion = tfNicknameModificacion;
    }

    public JTextField getTfRolModificacion() {
        return tfRolModificacion;
    }

    public void setTfRolModificacion(JTextField tfRolModificacion) {
        this.tfRolModificacion = tfRolModificacion;
    }

    public JComboBox getCb() {
        return cb;
    }

    public void setCb(JComboBox cb) {
        this.cb = cb;
    }

    public void addrbAltaAl (ActionListener al){
        rbAlta.addActionListener(al);
    }
    public void addrbBajaAl (ActionListener al){
        rbBaja.addActionListener(al);
    }
    public void addrbConsultaAl (ActionListener al){
        rbConsulta.addActionListener(al);
    }
    public void addrbModificacionAl (ActionListener al){
        rbModificacion.addActionListener(al);
    }
    public void addbCancelarAltaAl (ActionListener al){
        bCancelarAlta.addActionListener(al);
    }
    public void addbInscribirJugadorAl (ActionListener al){
        bInscribirJugador.addActionListener(al);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addtfNombreJugadorBajaAl (FocusListener fl){
        tfNombreJugadorBaja.addFocusListener(fl);
    }
    public void addbCancelarBajaAl (ActionListener al){
        bCancelarBaja.addActionListener(al);
    }
    public void addbBorrarJugadorAl (ActionListener al){
        bBorrarJugador.addActionListener(al);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addbCancelarModificacionAl (ActionListener al){
        bCancelarModificacion.addActionListener(al);
    }
    public void addbModificarAl (ActionListener al){
        bModificar.addActionListener(al);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }
}
