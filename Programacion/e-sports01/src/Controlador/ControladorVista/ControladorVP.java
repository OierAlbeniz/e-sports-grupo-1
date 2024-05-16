package Controlador.ControladorVista;

import Modelo.Usuario;
import Vista.VentanaEditar;
import Vista.VentanaInicioSesion;
import Vista.VentanaPrincipal;
import Vista.VistaPerfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVP {
    private ControladorVista cv;
    private VentanaPrincipal vp;
    private ControladorVLogin cvl;
    private VistaPerfil vper;
    private VentanaInicioSesion vsesion;

    public ControladorVP(ControladorVista cv) throws Exception {
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
