package Vista;

import javax.swing.*;

public class VentanaEquipo extends JFrame{
    private JRadioButton altaRadioButton;
    private JRadioButton modificacionRadioButton;
    private JRadioButton bajaRadioButton;
    private JRadioButton consultaRadioButton;
    private JPanel panelAlta;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JButton cancelarButton;
    private JButton inscribirButton;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JTextField textField3;
    private JButton cancelarButton1;
    private JButton borrarEquipoButton;
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
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.comboBox2 = comboBox2;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
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
        this.textField2 = textField2;
        this.comboBox1 = comboBox1;
        this.cancelarButton = cancelarButton;
        this.inscribirButton = inscribirButton;
        this.comboBox2 = comboBox2;
        this.textArea1 = textArea1;
        this.textField3 = textField3;
        this.cancelarButton1 = cancelarButton1;
        this.borrarEquipoButton = borrarEquipoButton;
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
