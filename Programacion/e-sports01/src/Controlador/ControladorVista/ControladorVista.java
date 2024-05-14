package Controlador.ControladorVista;

import Controlador.ControladorBD.ControladorTablaJugador;
import Controlador.ControladorPrincipal;
import Vista.VentanaPrincipal;

public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVEquipo cveq;
    private ControladorVLogin cvlogin;
    private ControladorVEditar cveditar;
    private ControladorVJugador cvjugador;
    private ControladorVP cvp;

    public ControladorVista(ControladorPrincipal cp) {
        this.cp = cp;
        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();
        cveditar=new ControladorVEditar(this);
        cvp=new ControladorVP(this);
        cveq=new ControladorVEquipo(this);
        cvjugador = new ControladorVJugador(this);

    }

    public void crearMostrarVP() {
        cvp.crearMostrar();
    }
    public void crearMostrarEditar() {
        cveditar.crearMostrar();
    }

<<<<<<< HEAD
=======
        cveq.crearMostrarVentanaEquipo();

    }
    public void crearVentanaJugador() {
        cvjugador.crearVJugador();
    }


>>>>>>> ca39ce7593be06b9bf622155171cb29f54b9049a


}
