package Controlador.ControladorVista;

import Vista.VentanaEditar;
import Vista.VentanaEquipo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVEquipo {
        private VentanaEditar ved;

        public ControladorVEquipo(){
            ved = new VentanaEditar();
            ved.addJugadores(new ventanaEquipo());
        }

        public class ventanaEquipo implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                ved.setVisible(true);
            }
        }


}
