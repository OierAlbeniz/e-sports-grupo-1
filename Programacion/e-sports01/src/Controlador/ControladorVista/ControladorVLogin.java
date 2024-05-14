package Controlador.ControladorVista;

import Vista.VentanaInicioSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVLogin {
    private ControladorVista cv;
    private VentanaInicioSesion vsesion;
    private ControladorVP cvp;

    public ControladorVLogin(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vsesion = new VentanaInicioSesion();
        vsesion.setVisible(true);
        vsesion.addSesion(new BSesionAL());
    }
    public class BSesionAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Le dice al controlador de vista que la operación elegida es alta
            vsesion.dispose();
            cv.crearMostrarVP();
        }
    }
}
