package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Staff {
    private JPanel pPrincipal;
    private JRadioButton rdAlta;
    private JRadioButton rdBja;
    private JRadioButton rdModificacion;
    private JRadioButton rdConsulta;
    private JPanel altaStaff;
    private JRadioButton rdAEntrenador;
    private JRadioButton rdAAsistente;
    private JTextField txtANombre;
    private JTextField txtAPApellido;
    private JTextField txtASApellido;
    private JComboBox cbbAEquipo;
    private JPanel panelBaja;
    private JTextField txtBDni;
    private JTextArea txaBreslubaja;
    private JButton btAInscribir;
    private JButton btACancelar;
    private JButton borrarButton;
    private JButton btBCancelar;
    private JTextField txtADNI;
    private JTextField txtMDNI;
    private JTextField txtMSueldo;
    private JTextField txtASueldo;
    private JTextField txtMNombre;
    private JTextField txtMApellidoUno;
    private JTextField txtMApellidoDos;
    private JComboBox cbbMEquipo;
    private JRadioButton rdMEntrenador;
    private JRadioButton rdMAsistente;
    private JButton btMModificar;
    private JButton btMCancelar;
    private JPanel panelConsulta;
    private JPanel panelModificacion;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton salirButton;

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public void setpPrincipal(JPanel pPrincipal) {
        this.pPrincipal = pPrincipal;
    }

    public JRadioButton getAltaRadioButton() {
        return rdAlta;
    }

    public void setAltaRadioButton(JRadioButton altaRadioButton) {
        this.rdAlta = altaRadioButton;
    }

    public JRadioButton getBajaRadioButton() {
        return rdBja;
    }

    public void setBajaRadioButton(JRadioButton bajaRadioButton) {
        this.rdBja = bajaRadioButton;
    }

    public JRadioButton getModificacionRadioButton() {
        return rdModificacion;
    }

    public void setModificacionRadioButton(JRadioButton modificacionRadioButton) {
        this.rdModificacion = modificacionRadioButton;
    }

    public JRadioButton getConsultaRadioButton() {
        return rdConsulta;
    }

    public void setConsultaRadioButton(JRadioButton consultaRadioButton) {
        this.rdConsulta = consultaRadioButton;
    }

    public JPanel getAltaStaff() {
        return altaStaff;
    }

    public void setAltaStaff(JPanel altaStaff) {
        this.altaStaff = altaStaff;
    }

    public JRadioButton getRdAEntrenador() {
        return rdAEntrenador;
    }

    public void setRdAEntrenador(JRadioButton rdAEntrenador) {
        this.rdAEntrenador = rdAEntrenador;
    }

    public JRadioButton getRdAAsistente() {
        return rdAAsistente;
    }

    public void setRdAAsistente(JRadioButton rdAAsistente) {
        this.rdAAsistente = rdAAsistente;
    }

    public JTextField getTxtANombre() {
        return txtANombre;
    }

    public void setTxtANombre(JTextField txtANombre) {
        this.txtANombre = txtANombre;
    }

    public JTextField getTxtAPApellido() {
        return txtAPApellido;
    }

    public void setTxtAPApellido(JTextField txtAPApellido) {
        this.txtAPApellido = txtAPApellido;
    }

    public JTextField getTxtASApellido() {
        return txtASApellido;
    }

    public void setTxtASApellido(JTextField txtASApellido) {
        this.txtASApellido = txtASApellido;
    }

    public JComboBox getCbbAEquipo() {
        return cbbAEquipo;
    }

    public void setCbbAEquipo(JComboBox cbbAEquipo) {
        this.cbbAEquipo = cbbAEquipo;
    }

    public JTextField getTxtBDni() {
        return txtBDni;
    }

    public void setTxtBDni(JTextField txtBDni) {
        this.txtBDni = txtBDni;
    }

    public JTextArea getTxaBreslubaja() {
        return txaBreslubaja;
    }

    public void setTxaBreslubaja(JTextArea txaBreslubaja) {
        this.txaBreslubaja = txaBreslubaja;
    }

    public JButton getBtAInscribir() {
        return btAInscribir;
    }

    public void setBtAInscribir(JButton btAInscribir) {
        this.btAInscribir = btAInscribir;
    }

    public JButton getBtACancelar() {
        return btACancelar;
    }

    public void setBtACancelar(JButton btACancelar) {
        this.btACancelar = btACancelar;
    }

    public JButton getBorrarButton() {
        return borrarButton;
    }

    public void setBorrarButton(JButton borrarButton) {
        this.borrarButton = borrarButton;
    }

    public JButton getBtBCancelar() {
        return btBCancelar;
    }

    public void setBtBCancelar(JButton btBCancelar) {
        this.btBCancelar = btBCancelar;
    }

    public JTextField getTxtADNI() {
        return txtADNI;
    }

    public void setTxtADNI(JTextField txtADNI) {
        this.txtADNI = txtADNI;
    }

    public JTextField getTxtMDNI() {
        return txtMDNI;
    }

    public void setTxtMDNI(JTextField txtMDNI) {
        this.txtMDNI = txtMDNI;
    }

    public JTextField getTxtMSueldo() {
        return txtMSueldo;
    }

    public void setTxtMSueldo(JTextField txtMSueldo) {
        this.txtMSueldo = txtMSueldo;
    }

    public JTextField getTxtASueldo() {
        return txtASueldo;
    }

    public void setTxtASueldo(JTextField txtASueldo) {
        this.txtASueldo = txtASueldo;
    }

    public JTextField getTxtMNombre() {
        return txtMNombre;
    }

    public void setTxtMNombre(JTextField txtMNombre) {
        this.txtMNombre = txtMNombre;
    }

    public JTextField getTxtMApellidoUno() {
        return txtMApellidoUno;
    }

    public void setTxtMApellidoUno(JTextField txtMApellidoUno) {
        this.txtMApellidoUno = txtMApellidoUno;
    }

    public JTextField getTxtMApellidoDos() {
        return txtMApellidoDos;
    }

    public void setTxtMApellidoDos(JTextField txtMApellidoDos) {
        this.txtMApellidoDos = txtMApellidoDos;
    }

    public JComboBox getCbbMEquipo() {
        return cbbMEquipo;
    }

    public void setCbbMEquipo(JComboBox cbbMEquipo) {
        this.cbbMEquipo = cbbMEquipo;
    }

    public JRadioButton getRdMEntrenador() {
        return rdMEntrenador;
    }

    public void setRdMEntrenador(JRadioButton rdMEntrenador) {
        this.rdMEntrenador = rdMEntrenador;
    }

    public JRadioButton getRdMAsistente() {
        return rdMAsistente;
    }

    public void setRdMAsistente(JRadioButton rdMAsistente) {
        this.rdMAsistente = rdMAsistente;
    }

    public JButton getBtMModificar() {
        return btMModificar;
    }

    public void setBtMModificar(JButton btMModificar) {
        this.btMModificar = btMModificar;
    }

    public JButton getBtMCancelar() {
        return btMCancelar;
    }

    public void setBtMCancelar(JButton btMCancelar) {
        this.btMCancelar = btMCancelar;
    }

    public JPanel getPanelConsulta() {
        return panelConsulta;
    }

    public void setPanelConsulta(JPanel panelConsulta) {
        this.panelConsulta = panelConsulta;
    }

    public JPanel getPanelModificacion() {
        return panelModificacion;
    }

    public void setPanelModificacion(JPanel panelModificacion) {
        this.panelModificacion = panelModificacion;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }

    public void addradioAlta(ActionListener al){
     rdAlta.addActionListener(al);
    }

}
