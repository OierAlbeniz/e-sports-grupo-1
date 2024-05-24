package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class VentanaCompeticiones extends JFrame{
    private JRadioButton rbNuevo;
    private JRadioButton rbEliminar;
    private JRadioButton rbEditar;
    private JPanel pNuevo;
    private JTextField tfNombre;
    private JPanel pEditar;
    private JComboBox cbEditCompeticion;
    private JRadioButton rbEditarNombre;
    private JTextField tfNuevoNombre;
    private JRadioButton rbVincular;
    private JRadioButton rbDesvincular;
    private JComboBox cbNuevoEstado;
    private JComboBox cbNuevoJuego;
    private JPanel pEliminar;
    private JComboBox cbCompeticion;
    private JButton bInicio;
    private JButton bVolver;
    private JPanel pCompeticiones;
    private JTextField tfFechaInicio;
    private JTextField tfFechaFin;
    private JComboBox cbJuego;
    private JTextField tfNuevaFechaIni;
    private JTextField tfNuevaFechaFin;
    private JPanel panel2;
    private JButton bAceptarNuevo;
    private JButton bAceptarEliminar;
    private JButton bAceptarEditar;

    public VentanaCompeticiones() {
        setContentPane(pCompeticiones);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        redondearPanel(panel2, 30);
    }

    // Método para establecer las esquinas redondeadas de un JButton
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

    public JPanel getpEditar() {
        return pEditar;
    }

    public void setpEditar(JPanel pEditar) {
        this.pEditar = pEditar;
    }

    public JComboBox getCbEditCompeticion() {
        return cbEditCompeticion;
    }

    public void setCbEditCompeticion(JComboBox cbEditCompeticion) {
        this.cbEditCompeticion = cbEditCompeticion;
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

    public JComboBox getCbNuevoEstado() {
        return cbNuevoEstado;
    }

    public void setCbNuevoEstado(JComboBox cbNuevoEstado) {
        this.cbNuevoEstado = cbNuevoEstado;
    }

    public JComboBox getCbNuevoJuego() {
        return cbNuevoJuego;
    }

    public void setCbNuevoJuego(JComboBox cbNuevoJuego) {
        this.cbNuevoJuego = cbNuevoJuego;
    }

    public JPanel getpEliminar() {
        return pEliminar;
    }

    public void setpEliminar(JPanel pEliminar) {
        this.pEliminar = pEliminar;
    }

    public JComboBox getCbCompeticion() {
        return cbCompeticion;
    }

    public void setCbCompeticion(JComboBox cbCompeticion) {
        this.cbCompeticion = cbCompeticion;
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

    public JPanel getpCompeticiones() {
        return pCompeticiones;
    }

    public void setpCompeticiones(JPanel pCompeticiones) {
        this.pCompeticiones = pCompeticiones;
    }

    public JTextField getTfFechaInicio() {
        return tfFechaInicio;
    }

    public void setTfFechaInicio(JTextField tfFechaInicio) {
        this.tfFechaInicio = tfFechaInicio;
    }

    public JTextField getTfFechaFin() {
        return tfFechaFin;
    }

    public void setTfFechaFin(JTextField tfFechaFin) {
        this.tfFechaFin = tfFechaFin;
    }

    public JComboBox getCbJuego() {
        return cbJuego;
    }

    public void setCbJuego(JComboBox cbJuego) {
        this.cbJuego = cbJuego;
    }

    public JTextField getTfNuevaFechaIni() {
        return tfNuevaFechaIni;
    }

    public void setTfNuevaFechaIni(JTextField tfNuevaFechaIni) {
        this.tfNuevaFechaIni = tfNuevaFechaIni;
    }

    public JTextField getTfNuevaFechaFin() {
        return tfNuevaFechaFin;
    }

    public void setTfNuevaFechaFin(JTextField tfNuevaFechaFin) {
        this.tfNuevaFechaFin = tfNuevaFechaFin;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
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
    public void addbAceptarAl(ActionListener al) {
        bAceptarNuevo.addActionListener(al);
    }
    public void addbAceptarEliminarAl(ActionListener al) {
        bAceptarEliminar.addActionListener(al);
    }
    public void addbAceptarEditarAl(ActionListener al) {
        bAceptarEditar.addActionListener(al);
    }
    public void addCbEditAL(ActionListener al) {
        cbEditCompeticion.addActionListener(al);
    }
    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }



    public void limpiar() {
        tfNombre.setText("");
        tfFechaInicio.setText("");
        tfFechaFin.setText("");
        cbJuego.setSelectedIndex(-1);
        cbCompeticion.setSelectedIndex(-1);
        cbEditCompeticion.setSelectedIndex(-1);
        tfNuevoNombre.setText("");
        tfNuevaFechaIni.setText("");
        tfNuevaFechaFin.setText("");
        cbNuevoEstado.setSelectedIndex(-1);
        cbNuevoJuego.setSelectedIndex(-1);
    }
    public void limpiarEditar() {
        cbNuevoEstado.setSelectedIndex(-1);
        cbNuevoJuego.removeAllItems();
    }
}
