package Controlador.ControladorVista;

import Vista.VentanaEditar;
import Vista.VentanaEquipo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVEquipo {
        private ControladorVEditar ved;
        private VentanaEquipo veq;
        private ControladorVista cv;



    public ControladorVEquipo(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrarEquipo() {
        veq = new VentanaEquipo();
        veq.setVisible(true);

    }
    public class VistaEquipo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Le dice al controlador de vista que la operación elegida es alta
            ved.crearVentanaEquipo();
        }
    }

}
