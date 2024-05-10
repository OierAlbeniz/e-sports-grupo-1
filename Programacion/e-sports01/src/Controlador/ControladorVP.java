package Controlador;
import Vista.InicioSesion.InicioSesion;
import Vista.VentanaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class ControladorVP {
        private InicioSesion vis;

        public ControladorVP() {
            vis = new InicioSesion();
            vis.setVisible(false);

        }


    }

