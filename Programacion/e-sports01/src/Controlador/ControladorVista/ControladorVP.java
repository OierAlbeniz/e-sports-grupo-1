package Controlador.ControladorVista;

import Vista.VentanaPrincipal;

public class ControladorVP {
    private ControladorVista cv;
    private VentanaPrincipal vp;

    public ControladorVP(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vp = new VentanaPrincipal();
        vp.setVisible(true);
    }
}
