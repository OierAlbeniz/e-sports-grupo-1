package Controlador.ControladorVista;

import Modelo.Equipo;
import Modelo.Jugador;
import Modelo.Usuario;
import Vista.VentanaJugadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorVJugador {

    private ControladorVista cv;
    private Connection con;
    private VentanaJugadores vJugadores;


    public ControladorVJugador(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vJugadores = new VentanaJugadores();
        vJugadores.setVisible(true);
        vJugadores.addVolver(new BVolverAL());
        vJugadores.addInicio(new BInicioAL());
        vJugadores.addrbNuevoAL(new RbNuevoAL());
        vJugadores.addrbEditarAL(new RbEditarAL());
        vJugadores.addrbEliminarAL(new RbEliminarAL());
        vJugadores.getCbEquipoElim().addFocusListener(new ComboEquipoElimFocusListener());
        vJugadores.getCbEquipoNuevo().addFocusListener(new ComboNombreJugadoresEditar());
        vJugadores.limpiar();
        vJugadores.getpNuevo().setVisible(false);
        vJugadores.getpEditar().setVisible(false);
        vJugadores.getpEliminar().setVisible(false);
        llenarComboEquipo();
        llenarComboEquipoEliminar();
        llenarComboEquipoNuevo();
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vJugadores.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vJugadores.dispose();
        }
    }

    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbNuevo().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(true);
                vJugadores.getpEditar().setVisible(false);
                vJugadores.getpEliminar().setVisible(false);
                vJugadores.addAceptar(new addBotonAceptar());
            }



        }
    }
    public class addBotonAceptar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vJugadores.getRbNuevo().isSelected()) {
                    // Lógica para crear un nuevo jugador
                    String nombre = vJugadores.getTfNombre().getText();
                    String primerApellido = vJugadores.getTfApellido1().getText();
                    String segundoApellido = vJugadores.getTfApellido2().getText();
                    Integer sueldo = Integer.valueOf(vJugadores.getTfSueldo().getText());
                    String nacionalidad = String.valueOf(vJugadores.getCbNacionalidad().getSelectedItem());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate fechaNacimiento = LocalDate.parse(vJugadores.getTfFechaNac().getText(), formatter);
                    String nickname = vJugadores.getTfNickname().getText();
                    String rol = String.valueOf(vJugadores.getCbRol().getSelectedItem());
                    String equipo = String.valueOf(vJugadores.getCbEquipoNuevo().getSelectedIndex() + 1);

                    Usuario anadirJugador = cv.crearJugador(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, equipo);
                    JOptionPane.showMessageDialog(vJugadores, "Jugador creado correctamente.");

                } else if (vJugadores.getRbEliminar().isSelected()) {
                    // Lógica para eliminar un jugador
                    String nombre = (String) vJugadores.getCbJugador().getSelectedItem();
                    String equipo = (String) vJugadores.getCbEquipoElim().getSelectedItem();
                    cv.eliminarJugador(nombre, equipo);


                } else if (vJugadores.getRbEditar().isSelected()) {
                    // Lógica para editar un jugador
                    JOptionPane.showMessageDialog(null, "Funcionalidad de edición aún no implementada.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vJugadores, "Error al realizar la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public class ComboEquipoElimFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {
            vJugadores.getCbJugador().removeAllItems(); // Clear existing items
            try {
                String equiposelecionado = (String) vJugadores.getCbEquipoElim().getSelectedItem();
                if (equiposelecionado != null && !equiposelecionado.isEmpty()) {
                    List<Jugador> jugadores = cv.llenarJugadoresNombre(equiposelecionado);
                    jugadores.forEach(jugador -> vJugadores.getCbJugador().addItem(jugador.getNombre()));

                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un equipo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vJugadores, "Error al cargar jugadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
//esto es el focus lost de editar
public class ComboNombreJugadoresEditar implements FocusListener {
    @Override
    public void focusGained(FocusEvent e) {
        // No action on focus gain
    }

    @Override
    public void focusLost(FocusEvent e) {

        try {
            String equiposelecionado = (String) vJugadores.getCbEditJugadores().getSelectedItem();
            if (equiposelecionado != null && !equiposelecionado.isEmpty()) {
                List<Jugador> jugadores = cv.llenarJugadoresNombre(equiposelecionado);
                jugadores.forEach(jugador -> vJugadores.getCbEditJugadores().addItem(jugador.getNombre()));

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un equipo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vJugadores, "Error al cargar jugadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


// In your crearMostrar method or constructor where you setup listeners:

    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbEditar().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(false);
                vJugadores.getpEditar().setVisible(true);
                vJugadores.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vJugadores.getRbEliminar().isSelected()){
                vJugadores.limpiar();
                vJugadores.getpNuevo().setVisible(false);
                vJugadores.getpEditar().setVisible(false);
                vJugadores.getpEliminar().setVisible(true);
            }
        }
    }
    public void llenarComboEquipo(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o-> vJugadores.getCbEquipo().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public void llenarComboEquipoEliminar(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o-> vJugadores.getCbEquipoElim().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public void llenarComboEquipoNuevo(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);

            listaEquipos.forEach(o-> vJugadores.getCbEquipoNuevo().addItem(o.getNombre()));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }




}
