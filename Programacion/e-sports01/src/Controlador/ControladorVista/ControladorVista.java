package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVP cvp;

    public ControladorVista(ControladorPrincipal cp) {
        this.cp = cp;

        cvp = new ControladorVP(this);
        cvp.crearMostrar();
    }
}
