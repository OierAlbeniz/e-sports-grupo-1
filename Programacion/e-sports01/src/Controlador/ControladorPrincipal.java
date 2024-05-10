package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVLogin;
import Controlador.ControladorVista.ControladorVista;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;
    private ControladorVLogin vis;

    public ControladorPrincipal() {

        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
        vis = new ControladorVLogin();

    }


}
