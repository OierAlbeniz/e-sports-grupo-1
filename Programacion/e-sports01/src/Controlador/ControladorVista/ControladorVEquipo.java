package Controlador.ControladorVista;

import Vista.VentanaEquipos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVEquipo {

    private ControladorVista cv;
    private Connection con;
    private VentanaEquipos vEquipos;


    public ControladorVEquipo(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vEquipos = new VentanaEquipos();
        vEquipos.setVisible(true);
        vEquipos.addVolver(new BVolverAL());
        vEquipos.addInicio(new BInicioAL());

        vEquipos.addrbNuevoAL(new RbNuevoAL());
        vEquipos.addrbEditarAL(new RbEditarAL());
        vEquipos.addrbEliminarAL(new RbEliminarAL());
        vEquipos.limpiar();
        vEquipos.getpNuevo().setVisible(false);
        vEquipos.getpEditar().setVisible(false);
        vEquipos.getpEliminar().setVisible(false);
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vEquipos.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vEquipos.dispose();
        }
    }
    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbNuevo().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(true);
                vEquipos.getpEditar().setVisible(false);
                vEquipos.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbEditar().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(false);
                vEquipos.getpEditar().setVisible(true);
                vEquipos.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbEliminar().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(false);
                vEquipos.getpEditar().setVisible(false);
                vEquipos.getpEliminar().setVisible(true);
            }
        }
    }
}
