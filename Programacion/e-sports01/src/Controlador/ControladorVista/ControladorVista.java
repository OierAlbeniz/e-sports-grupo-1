package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;
import Vista.VentanaPrincipal;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVEquipo cveq;
    private ControladorVLogin cvlogin;
    private ControladorVEditar cveditar;
    private ControladorVP cvp;

    public ControladorVista(ControladorPrincipal cp) {
        this.cp = cp;
        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();
        cvp=new ControladorVP(this);
        cveq=new ControladorVEquipo(this);
    }

    public void crearMostrarVP() {
        cvp.crearMostrar();
    }
    public void crearMostrarEditar() {
        cveditar=new ControladorVEditar(this);
        cveditar.crearMostrar();

    }
    public void crearVentanaEquipo() {

        cveq.crearMostrarVentanaEquipo();

    }


}
