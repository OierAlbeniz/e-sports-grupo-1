package Controlador;

import Vista.InicioSesion.InicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVLogin {

    private InicioSesion vis;

    public  ControladorVLogin(){
        vis.btduda(new btDuda());
        vis.btsalir(new btSalir());
        vis.btIniciarSesion(new btIniarSesion());
    }

    public class btDuda implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Bienvenido a la ayuda \n contacta con el soporte tecnico \n egibide@gmail.com");
        }
    }

    public class btSalir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            vis.dispose();
        }
    }

    public class btIniarSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"hola");
        }
    }


}
