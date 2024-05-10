package Controlador.ControladorVista;

import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVP {
    private ControladorVista cv;
    private VentanaPrincipal vp;

    public ControladorVP(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vp = new VentanaPrincipal();
        vp.setVisible(true);
        vp.addeditar(new BEditarAL());
    }
    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Le dice al controlador de vista que la operación elegida es alta
            cv.crearMostrarEditar();
        }
    }
}
