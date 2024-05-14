package Controlador.ControladorVista;

import Controlador.ControladorBD.ControladorTablaEquipo;
import Vista.VentanaEditar;
import Vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVEditar {
    private ControladorVista cv;
   private ControladorVEquipo ceq;
    private Connection con;
    private VentanaEditar vEditar;

    public ControladorVEditar(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vEditar = new VentanaEditar();
        vEditar.setVisible(true);

    }

    public void crearVentanaEquipo() {
        ceq.crearMostrarEquipo();

    }

    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Le dice al controlador de vista que la operación elegida es alta
            cv.crearMostrarEditar();
        }
    }
}
