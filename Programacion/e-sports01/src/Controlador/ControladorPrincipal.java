package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;
import Modelo.Usuario;

public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;

    public ControladorPrincipal() {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
    }
    public Usuario buscarUsuario(String user) throws Exception {
        return cb.buscarUsuario(user);
    }

}
