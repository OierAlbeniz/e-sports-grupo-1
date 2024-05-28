package Controlador.ControladorVista;
import Vista.VistaPerfil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controlador para la vista de perfil.
 */
public class ControladorPerfil {
    /**
     * Constructor de la clase ControladorPerfil.
     */
private Perfil p;
    /**
     * Constructor de la clase ControladorPerfil.
     */
    public ControladorPerfil() {
        p = new Perfil();
        
    }
    /**
     * Clase interna que maneja los eventos de la vista del perfil.
     */


    public class Perfil implements ActionListener {
        /**
         * Maneja los eventos de acción en la vista del perfil.
         *
         * @param e El evento de acción.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("holaaa");            }
    }
}
