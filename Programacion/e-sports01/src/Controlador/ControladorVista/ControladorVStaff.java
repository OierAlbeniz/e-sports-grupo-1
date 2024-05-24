package Controlador.ControladorVista;

import Vista.VentanaStaff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVStaff {

    private ControladorVista cv;
    private Connection con;
    private VentanaStaff vStaff;


    public ControladorVStaff(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vStaff = new VentanaStaff();
        vStaff.setVisible(true);
        vStaff.addVolver(new BVolverAL());
        vStaff.addInicio(new BInicioAL());

        vStaff.addrbNuevoAL(new RbNuevoAL());
        vStaff.addrbEditarAL(new RbEditarAL());
        vStaff.addrbEliminarAL(new RbEliminarAL());
        vStaff.limpiar();
        vStaff.getpNuevo().setVisible(false);
        vStaff.getpEditar().setVisible(false);
        vStaff.getpEliminar().setVisible(false);
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vStaff.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vStaff.dispose();
        }
    }
    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vStaff.getRbNuevo().isSelected()){
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(true);
                vStaff.getpEditar().setVisible(false);
                vStaff.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vStaff.getRbEditar().isSelected()){
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(false);
                vStaff.getpEditar().setVisible(true);
                vStaff.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vStaff.getRbEliminar().isSelected()){
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(false);
                vStaff.getpEditar().setVisible(false);
                vStaff.getpEliminar().setVisible(true);
            }
        }
    }
}
