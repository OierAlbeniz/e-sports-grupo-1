package Controlador.ControladorVista;

import Vista.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVP {
    private ControladorVista cv;
    private VentanaPrincipal vp;

    public ControladorVP(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
            vp = new VentanaPrincipal();
            vp.setVisible(true);
            vp.addeditar(new BEditarAL());
    }
    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vp.dispose();
        }
    }
}
