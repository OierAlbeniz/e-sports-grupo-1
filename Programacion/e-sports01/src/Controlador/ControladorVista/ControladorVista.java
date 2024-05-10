package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;
import Vista.VentanaPrincipal;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVLogin cvlogin;
    private ControladorVP cvp;

    public ControladorVista(ControladorPrincipal cp) {
        this.cp = cp;

        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();
        cvp=new ControladorVP(this);
    }

    public void crearMostrarVP() {
        cvp.crearMostrar();
    }
}
