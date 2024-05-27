package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Enfrentamiento;
import Vista.VentanaInsertarResultados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Controlador para la inserción de resultados en la interfaz gráfica.
 */
public class ControladorVInsertResultados {
    private ControladorVista cv;
    private VentanaInsertarResultados vInsertResultados;
    private List<String> listaCompeticiones;
    private List<Integer> listaJornadas;
    /**
     * Constructor de ControladorVInsertResultados.
     * @param cv ControladorVista asociado.
     */
    public ControladorVInsertResultados(ControladorVista cv) {
        this.cv = cv;
    }
    /**
     * Método para crear y mostrar la ventana de inserción de resultados.
     */
    public void crearMostrar() {
        vInsertResultados = new VentanaInsertarResultados();
        vInsertResultados.setVisible(true);
        //vInsertResultados.addVolver(new BVolverAL());
        vInsertResultados.addInicio(new BInicioAL());

        llenarComboCompeticiones();
        vInsertResultados.addCBCompeticion(new BCompeticionAL());
        //vInsertResultados.addCBJornada(new BJornadaAL());
        vInsertResultados.addCBJornadaFL(new BJornadaFL());

        listaCompeticiones = new ArrayList<>();
        listaJornadas = new ArrayList<>();
        vInsertResultados.getCbCompeticiones().setSelectedIndex(-1);
    }
    /**
     * Método para llenar el combo de competiciones.
     */
    public void llenarComboCompeticiones() {
        try {
            listaCompeticiones = cv.buscarCompeticiones();
            listaCompeticiones.forEach(o -> {
                vInsertResultados.getCbCompeticiones().addItem(o);
                System.out.println("Competicion añadida al combo: " + o);
            });
        } catch (Exception e) {
            vInsertResultados.mostrar(e.getMessage());
        }
    }

    /**
     * ActionListener para el botón de inicio.
     */

    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vInsertResultados.getCbCompeticiones().removeAll();
            vInsertResultados.getCbJornadas().removeAll();
            cv.crearMostrarVP();
            vInsertResultados.dispose();
        }
    }
    /**
     * ActionListener para seleccionar una competición.
     */
    public class BCompeticionAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                vInsertResultados.getCbJornadas().removeAllItems();
                String nombreCompeticion = (String) vInsertResultados.getCbCompeticiones().getSelectedItem();
                if (nombreCompeticion != null) {
                    System.out.println(nombreCompeticion);
                    Competicion competicion = cv.buscarCompeticion(nombreCompeticion);
                    System.out.println(competicion.getIdCompeticion());
                    llenarComboJornadas(competicion);
                }
            } catch (Exception ex) {
                vInsertResultados.mostrar(ex.getMessage());
            }
        }
    }
    /**
     * Método para llenar el combo de jornadas.
     * @param competicion Competición seleccionada.
     */
    public void llenarComboJornadas(Competicion competicion) {
        try {
            System.out.println("Competicion " + competicion.getIdCompeticion());
            Integer competicionID = competicion.getIdCompeticion();
            listaJornadas = cv.buscarJornadas(competicionID);

            if (!listaJornadas.isEmpty()) {
                listaJornadas.forEach(o -> {
                    vInsertResultados.getCbJornadas().addItem(o);
                    System.out.println("Jornada añadida al combo: " + o);
                });
            } else {
                System.out.println("No se encontraron jornadas para la competición.");
            }
            vInsertResultados.getCbJornadas().setSelectedIndex(-1);
        } catch (Exception ex) {
            vInsertResultados.mostrar(ex.getMessage());
        }
    }

    /**
     * FocusListener para el combo de jornadas.
     */
public class BJornadaFL implements FocusListener {

    @Override
    public void focusGained(FocusEvent e) {
        // No hacemos nada cuando el foco se gana
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            Integer idJornada = (Integer) vInsertResultados.getCbJornadas().getSelectedItem();
            System.out.println(idJornada + " Jornadaa");
            if (idJornada != null) {
                Competicion competicion = cv.buscarCompeticion((String) vInsertResultados.getCbCompeticiones().getSelectedItem());
                System.out.println(idJornada + competicion.getNombre() + " Jornada y comp");
                Integer idCompeticion = competicion.getIdCompeticion();
                List<Enfrentamiento> enfrentamientos = cv.buscarEnfrentamientos(idJornada, idCompeticion);

                vInsertResultados.getPanelEnfrentamiento().removeAll();
                vInsertResultados.getPanelEnfrentamiento().setLayout(new BoxLayout(vInsertResultados.getPanelEnfrentamiento(), BoxLayout.Y_AXIS));

                if (!enfrentamientos.isEmpty()) {
                    for (Enfrentamiento enfrentamiento : enfrentamientos) {
                        JPanel panelEnfrentamiento = crearPanelEnfrentamiento(enfrentamiento);
                        vInsertResultados.agregarPanel(panelEnfrentamiento);
                    }
                }



                // Volver a validar y repintar el panel principal en la vista
                vInsertResultados.actualizarVista();
            }
        } catch (Exception ex) {
            vInsertResultados.mostrar(ex.getMessage());
        }
    }
}


    /**
     * Método para crear un panel de enfrentamiento.
     * @param enfrentamiento Enfrentamiento.
     * @return Panel creado.
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

        resultadoLocal.setEditable(true);
        resultadoVisitante.setEditable(true);

        panel.add(equipo1);
        panel.add(resultadoLocal);
        panel.add(guion);
        panel.add(resultadoVisitante);
        panel.add(equipo2);

        // Botón Aceptar
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.setBackground(new Color(46, 47, 47)); // Fondo del botón
        botonAceptar.setForeground(new Color(0, 255, 185)); // Color de la fuente
        botonAceptar.setFont(new Font("Michroma", Font.PLAIN, 12)); // Fuente

        botonAceptar.addActionListener(e -> {
            try {
                int resLocal = Integer.parseInt(resultadoLocal.getText());
                int resVisitante = Integer.parseInt(resultadoVisitante.getText());
                cv.guardarResultados(enfrentamiento.getIdEnfJor(), resLocal, resVisitante);
                cv.actualizarTablaClasificacion(enfrentamiento, resLocal, resVisitante);
            } catch (Exception ex) {
                vInsertResultados.mostrar(ex.getMessage());
            }
        });

        panel.add(botonAceptar);

        // Llenar los resultados si ya existen
        cv.llenarResultados(resultadoLocal, resultadoVisitante, enfrentamiento.getIdEnfJor());


        return panel;
    }

    /**
     * ActionListener para el botón de aceptar resultados en la ventana de inserción de resultados.
     */
    public class BAceptarAL implements ActionListener {
        private Enfrentamiento enfrentamiento;
        private JTextField resultadoLocal;
        private JTextField resultadoVisitante;
        /**
         * Constructor de la clase BAceptarAL.
         *
         * @param enfrentamiento     El enfrentamiento para el que se ingresan los resultados.
         * @param resultadoLocal     El campo de texto para el resultado local.
         * @param resultadoVisitante El campo de texto para el resultado visitante.

         */
        public BAceptarAL(Enfrentamiento enfrentamiento, JTextField resultadoLocal, JTextField resultadoVisitante) {
            this.enfrentamiento = enfrentamiento;
            this.resultadoLocal = resultadoLocal;
            this.resultadoVisitante = resultadoVisitante;
        }
        /**
         * Acción realizada al hacer clic en el botón de aceptar.
         *
         * @param e El evento de acción.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Guardar resultados en la tabla de enfrentamiento
                int resultLocal = Integer.parseInt(resultadoLocal.getText());
                int resultVisitante = Integer.parseInt(resultadoVisitante.getText());
                cv.guardarResultados(enfrentamiento.getIdEnfJor(), resultLocal, resultVisitante);

                // Actualizar la tabla de clasificación
                cv.actualizarTablaClasificacion(enfrentamiento, resultLocal, resultVisitante);
            } catch (NumberFormatException ex) {
                // Manejar error si no se ingresan números válidos
                vInsertResultados.mostrar("Por favor, ingrese resultados válidos.");
            } catch (Exception ex) {
                vInsertResultados.mostrar(ex.getMessage());
            }
        }
    }
}