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
import java.text.DecimalFormat;
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
        vJugadores.getCbEquipoEditar().addFocusListener(new ComboNombreJugadoresEditar());
        vJugadores.getCbEditJugadores().addFocusListener(new ComboEditJugadoresFocusListener());
        vJugadores.limpiar();
        vJugadores.getpNuevo().setVisible(false);
        vJugadores.getpEditar().setVisible(false);
        vJugadores.getpEliminar().setVisible(false);
        llenarComboEquipo();
        llenarComboEquipoEliminar();
        llenarComboEquipoNuevo();
        llenarComboEquipoEditar();
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

                }
                if (vJugadores.getRbEliminar().isSelected()) {
                    // Lógica para eliminar un jugador
                    String nombre = (String) vJugadores.getCbJugador().getSelectedItem();
                    String equipo = (String) vJugadores.getCbEquipoElim().getSelectedItem();
                    cv.eliminarJugador(nombre, equipo);


                }
                if (vJugadores.getRbEditar().isSelected())
                {

                    String nombre = vJugadores.getTfNuevoNombre().getText();
                    String primerApellido = vJugadores.getTfNuevoApellido1().getText();
                    String segundoApellido = vJugadores.getTfNuevoApellido2().getText();
                    Integer sueldo = Integer.valueOf(vJugadores.getTfNuevoSueldo().getText());
                    String nacionalidad = String.valueOf(vJugadores.getCbNacionalidad().getSelectedItem());
                    LocalDate fechaNacimiento = LocalDate.parse(vJugadores.getTfNuevaFechaNac().getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    String nickname = vJugadores.getTfNuevoNick().getText();
                    String rol = String.valueOf(vJugadores.getCbNuevoRol().getSelectedItem());
                    String nuevoEquipo = String.valueOf(vJugadores.getCbNuevoEquipo().getSelectedItem());
                    String nombreAntiguo = (String) vJugadores.getCbEditJugadores().getSelectedItem();
                    String equipoAntiguo = (String) vJugadores.getCbEquipoEditar().getSelectedItem();
                    cv.editarJugadorConfir( nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, nuevoEquipo,nombreAntiguo,equipoAntiguo);
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
                vJugadores.getCbEditJugadores().removeAllItems();
                String equiposelecionado = (String) vJugadores.getCbEquipoEditar().getSelectedItem();
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
    public void llenarComboEquipoEditar(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);

            listaEquipos.forEach(o-> vJugadores.getCbNuevoEquipo().addItem(o.getNombre()));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



    //aqui se buscan todos los datos de ese jugador
    public class ComboEditJugadoresFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {

            try {

                // Obtener el jugador seleccionado en la JComboBox
                String nombreJugador = (String) vJugadores.getCbEditJugadores().getSelectedItem();

                // Verificar si se seleccionó un jugador válido
                if (!nombreJugador.isEmpty()) {
                    // Obtener los datos de los campos de la ventana
                    String equipo = (String) vJugadores.getCbEquipoEditar().getSelectedItem();

                    // Enviar los datos al controlador de base de datos
                    Jugador buscarDatos= cv.actualizarJugador(nombreJugador,equipo);



                    vJugadores.getTfNuevoNombre().setText(buscarDatos.getNombre());
                    vJugadores.getTfNuevoApellido1().setText(buscarDatos.getApellido1());
                    vJugadores.getTfNuevoApellido2().setText(buscarDatos.getApellido2());
                    DecimalFormat formatter2 = new DecimalFormat("#");
                    formatter2.setGroupingUsed(false); // Desactiva el uso de separadores de grupo (comas)
                    String sueldoFormateado = formatter2.format(buscarDatos.getSueldo());
                    vJugadores.getTfNuevoSueldo().setText(sueldoFormateado);                    vJugadores.getCbNuevaNacionalidad().addItem(buscarDatos.getNacionalidad());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
                    String fechaFormateada = buscarDatos.getFechaNacimiento().format(formatter);
                    vJugadores.getTfNuevaFechaNac().setText(fechaFormateada);                    vJugadores.getTfNuevoNick().setText(buscarDatos.getNickname());
                    vJugadores.getCbNuevoRol().addItem(buscarDatos.getRol());

                    vJugadores.getCbNuevoEquipo().setSelectedItem(vJugadores.getCbEquipoEditar().getSelectedItem());


                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un jugador válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vJugadores, "Error al actualizar el jugador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}