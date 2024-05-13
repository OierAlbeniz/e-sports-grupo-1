package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;

    public ControladorPrincipal() {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
    }
}
