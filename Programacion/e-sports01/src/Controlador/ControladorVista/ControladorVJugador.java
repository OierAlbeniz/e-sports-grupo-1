package Controlador.ControladorVista;

import Vista.VentanaJugador;

public class ControladorVJugador {
    private ControladorVista cv;
    private VentanaJugador vj;

    public ControladorVJugador(ControladorVista cv) {
        this.cv = cv;
    }


    public void crearVJugador() {
        vj = new VentanaJugador();
        vj.setVisible(true);


    }
}
