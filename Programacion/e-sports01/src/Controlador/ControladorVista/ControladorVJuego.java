package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Juego;
import Vista.VentanaJuegos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorVJuego {
    private ControladorVista cv;
    private Connection con;
    private VentanaJuegos vJuegos;
    private List<Juego> listaJuegos;
    private List<String> listaNombreCometiciones;

    public ControladorVJuego(ControladorVista cv) {
        this.cv = cv;
    }

    /**
     * Crea y muestra la ventana de gestión de juegos.
     */

    public void crearMostrar() {
        vJuegos = new VentanaJuegos();
        vJuegos.setVisible(true);
        vJuegos.addVolver(new BVolverAL());
        vJuegos.addInicio(new BInicioAL());
        listaNombreCometiciones=new ArrayList<>();
        listaJuegos=new ArrayList<>();
        vJuegos.addrbNuevoAL(new RbNuevoAL());
        vJuegos.addrbEditarAL(new RbEditarAL());
        vJuegos.addrbEliminarAL(new RbEliminarAL());
        //vJuegos.limpiar();
        vJuegos.getpNuevo().setVisible(false);
        vJuegos.getpEditar().setVisible(false);
        vJuegos.getpEliminar().setVisible(false);
        vJuegos.addbAceptarAl(new bAceptarAl());
        vJuegos.addbAceptarEliminarAl(new bAceptarEliminarAl());
        vJuegos.addbAceptarEditarAl(new bAceptarEditarAl());
        vJuegos.addcbEditJuegoAL(new cbEditJuegoAL());
        llenarCombos();
    }

    /**
     * Llena los combos de la ventana con las competiciones y juegos disponibles.
     */

    public void llenarCombos() {
        try {
            listaNombreCometiciones = cv.buscarCompeticiones();
            for (String nombre : listaNombreCometiciones) {
                vJuegos.getCbCompeticion().addItem(nombre);
                System.out.println("Competición añadida al combo: " + nombre);
            }
            listaJuegos = cv.buscarJuegos();
            listaJuegos.forEach(o -> {
                vJuegos.getCbJuego().addItem(o.getNombre());
                vJuegos.getCbEditJuego().addItem(o.getNombre());
                System.out.println("Juego añadido al combo: " + o.getNombre());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ActionListener para el botón "Volver".
     */

    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vJuegos.dispose();
        }
    }

    /**
     * ActionListener para el botón "Inicio".
     */

    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vJuegos.dispose();
        }
    }

    /**
     * ActionListener para la opción "Nuevo" de juegos.
     */

    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vJuegos.getRbNuevo().isSelected()) {

                vJuegos.getpNuevo().setVisible(true);
                vJuegos.getpEditar().setVisible(false);
                vJuegos.getpEliminar().setVisible(false);
            }
        }
    }

    /**
     * ActionListener para la opción "Editar" de juegos.
     */
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vJuegos.getRbEditar().isSelected()) {

                vJuegos.getpNuevo().setVisible(false);
                vJuegos.getpEditar().setVisible(true);
                vJuegos.getpEliminar().setVisible(false);
                vJuegos.getCbEditJuego().setSelectedIndex(-1);
            }
        }
    }

    /**
     * ActionListener para la opción "Eliminar" de juegos.
     */


    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vJuegos.getRbEliminar().isSelected()) {

                vJuegos.getpNuevo().setVisible(false);
                vJuegos.getpEditar().setVisible(false);
                vJuegos.getpEliminar().setVisible(true);
            }
        }
    }

    /**
     * ActionListener para el botón "Aceptar" en la creación de un juego.
     */

    private class bAceptarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vJuegos.getRbNuevo().isSelected()) {
                    String nombre = vJuegos.getTfNombre().getText();
                    String empresa = vJuegos.getTfEmpresa().getText();
                    String fechaLanz = vJuegos.getTfFechaLanz().getText();

                    if (nombre.isEmpty() || empresa.isEmpty() || fechaLanz.isEmpty()) {
                        throw new Exception("Todos los campos son obligatorios");
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate fechaLanzamiento = LocalDate.parse(fechaLanz, formato);

                    Juego j = new Juego(nombre, empresa, fechaLanzamiento, null);
                    cv.insertarJuego(j);

                    vJuegos.mostrar("Juego añadido exitosamente");
                }
            } catch (Exception ex) {
                vJuegos.mostrar(ex.getMessage());
            }
        }
    }

    /**
     * ActionListener para el botón "Aceptar" en la eliminación de un juego.
     */

    private class bAceptarEliminarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vJuegos.getRbEliminar().isSelected()) {
                    String nombreJuego = vJuegos.getCbJuego().getSelectedItem().toString();
                    Juego j = cv.buscarJuego(nombreJuego);

                    cv.eliminarJuego(j);

                    vJuegos.mostrar("Juego eliminado exitosamente");
                }
            } catch (Exception ex) {
                vJuegos.mostrar(ex.getMessage());
            }
        }
    }

    /**
     * ActionListener para el botón "Aceptar" en la modificación de un juego.
     */

    private class bAceptarEditarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vJuegos.getRbEditar().isSelected()) {
                    String nombreJuego = vJuegos.getCbEditJuego().getSelectedItem().toString();
                    Juego j = cv.buscarJuego(nombreJuego);

                    String nuevoNombre = vJuegos.getTfNuevoNombre().getText();
                    String nuevaEmpresa = vJuegos.getTfNuevaEmpresa().getText();
                    String nuevaFechaLanz = vJuegos.getTfNuevaFechaLanz().getText();

                    if (nuevoNombre.isEmpty() || nuevaEmpresa.isEmpty() || nuevaFechaLanz.isEmpty()) {
                        throw new Exception("Todos los campos son obligatorios");
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate nuevaFechaLanzamiento = LocalDate.parse(nuevaFechaLanz, formato);

                    j.setNombre(nuevoNombre);
                    j.setEmpresa(nuevaEmpresa);
                    j.setFechalanzamiento(nuevaFechaLanzamiento);

                    String competicionVincular = vJuegos.getCbVincular().getSelectedItem().toString();
                    if (!competicionVincular.equals("no vincular")) {
                        Competicion comp = cv.buscarCompeticion(competicionVincular);
                        cv.vincularJuegoACompeticion(j.getIdJuego(), comp.getIdCompeticion());
                    }

                    String competicionDesvincular = vJuegos.getCbDesvincular().getSelectedItem().toString();
                    if (!competicionDesvincular.equals("no desvincular")) {
                        Competicion comp = cv.buscarCompeticion(competicionDesvincular);
                        cv.desvincularJuegoDeCompeticion(j.getIdJuego(), comp.getIdCompeticion());
                    }

                    cv.modificarJuego(j);

                    vJuegos.mostrar("Juego modificado exitosamente");
                }
            } catch (Exception ex) {
                vJuegos.mostrar(ex.getMessage());
            }
        }
    }

    /**
     * ActionListener para el cambio de selección en el combo de edición de juego.
     */

    public class cbEditJuegoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vJuegos.getRbEditar().isSelected()) {
                    if (vJuegos.getCbEditJuego().getSelectedItem() != null) {
                        String nombreJuego = vJuegos.getCbEditJuego().getSelectedItem().toString();
                        System.out.println(nombreJuego);

                        Juego juego = cv.buscarJuego(nombreJuego);

                        // Rellenar los campos de texto con los detalles del juego seleccionado
                        vJuegos.getTfNuevoNombre().setText(juego.getNombre());
                        vJuegos.getTfNuevaEmpresa().setText(juego.getEmpresa());

                        // Formatear la fecha de lanzamiento al formato deseado
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String fechaFormateada = juego.getFechalanzamiento().format(formatter);
                        vJuegos.getTfNuevaFechaLanz().setText(fechaFormateada);

                        // Llenar la ComboBox de vincular a competiciones
                        llenarComboBoxCompeticionesNoAsociadas(juego, vJuegos.getCbVincular());
                        vJuegos.getCbVincular().addItem("no vincular");

                        // Llenar la ComboBox de desvincular competiciones
                        llenarComboBoxCompeticiones(juego, vJuegos.getCbDesvincular());
                        vJuegos.getCbDesvincular().addItem("no desvincular");
                    }
                }
            } catch (Exception ex) {
                vJuegos.mostrar(ex.getMessage());
            }
        }
    }

    /**
     * Llena el combo de competiciones asociadas al juego.
     * @param juego Juego del cual se obtienen las competiciones asociadas.
     * @param comboBox ComboBox a llenar con las competiciones asociadas.
     * @throws Exception Si ocurre algún error al buscar las competiciones asociadas.
     */

        private void llenarComboBoxCompeticiones(Juego juego, JComboBox comboBox) throws Exception {

            List<Competicion> competiciones = cv.buscarCompeticionesPorJuego(juego.getIdJuego());
            comboBox.removeAllItems();
            for (Competicion competicion : competiciones) {
                comboBox.addItem(competicion.getNombre());
            }


        }

    /**
     * Llena el combo de competiciones no asociadas al juego.
     * @param juego Juego del cual se obtienen las competiciones no asociadas.
     * @param comboBox ComboBox a llenar con las competiciones no asociadas.
     * @throws Exception Si ocurre algún error al buscar las competiciones no asociadas.
     */

        private void llenarComboBoxCompeticionesNoAsociadas(Juego juego, JComboBox comboBox) throws Exception {
            // Obtener todas las competiciones
            List<Competicion> todasCompeticiones = cv.llenarCompeticiones();

            // Obtener las competiciones asociadas al juego
            List<Competicion> competicionesAsociadas = cv.buscarCompeticionesPorJuego(juego.getIdJuego());

            // Filtrar las competiciones que no están asociadas al juego
            List<Competicion> competicionesNoAsociadas = new ArrayList<>();
            for (Competicion competicion : todasCompeticiones) {
                boolean estaAsociada = false;
                for (Competicion asociada : competicionesAsociadas) {
                    if (competicion.getIdCompeticion() == asociada.getIdCompeticion()) {
                        estaAsociada = true;
                        break;
                    }
                }
                if (!estaAsociada) {
                    competicionesNoAsociadas.add(competicion);
                }
            }

            // Limpiar la ComboBox
            comboBox.removeAllItems();

            // Agregar las competiciones no asociadas a la ComboBox
            for (Competicion competicion : competicionesNoAsociadas) {
                comboBox.addItem(competicion.getNombre());
            }
        }

}
