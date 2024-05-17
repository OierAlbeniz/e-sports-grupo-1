package Controlador.ControladorVista;

import Vista.VentanaPatrocinadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVPatrocinador {

    private ControladorVista cv;
    private Connection con;
    private VentanaPatrocinadores vPatrocinadores;


    public ControladorVPatrocinador(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vPatrocinadores = new VentanaPatrocinadores();
        vPatrocinadores.setVisible(true);
        vPatrocinadores.addVolver(new BVolverAL());
        vPatrocinadores.addInicio(new BInicioAL());

        vPatrocinadores.addrbNuevoAL(new RbNuevoAL());
        vPatrocinadores.addrbEditarAL(new RbEditarAL());
        vPatrocinadores.addrbEliminarAL(new RbEliminarAL());
        vPatrocinadores.limpiar();
        vPatrocinadores.getpNuevo().setVisible(false);
        vPatrocinadores.getpEditar().setVisible(false);
        vPatrocinadores.getpEliminar().setVisible(false);
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vPatrocinadores.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vPatrocinadores.dispose();
        }
    }
    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbNuevo().isSelected()){
                vPatrocinadores.limpiar();
                vPatrocinadores.getpNuevo().setVisible(true);
                vPatrocinadores.getpEditar().setVisible(false);
                vPatrocinadores.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbEditar().isSelected()){
                vPatrocinadores.limpiar();
                vPatrocinadores.getpNuevo().setVisible(false);
                vPatrocinadores.getpEditar().setVisible(true);
                vPatrocinadores.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbEliminar().isSelected()){
                vPatrocinadores.limpiar();
                vPatrocinadores.getpNuevo().setVisible(false);
                vPatrocinadores.getpEditar().setVisible(false);
                vPatrocinadores.getpEliminar().setVisible(true);
            }
        }
    }
}
