package Controlador.ControladorVista;

import Modelo.Asistente;
import Modelo.Equipo;
import Vista.VentanaStaff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.util.ArrayList;
/**
 * Controlador para la gestión de la ventana de staff.
 */
public class ControladorVStaff {

    private ControladorVista cv;
    private Connection con;
    private VentanaStaff vStaff;


    public ControladorVStaff(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vStaff = new VentanaStaff();
        vStaff.setVisible(true);
        vStaff.addVolver(new BVolverAL());
        vStaff.addInicio(new BInicioAL());
        vStaff.getJbEquipo().addFocusListener(new ComboEquipoElimFocusListener());
        vStaff.getJbEquipo().addFocusListener(new ComboEquipoEditarFocusListener());
        // vStaff.getJbEquipo().addFocusListener(new ComboEquipoEditarCompletarFocusListener());
        vStaff.getCbEditStaff().addFocusListener(new ComboEquipoEditarLlenarFocusListener());
        vStaff.addrbNuevoAL(new RbNuevoAL());
        vStaff.addrbEditarAL(new RbEditarAL());
        vStaff.addrbEliminarAL(new RbEliminarAL());
        vStaff.addBAceptarEliminar(new bAceptarEliminarAL());
        vStaff.limpiar();
        vStaff.addbAceptarNuevo(new btAceptarNuevoAL());
        vStaff.getpNuevo().setVisible(false);
        vStaff.getpEditar().setVisible(false);
        vStaff.getpEliminar().setVisible(false);
        llenarComboEquipo();
        llenarComboEquipoNuevo();

    }
    /**
     * ActionListener para la acción de volver atrás.
     */
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vStaff.dispose();
        }
    }
    /**
     * ActionListener para la acción de aceptar un nuevo asistente.
     */
    public class btAceptarNuevoAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String nombre = vStaff.getTfNombre().getText();
            String apellido1 = vStaff.getTfApellido1().getText();
            String apellido2 = vStaff.getTfApellido2().getText();
            Integer sueldo = Integer.valueOf(vStaff.getTfSueldo().getText());

            if (vStaff.getRbAsistente().isSelected()) {
                String tipo = vStaff.getRbAsistente().getText();
                try {
                    cv.crearAsistente(nombre, apellido1, apellido2, sueldo, tipo);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else if (vStaff.getRbEntrenador().isSelected()) {
                String tipo = vStaff.getRbEntrenador().getText();
                try {
                    cv.crearEntrenador(nombre, apellido1, apellido2, sueldo, tipo);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
    }
    /**
     * ActionListener para la acción de volver al inicio.
     */
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vStaff.dispose();
        }
    }
    /**
     * ActionListener para la acción de seleccionar la opción de nuevo asistente.
     */
    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vStaff.getRbNuevo().isSelected()) {
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(true);
                vStaff.getpEditar().setVisible(false);
                vStaff.getpEliminar().setVisible(false);
            }
        }
    }
    /**
     * ActionListener para la acción de seleccionar la opción de editar asistente.
     */
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vStaff.getRbEditar().isSelected()) {
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(false);
                vStaff.getpEditar().setVisible(true);
                vStaff.getpEliminar().setVisible(false);
            }
        }
    }
    /**
     * ActionListener para la acción de seleccionar la opción de eliminar asistente.
     */
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vStaff.getRbEliminar().isSelected()) {
                vStaff.limpiar();
                vStaff.getpNuevo().setVisible(false);
                vStaff.getpEditar().setVisible(false);
                vStaff.getpEliminar().setVisible(true);
            }
        }
    }

    /**
     * ActionListener para la acción de aceptar la eliminación de un asistente.
     */
    public class bAceptarEliminarAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreAsistente = (String) vStaff.getCbStaff().getSelectedItem();
            String nombreEquipo = (String) vStaff.getJbEquipo().getSelectedItem();
            try {
                cv.borrarAsistente(nombreAsistente, nombreEquipo);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    /**
     * FocusListener para llenar el combo de selección de asistentes al perder el enfoque.
     */

    public void llenarComboEquipo() {


        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o -> vStaff.getJbEquipo().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * FocusListener para llenar el combo de selección de asistentes a editar al perder el enfoque.
     */
    public void llenarComboEquipoNuevo() {


        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o -> vStaff.getCbNuevoEquipo().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * FocusListener para llenar el combo de selección de asistentes a editar completamente al perder el enfoque.
     */

    public class ComboEquipoElimFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                vStaff.getCbStaff().removeAllItems();
                String nombreEquipo = (String) vStaff.getJbEquipo().getSelectedItem();

                if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
                    // Obtener asistentes por equipo desde el ControladorPrincipal
                    ArrayList<String> asistentes = cv.obtenerAsistentesPorEquipo(nombreEquipo, nombreEquipo);
                    // Limpiar items previos en la JComboBox

                    // Agregar los nuevos asistentes a la JComboBox
                    for (String asistente : asistentes) {
                        vStaff.getCbStaff().addItem(asistente);
                    }
                } else {
                    // Manejar el caso de que no se seleccione ningún equipo
                }
            } catch (Exception ex) {
                // Manejar la excepción
            }
        }

    }
    /**
     * FocusListener para llenar el combo de selección de asistentes a editar al perder el enfoque.
     */
    public class ComboEquipoEditarFocusListener implements FocusListener {
        /**
         * No se realiza ninguna acción cuando se gana el enfoque.
         */
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }/**
         * Al perder el enfoque, se llena el combo de selección de asistentes a editar con los asistentes del equipo seleccionado.
         * Se manejan excepciones en caso de errores durante el proceso.
         */

        @Override
        public void focusLost(FocusEvent e) {
            try {
                vStaff.getCbEditStaff().removeAllItems();
                String nombreEquipo = (String) vStaff.getJbEquipo().getSelectedItem();

                if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
                    // Obtener asistentes por equipo desde el ControladorPrincipal
                    ArrayList<String> asistentes = cv.obtenerAsistentesPorEquipo(nombreEquipo, nombreEquipo);
                    // Limpiar items previos en la JComboBox

                    // Agregar los nuevos asistentes a la JComboBox
                    for (String asistente : asistentes) {
                        vStaff.getCbEditStaff().addItem(asistente);
                    }
                } else {
                    // Manejar el caso de que no se seleccione ningún equipo
                }
            } catch (Exception ex) {
                // Manejar la excepción
            }
        }

    }


    /**
     * FocusListener para imprimir los datos de los asistentes seleccionados al perder el enfoque.
     */
    public class ComboEquipoEditarLlenarFocusListener implements FocusListener {/**
     * No se realiza ninguna acción cuando se gana el enfoque.
     */
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }
        /**
         * Al perder el enfoque, se imprime en la consola los datos de los asistentes seleccionados.
         * Se manejan excepciones en caso de errores durante el proceso.
         */
        @Override
        public void focusLost(FocusEvent e) {
            try {
                String nombreStaff = (String) vStaff.getCbEditStaff().getSelectedItem();
                String nombreEquipo = (String) vStaff.getJbEquipo().getSelectedItem();
                ArrayList<String> asistentes =  cv.obtenerAsistentesPorEquipo(nombreStaff, nombreEquipo);

                // Imprimir los datos del ArrayList
                for (String asistente : asistentes) {
                    System.out.println(asistente);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /*public class ComboEquipoEditarCompletarFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No action on focus gain
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                Equipo nombreEquipo = (Equipo) vStaff.getCbEquipo().getSelectedItem();
                Asistente nombreAntiguo = (Asistente) vStaff.getCbEditStaff().getSelectedItem();
                String nombreNuevo = vStaff.getTfNuevoNombre().getText();
                String apellido1 = vStaff.getTfNuevoApellido1().getText();
                String apellido2 = vStaff.getTfNuevoApellido2().getText();
                Integer sueldo = Integer.valueOf(vStaff.getTfNuevoSueldo().getText());
                ArrayList<Asistente> asistentes = cv.obtenerAsistentesPorEquipo(nombreEquipo,nombreAntiguo);
                for (Asistente asistente : asistentes) {
                    vStaff.getCbStaff().addItem(asistente);
                }
            } catch (Exception ex) {
                // Manejar la excepción
            }
        }

    }*/
}