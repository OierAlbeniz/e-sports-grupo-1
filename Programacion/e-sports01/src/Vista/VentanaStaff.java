package Vista;

import javax.swing.*;
<<<<<<< HEAD:Programacion/union/e-sports01/src/Vista/VentanaStaff.java
import java.awt.event.ActionListener;
=======
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
>>>>>>> d675d12fa0793e4066258ec58a6bcbeca432a0fc:Programacion/e-sports01/src/Vista/VentanaStaff.java

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
<<<<<<< HEAD:Programacion/union/e-sports01/src/Vista/VentanaStaff.java
    private JRadioButton rbEntrenadorM;
    private JRadioButton rbAsistenteM;
    private JRadioButton rbEntrenador;
    private JRadioButton rbAsistente;
    private JComboBox jbEquipo;
    private JButton btAceptar;
    private JButton bAceptarEliminar;
=======
    private JRadioButton rbEntrenador;
    private JRadioButton rbAsistente;
    private JRadioButton rbEntrenadorM;
    private JRadioButton rbAsistenteM;
    private JPanel panel2;
>>>>>>> d675d12fa0793e4066258ec58a6bcbeca432a0fc:Programacion/e-sports01/src/Vista/VentanaStaff.java
    private JComboBox cbEquipo;
    public VentanaStaff() {
        setContentPane(pStaff);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
<<<<<<< HEAD:Programacion/union/e-sports01/src/Vista/VentanaStaff.java
    }

    public JRadioButton getRbEntrenadorM() {
        return rbEntrenadorM;
    }

    public void setRbEntrenadorM(JRadioButton rbEntrenadorM) {
        this.rbEntrenadorM = rbEntrenadorM;
    }

    public JRadioButton getRbAsistenteM() {
        return rbAsistenteM;
    }

    public void setRbAsistenteM(JRadioButton rbAsistenteM) {
        this.rbAsistenteM = rbAsistenteM;
    }

    public JRadioButton getRbEntrenador() {
        return rbEntrenador;
    }

    public void setRbEntrenador(JRadioButton rbEntrenador) {
        this.rbEntrenador = rbEntrenador;
    }

    public JRadioButton getRbAsistente() {
        return rbAsistente;
    }

    public void setRbAsistente(JRadioButton rbAsistente) {
        this.rbAsistente = rbAsistente;
    }

    public JComboBox getJbEquipo() {
        return jbEquipo;
    }

    public void setJbEquipo(JComboBox jbEquipo) {
        this.jbEquipo = jbEquipo;
=======
        redondearPanel(panel2,30);
    }

    private void redondearPanel(JPanel panel, int cornerRadius) {
        panel.setOpaque(false);
        panel.setBorder(new redondear(cornerRadius));
    }

    // Clase para crear un borde redondeado
    class redondear implements Border {
        private int radius;

        public redondear(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.black);
            g2d.draw(new RoundRectangle2D.Double(x, y, width -1, height -1, radius, radius));
            g2d.dispose();
        }
>>>>>>> d675d12fa0793e4066258ec58a6bcbeca432a0fc:Programacion/e-sports01/src/Vista/VentanaStaff.java
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
<<<<<<< HEAD:Programacion/union/e-sports01/src/Vista/VentanaStaff.java
    public void addBAceptarEliminar (ActionListener al){
        bAceptarEliminar.addActionListener(al);
    }
    public void addbAceptarNuevo (ActionListener al){
        btAceptar.addActionListener(al);
    }
=======


>>>>>>> d675d12fa0793e4066258ec58a6bcbeca432a0fc:Programacion/e-sports01/src/Vista/VentanaStaff.java
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