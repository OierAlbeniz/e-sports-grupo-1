package Controlador.ControladorVista;

import Controlador.ControladorBD.ControladorTablaEquipo;
import Vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVP {
    private ControladorVista cv;
    private ControladorTablaEquipo ctequipo;
    private Connection con;
    private VentanaPrincipal vp;

    public ControladorVP(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        //try {
            //ctequipo=new ControladorTablaEquipo(con);
            vp = new VentanaPrincipal();
            vp.setVisible(true);
            vp.addeditar(new BEditarAL());
            /*String nombre = ctequipo.rellenarNombre();
            vp.getTfRelleno().setText(nombre);
        }catch(Exception e){
            vp.mostrarMensaje(e.getMessage());
        }
         */
    }
    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vp.dispose();
        }
    }
}
