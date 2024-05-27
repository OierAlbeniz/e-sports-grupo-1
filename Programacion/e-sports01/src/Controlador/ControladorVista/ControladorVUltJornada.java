package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Vista.VentanaUltJornada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Controlador para la ventana de visualización de la última jornada de una competición.
 */
public class ControladorVUltJornada {
    private ControladorVista cv;
    private VentanaUltJornada vUltJornada;
    private List<String> listaCompeticiones;
    /**
     * Constructor de la clase.
     *
     * @param cv Controlador principal.
     */
    public ControladorVUltJornada(ControladorVista cv) {
        this.cv = cv;
    }
    /**
     * Crea y muestra la ventana de la última jornada.
     */
    public void crearMostrar() {
        vUltJornada = new VentanaUltJornada();
        vUltJornada.setVisible(true);
        vUltJornada.addInicio(new BInicioAL());

        llenarComboCompeticiones();
        vUltJornada.addCBCompeticion(new BCompeticionAL());

        listaCompeticiones = new ArrayList<>();
        vUltJornada.getCbCompeticiones().setSelectedIndex(-1);
    }
    /**
     * Llena el combo de competiciones con los nombres de las competiciones disponibles.
     */
    public void llenarComboCompeticiones() {
        try {
            listaCompeticiones = cv.buscarCompeticiones();
            listaCompeticiones.forEach(o -> {
                vUltJornada.getCbCompeticiones().addItem(o);
                System.out.println("Competicion añadida al combo: " + o);
            });
        } catch (Exception e) {
            vUltJornada.mostrar(e.getMessage());
        }
    }
    /**
     * ActionListener para el botón de inicio.
     */
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vUltJornada.getCbCompeticiones().removeAllItems();
            cv.crearMostrarVP();
            vUltJornada.dispose();
        }
    }
    /**
     * ActionListener para la selección de competición en el combo box.
     */
    public class BCompeticionAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombreCompeticion = (String) vUltJornada.getCbCompeticiones().getSelectedItem();
                if (nombreCompeticion != null) {
                    System.out.println(nombreCompeticion);
                    Competicion competicion = cv.buscarCompeticion(nombreCompeticion);
                    System.out.println(competicion.getIdCompeticion());
                    mostrarEnfrentamientosUltimaJornada(competicion);
                }
            } catch (Exception ex) {
                vUltJornada.mostrar(ex.getMessage());
            }
        }
    }
    /**
     * Muestra los enfrentamientos de la última jornada de la competición seleccionada.
     *
     * @param competicion Competición seleccionada.
     */
    public void mostrarEnfrentamientosUltimaJornada(Competicion competicion) {
        try {
            System.out.println("Competicion " + competicion.getIdCompeticion());
            Integer competicionID = competicion.getIdCompeticion();
            Integer ultimaJornada = cv.buscarUltimaJornada(competicionID); // Método que retorna la última jornada de la competición
            if (ultimaJornada != null) {
                List<Enfrentamiento> enfrentamientos = cv.buscarEnfrentamientos(ultimaJornada, competicionID);

                vUltJornada.getPanelEnfrentamiento().removeAll();
                vUltJornada.getPanelEnfrentamiento().setLayout(new BoxLayout(vUltJornada.getPanelEnfrentamiento(), BoxLayout.Y_AXIS));

                if (!enfrentamientos.isEmpty()) {
                    for (Enfrentamiento enfrentamiento : enfrentamientos) {
                        JPanel panelEnfrentamiento = crearPanelEnfrentamiento(enfrentamiento);
                        vUltJornada.agregarPanel(panelEnfrentamiento);
                    }
                } else {
                    System.out.println("No se encontraron enfrentamientos para la última jornada.");
                }

                // Volver a validar y repintar el panel principal en la vista
                vUltJornada.actualizarVista();
            } else {
                System.out.println("No se encontró la última jornada para la competición.");
            }
        } catch (Exception ex) {
            vUltJornada.mostrar(ex.getMessage());
        }
    }
    /**
     * Crea un panel para mostrar un enfrentamiento.
     *
     * @param enfrentamiento Enfrentamiento a mostrar.
     * @return JPanel que muestra el enfrentamiento.
     */
    private JPanel crearPanelEnfrentamiento(Enfrentamiento enfrentamiento) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setBackground(new Color(0, 255, 185)); // Establecer el color de fondo

        JLabel equipo1 = new JLabel(enfrentamiento.getEquipoUno().getNombre());
        JLabel equipo2 = new JLabel(enfrentamiento.getEquipoDos().getNombre());
        JTextField resultadoLocal = new JTextField(2);
        JTextField resultadoVisitante = new JTextField(2);
        JLabel guion = new JLabel("-");

        Font font = new Font("Michroma", Font.PLAIN, 22);
        Color colorFuente = new Color(46, 47, 47); // Color de fuente RGB (46, 47, 47)
        equipo1.setFont(font);
        equipo2.setFont(font);
        resultadoLocal.setFont(font);
        resultadoVisitante.setFont(font);
        guion.setFont(font);

        equipo1.setForeground(colorFuente);
        equipo2.setForeground(colorFuente);
        guion.setForeground(colorFuente);

        // Establecer el fondo del textfield
        resultadoLocal.setBackground(new Color(0, 255, 185));
        resultadoVisitante.setBackground(new Color(0, 255, 185));
        resultadoLocal.setForeground(colorFuente);
        resultadoVisitante.setForeground(colorFuente);
        resultadoLocal.setEnabled(true);
        resultadoVisitante.setEnabled(true);

        resultadoLocal.setEditable(false);
        resultadoLocal.setEnabled(false);
        resultadoLocal.setBackground(new Color(46, 47, 47)); // Establecer el color de fondo

        resultadoVisitante.setEditable(false);
        resultadoVisitante.setEnabled(false);
        resultadoVisitante.setBackground(new Color(46, 47, 47)); // Establecer el color de fondo


        panel.add(equipo1);
        panel.add(resultadoLocal);
        panel.add(guion);
        panel.add(resultadoVisitante);
        panel.add(equipo2);

        // Llenar los resultados si ya existen
        cv.llenarResultados(resultadoLocal, resultadoVisitante, enfrentamiento.getIdEnfJor());

        return panel;
    }

}
