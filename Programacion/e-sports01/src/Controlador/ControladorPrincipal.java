package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorBD.ControladorTablaEquipo;
import Controlador.ControladorVista.ControladorVista;
import Vista.VentanaPrincipal;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;
    private VentanaPrincipal vp;
    private ControladorTablaEquipo cte;

    public ControladorPrincipal() {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
        vp = new VentanaPrincipal();


    }

}
