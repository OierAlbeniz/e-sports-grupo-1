package Controlador.ControladorVista;

import Vista.VentanaJugadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVJugador {

    private ControladorVista cv;
    private Connection con;
    private VentanaJugadores vJugadores;


    public ControladorVJugador(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vJugadores = new VentanaJugadores();
        vJugadores.setVisible(true);
        vJugadores.addVolver(new BVolverAL());
        vJugadores.addInicio(new BInicioAL());

        vJugadores.addrbNuevoAL(new RbNuevoAL());
        vJugadores.addrbEditarAL(new RbEditarAL());
        vJugadores.addrbEliminarAL(new RbEliminarAL());
        vJugadores.limpiar();
        vJugadores.getpNuevo().setVisible(false);
        vJugadores.getpEditar().setVisible(false);
        vJugadores.getpEliminar().setVisible(false);
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vJugadores.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vJugadores.dispose();
        }
    }

    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbNuevo().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(true);
                vJugadores.getpEditar().setVisible(false);
                vJugadores.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbEditar().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(false);
                vJugadores.getpEditar().setVisible(true);
                vJugadores.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbEliminar().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(false);
                vJugadores.getpEditar().setVisible(false);
                vJugadores.getpEliminar().setVisible(true);
            }
        }
    }
}
