package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class VentanaClasificacion extends JFrame{
    private JComboBox cbbClasificacion;
    private JTextArea txaClasificacion;
    private JPanel pPrincipal;
    private JPanel panel2;
    private JButton bInicio;
    private JComboBox cbCompeticion;
    private JPanel panelClasificacion;

    public VentanaClasificacion() {
        setContentPane(pPrincipal);
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

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JComboBox getCbCompeticion() {
        return cbCompeticion;
    }

    public void setCbCompeticion(JComboBox cbCompeticion) {
        this.cbCompeticion = cbCompeticion;
    }

    public JPanel getPanelClasificacion() {
        return panelClasificacion;
    }

    public void setPanelClasificacion(JPanel panelClasificacion) {
        this.panelClasificacion = panelClasificacion;
    }

    public void setpPrincipal(JPanel pPrincipal) {
        this.pPrincipal = pPrincipal;
    }
    public void addInicio(ActionListener al) {
        bInicio.addActionListener(al);
    }
    public void addCBCompeticion(ActionListener al) {
        cbCompeticion.addActionListener(al);
    }
    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }
}
