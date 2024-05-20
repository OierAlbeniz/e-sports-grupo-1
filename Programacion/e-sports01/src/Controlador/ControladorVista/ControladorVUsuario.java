package Controlador.ControladorVista;

import Vista.VistaPerfil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVUsuario {
    private VistaPerfil vpr;
    private ControladorVista cv;
    public ControladorVUsuario(ControladorVista cv) {
        this.cv = cv;
    }



    public void crearMostrarUsuario() {
        vpr = new VistaPerfil();
        vpr.setVisible(true);
        vpr.addBCancelar(new botonCancelar());
        vpr.addCrearUsuario(new crearUsuario());
    }

    public class botonCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vpr.dispose();
        }
    }

    public class crearUsuario implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }





}
