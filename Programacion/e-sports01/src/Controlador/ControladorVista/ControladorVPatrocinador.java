package Controlador.ControladorVista;

import Modelo.Equipo;
import Modelo.Jugador;
import Modelo.Patrocinador;
import Vista.VentanaPatrocinadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;

public class ControladorVPatrocinador {

    private ControladorVista cv;
    private VentanaPatrocinadores vPatrocinadores;

    public ControladorVPatrocinador(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vPatrocinadores = new VentanaPatrocinadores();
        vPatrocinadores.setVisible(true);
        vPatrocinadores.addVolver(new BVolverAL());
        vPatrocinadores.addInicio(new BInicioAL());

        vPatrocinadores.getCbDesvincular().addFocusListener(new ComboPatrocinadorElimFocusListener());
        vPatrocinadores.getCbVincular().addFocusListener(new ComboVincularFocusListener());
        vPatrocinadores.getCbEDPatrocinadores().addFocusListener(new ComboEditPatrocinadoresFocusListener());

        vPatrocinadores.addrbNuevoAL(new RbNuevoAL());
        vPatrocinadores.addrbEditarAL(new RbEditarAL());
        vPatrocinadores.addrbEliminarAL(new RbEliminarAL());
        vPatrocinadores.limpiar();
        vPatrocinadores.getpNuevo().setVisible(false);
        vPatrocinadores.getpEditar().setVisible(false);
        vPatrocinadores.getpEliminar().setVisible(false);

        llenarComboEquipo();
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

    public class AceptarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vPatrocinadores.getRbNuevo().isSelected()) {
                    // Lógica para crear un nuevo patrocinador
                    String nombre = vPatrocinadores.getTfNombre().getText();
                    String equipo = (String) vPatrocinadores.getCbVincular().getSelectedItem();
                    cv.crearPatrocinador(null, nombre);

                } else if (vPatrocinadores.getRbEliminar().isSelected()) {
                    // Lógica para eliminar un patrocinador
                    String nombre = (String) vPatrocinadores.getCbEPatrocinador().getSelectedItem();
                    String equipo = (String) vPatrocinadores.getCbDesvincular().getSelectedItem();
                    cv.eliminarPatrocinador(nombre, equipo);

                } else if (vPatrocinadores.getRbEditar().isSelected()) {
                    // Lógica para editar un patrocinador
                    String nombreNuevo = vPatrocinadores.getTfNuevoNombre().getText();
                    String nuevoEquipo = (String) vPatrocinadores.getCbVincular().getSelectedItem();
                    String nombreAntiguo = (String) vPatrocinadores.getCbEPatrocinador().getSelectedItem();
                    String equipoAntiguo = (String) vPatrocinadores.getCbEDPatrocinadores().getSelectedItem();
                    cv.editarPatrocinadorConfir(nombreNuevo, nuevoEquipo, nombreAntiguo, equipoAntiguo);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPatrocinadores, "Error al realizar la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ComboPatrocinadorElimFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                String equiposeleccionado = (String) vPatrocinadores.getCbDesvincular().getSelectedItem();
                if (equiposeleccionado != null && !equiposeleccionado.isEmpty()) {
                    vPatrocinadores.getCbEPatrocinador().removeAllItems();
                    List<Patrocinador> patrocinadores = cv.llenarPatrocinadorNombre(equiposeleccionado);
                    patrocinadores.forEach(patrocinador -> vPatrocinadores.getCbEPatrocinador().addItem(patrocinador.getNombre()));
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un equipo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPatrocinadores, "Error al cargar patrocinadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ComboVincularFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                String equiposeleccionado = (String) vPatrocinadores.getCbVincular().getSelectedItem();
                if (equiposeleccionado != null && !equiposeleccionado.isEmpty()) {
                    // Actualizar lógica para vincular
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un equipo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPatrocinadores, "Error al cargar datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ComboNombrePatrocinadorEditar implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {

            try {
                vPatrocinadores.getCbEDPatrocinadores().removeAllItems();
                String equiposelecionado = (String) vPatrocinadores.getCbEDPatrocinadores().getSelectedItem();
                if (equiposelecionado != null && !equiposelecionado.isEmpty()) {
                    List<Patrocinador> patrocinadores = cv.llenarPatrocinadorNombre(equiposelecionado);
                    patrocinadores.forEach(patrocinador -> vPatrocinadores.getCbEDPatrocinadores().addItem(patrocinador.getNombre());

                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un equipo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPatrocinadores, "Error al cargar jugadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void llenarComboEquipo() {
        try {
            List<Equipo> listaEquipos = cv.selectEquipo(null);
            listaEquipos.forEach(equipo -> {
                vPatrocinadores.getCbEquipos().addItem(equipo.getNombre());
                vPatrocinadores.getCbDesvincular().addItem(equipo.getNombre());
                vPatrocinadores.getCbVincular().addItem(equipo.getNombre());
                vPatrocinadores.getCbEDPatrocinadores().addItem(equipo.getNombre());
            });
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar combo de equipos", ex);
        }
    }
}
