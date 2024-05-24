package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class VentanaJuegos extends JFrame {
    private JRadioButton rbNuevo;
    private JRadioButton rbEliminar;
    private JRadioButton rbEditar;
    private JPanel pNuevo;
    private JTextField tfNombreNuevo;
    private JComboBox cbCompeticion;
    private JPanel pEditar;
    private JComboBox cbEditJuego;
    private JRadioButton rbEditarNombre;
    private JTextField tfEditarNombre;
    private JRadioButton rbVincular;
    private JRadioButton rbDesvincular;
    private JComboBox cbEditarFechaLanz;
    private JComboBox cbDesvincular;
    private JPanel pEliminar;
    private JComboBox cbEliminarJuego;
    private JButton bInicio;
    private JButton bVolver;
    private JPanel pJuegos;
    private JTextField tfEmpresa;
    private JTextField tfFechaLanz;
    private JComboBox cbVincular;
    private JTextField tfNuevaEmpresa;
    private JPanel panel2;

    public VentanaJuegos() {
        setContentPane(pJuegos);
        setLocationRelativeTo(null);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        redondearPanel(panel2, 30);
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
        return tfNombreNuevo;
    }

    public void setTfNombre(JTextField tfNombre) {
        this.tfNombreNuevo = tfNombre;
    }

    public JComboBox getCbCompeticion() {
        return cbCompeticion;
    }

    public void setCbCompeticion(JComboBox cbCompeticion) {
        this.cbCompeticion = cbCompeticion;
    }

    public JPanel getpEditar() {
        return pEditar;
    }

    public void setpEditar(JPanel pEditar) {
        this.pEditar = pEditar;
    }

    public JComboBox getCbEditJuego() {
        return cbEditJuego;
    }

    public void setCbEditJuego(JComboBox cbEditJuego) {
        this.cbEditJuego = cbEditJuego;
    }

    public JRadioButton getRbEditarNombre() {
        return rbEditarNombre;
    }

    public void setRbEditarNombre(JRadioButton rbEditarNombre) {
        this.rbEditarNombre = rbEditarNombre;
    }

    public JTextField getTfNuevoNombre() {
        return tfEditarNombre;
    }

    public void setTfNuevoNombre(JTextField tfNuevoNombre) {
        this.tfEditarNombre = tfNuevoNombre;
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

    public JComboBox getCbVNuevaFechaLanz() {
        return cbEditarFechaLanz;
    }

    public void setCbVNuevaFechaLanz(JComboBox cbVNuevaFechaLanz) {
        this.cbEditarFechaLanz = cbVNuevaFechaLanz;
    }

    public JComboBox getCbDesvincular() {
        return cbDesvincular;
    }

    public void setCbDesvincular(JComboBox cbDesvincular) {
        this.cbDesvincular = cbDesvincular;
    }

    public JPanel getpEliminar() {
        return pEliminar;
    }

    public void setpEliminar(JPanel pEliminar) {
        this.pEliminar = pEliminar;
    }

    public JComboBox getCbJuego() {
        return cbEliminarJuego;
    }

    public void setCbJuego(JComboBox cbJuego) {
        this.cbEliminarJuego = cbJuego;
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

    public JPanel getpJuegos() {
        return pJuegos;
    }

    public void setpJuegos(JPanel pJuegos) {
        this.pJuegos = pJuegos;
    }

    public JTextField getTfEmpresa() {
        return tfEmpresa;
    }

    public void setTfEmpresa(JTextField tfEmpresa) {
        this.tfEmpresa = tfEmpresa;
    }

    public JTextField getTfFechaLanz() {
        return tfFechaLanz;
    }

    public JTextField getTfNuevaEmpresa() {
        return tfNuevaEmpresa;
    }

    public void setTfNuevaEmpresa(JTextField tfNuevaEmpresa) {
        this.tfNuevaEmpresa = tfNuevaEmpresa;
    }

    public void setTfFechaLanz(JTextField tfFechaLanz) {
        this.tfFechaLanz = tfFechaLanz;
    }

    public JComboBox getCbVincular() {
        return cbVincular;
    }

    public void setCbVincular(JComboBox cbVincular) {
        this.cbVincular = cbVincular;
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
        tfNombreNuevo.setText("");
        tfEmpresa.setText("");
        tfFechaLanz.setText("");
        cbCompeticion.setSelectedIndex(-1);
        cbEliminarJuego.setSelectedIndex(-1);
        cbEditJuego.setSelectedIndex(-1);
        tfEditarNombre.setText("");
        tfNuevaEmpresa.setText("");
        cbEditarFechaLanz.setSelectedIndex(-1);
        cbVincular.setSelectedIndex(-1);
        cbDesvincular.setSelectedIndex(-1);
    }
}
