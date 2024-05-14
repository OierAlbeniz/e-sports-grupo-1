package Vista;

import javax.swing.*;

public class VentanaEquipo extends JFrame{
    private JRadioButton altaRadioButton;
    private JRadioButton modificacionRadioButton;
    private JRadioButton bajaRadioButton;
    private JRadioButton consultaRadioButton;
    private JPanel panelAlta;
    private JTextField textField1;
    private JTextField txtAFecha;
    private JComboBox cbbAPatrocinador;
    private JButton btACncelar;
    private JButton btAInscribir;
    private JComboBox cbbACompeticion;
    private JTextArea txaBSlect;
    private JTextField txtBNombre;
    private JButton cancelarButton1;
    private JButton txtBBorrar;
    private JPanel panelBaja;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton modificarButton;
    private JButton cancelarButton2;
    private JTextField textField6;
    private JTextArea textArea2;
    private JPanel panelConsulta;
    private JPanel Pprincipal;


    public VentanaEquipo(){
        setContentPane(Pprincipal);
        setSize(400,300);
        setLocationRelativeTo(null);
    }

    public JRadioButton getAltaRadioButton() {
        return altaRadioButton;
    }

    public void setAltaRadioButton(JRadioButton altaRadioButton) {
        this.altaRadioButton = altaRadioButton;
    }

    public JRadioButton getModificacionRadioButton() {
        return modificacionRadioButton;
    }

    public void setModificacionRadioButton(JRadioButton modificacionRadioButton) {
        this.modificacionRadioButton = modificacionRadioButton;
    }

    public JRadioButton getBajaRadioButton() {
        return bajaRadioButton;
    }

    public void setBajaRadioButton(JRadioButton bajaRadioButton) {
        this.bajaRadioButton = bajaRadioButton;
    }

    public JRadioButton getConsultaRadioButton() {
        return consultaRadioButton;
    }

    public void setConsultaRadioButton(JRadioButton consultaRadioButton) {
        this.consultaRadioButton = consultaRadioButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return txtAFecha;
    }

    public void setTextField2(JTextField textField2) {
        this.txtAFecha = textField2;
    }

    public JComboBox getComboBox1() {
        return cbbAPatrocinador;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.cbbAPatrocinador = comboBox1;
    }

    public JComboBox getComboBox2() {
        return cbbACompeticion;
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.cbbACompeticion = comboBox2;
    }

    public JTextArea getTextArea1() {
        return txaBSlect;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.txaBSlect = textArea1;
    }

    public JTextField getTextField3() {
        return txtBNombre;
    }

    public void setTextField3(JTextField textField3) {
        this.txtBNombre = textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public void setTextField5(JTextField textField5) {
        this.textField5 = textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public void setTextField6(JTextField textField6) {
        this.textField6 = textField6;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public void setTextArea2(JTextArea textArea2) {
        this.textArea2 = textArea2;
    }

    public VentanaEquipo(JRadioButton altaRadioButton, JRadioButton modificacionRadioButton, JRadioButton bajaRadioButton, JRadioButton consultaRadioButton, JPanel panelAlta, JTextField textField1, JTextField textField2, JComboBox comboBox1, JButton cancelarButton, JButton inscribirButton, JComboBox comboBox2, JTextArea textArea1, JTextField textField3, JButton cancelarButton1, JButton borrarEquipoButton, JPanel panelBaja, JTextField textField4, JTextField textField5, JComboBox comboBox3, JComboBox comboBox4, JButton modificarButton, JButton cancelarButton2, JTextField textField6, JTextArea textArea2, JPanel panelConsulta) {
        this.altaRadioButton = altaRadioButton;
        this.modificacionRadioButton = modificacionRadioButton;
        this.bajaRadioButton = bajaRadioButton;
        this.consultaRadioButton = consultaRadioButton;
        this.panelAlta = panelAlta;
        this.textField1 = textField1;
        this.txtAFecha = textField2;
        this.cbbAPatrocinador = comboBox1;
        this.btACncelar = cancelarButton;
        this.btAInscribir = inscribirButton;
        this.cbbACompeticion = comboBox2;
        this.txaBSlect = textArea1;
        this.txtBNombre = textField3;
        this.cancelarButton1 = cancelarButton1;
        this.txtBBorrar = borrarEquipoButton;
        this.panelBaja = panelBaja;
        this.textField4 = textField4;
        this.textField5 = textField5;
        this.comboBox3 = comboBox3;
        this.comboBox4 = comboBox4;
        this.modificarButton = modificarButton;
        this.cancelarButton2 = cancelarButton2;
        this.textField6 = textField6;
        this.textArea2 = textArea2;
        this.panelConsulta = panelConsulta;
    }



}
