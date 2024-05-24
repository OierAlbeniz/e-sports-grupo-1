package Controlador.ControladorVista;

import Vista.VentanaJuegos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVJuego {

    private ControladorVista cv;
    private Connection con;
    private VentanaJuegos vJuegos;


    public ControladorVJuego(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vJuegos = new VentanaJuegos();
        vJuegos.setVisible(true);
        vJuegos.addVolver(new BVolverAL());
        vJuegos.addInicio(new BInicioAL());

        vJuegos.addrbNuevoAL(new RbNuevoAL());
        vJuegos.addrbEditarAL(new RbEditarAL());
        vJuegos.addrbEliminarAL(new RbEliminarAL());
        vJuegos.limpiar();
        vJuegos.getpNuevo().setVisible(false);
        vJuegos.getpEditar().setVisible(false);
        vJuegos.getpEliminar().setVisible(false);
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vJuegos.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vJuegos.dispose();
        }
    }
    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJuegos.getRbNuevo().isSelected()){
                vJuegos.limpiar();
                vJuegos.getpNuevo().setVisible(true);
                vJuegos.getpEditar().setVisible(false);
                vJuegos.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJuegos.getRbEditar().isSelected()){
                vJuegos.limpiar();
                vJuegos.getpNuevo().setVisible(false);
                vJuegos.getpEditar().setVisible(true);
                vJuegos.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJuegos.getRbEliminar().isSelected()){
                vJuegos.limpiar();
                vJuegos.getpNuevo().setVisible(false);
                vJuegos.getpEditar().setVisible(false);
                vJuegos.getpEliminar().setVisible(true);
            }
        }
    }
}
