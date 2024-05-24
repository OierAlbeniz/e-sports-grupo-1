package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Juego;
import Vista.VentanaCompeticiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ControladorVCompeticion {

    private ControladorVista cv;
    private Connection con;
    private VentanaCompeticiones vCompeticiones;
    private List<Juego> listaJuegos;
    private List<Competicion> listaCompeticiones;
    private List<String> listaNombreCometiciones;

    public ControladorVCompeticion(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vCompeticiones = new VentanaCompeticiones();
        vCompeticiones.setVisible(true);
        vCompeticiones.addVolver(new BVolverAL());
        vCompeticiones.addInicio(new BInicioAL());

        vCompeticiones.addrbNuevoAL(new RbNuevoAL());
        vCompeticiones.addrbEditarAL(new RbEditarAL());
        vCompeticiones.addrbEliminarAL(new RbEliminarAL());
        vCompeticiones.limpiar();
        vCompeticiones.getpNuevo().setVisible(false);
        vCompeticiones.getpEditar().setVisible(false);
        vCompeticiones.getpEliminar().setVisible(false);
        vCompeticiones.addbAceptarAl(new bAceptarAl());
        vCompeticiones.addbAceptarEliminarAl(new bAceptarEliminarAl());
        vCompeticiones.addbAceptarEditarAl(new bAceptarEditarAl());
        vCompeticiones.addCbEditAL(new cbEditAL());
        llenarCombos();
    }

    public void llenarCombos() {
        try {
            listaJuegos = cv.buscarJuegos();
            listaJuegos.forEach(o -> {
                vCompeticiones.getCbJuego().addItem(o.getNombre());
                System.out.println("Juego añadido al combo: " + o.getNombre());
            });


            listaNombreCometiciones = cv.buscarCompeticiones();
            for (String nombre : listaNombreCometiciones) {
                vCompeticiones.getCbCompeticion().addItem(nombre);
                vCompeticiones.getCbEditCompeticion().addItem(nombre);
                System.out.println("Competición añadida al combo: " + nombre);
            }
            vCompeticiones.getCbNuevoEstado().addItem("Abierto");
            vCompeticiones.getCbNuevoEstado().addItem("Cerrado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listaJuegos.forEach(o -> vCompeticiones.getCbNuevoJuego().addItem(o.getNombre()));
    }

    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vCompeticiones.dispose();
        }
    }

    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vCompeticiones.dispose();
        }
    }

    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vCompeticiones.getRbNuevo().isSelected()) {
                vCompeticiones.limpiar();
                vCompeticiones.getpNuevo().setVisible(true);
                vCompeticiones.getpEditar().setVisible(false);
                vCompeticiones.getpEliminar().setVisible(false);
            }
        }
    }

    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vCompeticiones.getRbEditar().isSelected()) {
                vCompeticiones.limpiarEditar();
                vCompeticiones.getpNuevo().setVisible(false);
                vCompeticiones.getpEditar().setVisible(true);
                vCompeticiones.getpEliminar().setVisible(false);
                try {
                    listaJuegos = cv.buscarJuegos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                listaJuegos.forEach(o -> {
                    vCompeticiones.getCbNuevoJuego().addItem(o.getNombre());
                    System.out.println("Juego añadido al combo: " + o.getNombre());
                });
                vCompeticiones.getCbNuevoJuego().setSelectedIndex(-1);
            }
        }
    }

    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vCompeticiones.getRbEliminar().isSelected()) {
                vCompeticiones.limpiar();
                vCompeticiones.getpNuevo().setVisible(false);
                vCompeticiones.getpEditar().setVisible(false);
                vCompeticiones.getpEliminar().setVisible(true);
            }
        }
    }

    private class bAceptarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vCompeticiones.getRbNuevo().isSelected()) {
                    String nombre = vCompeticiones.getTfNombre().getText();
                    String fechaInicio = vCompeticiones.getTfFechaInicio().getText();
                    String fechaFin = vCompeticiones.getTfFechaFin().getText();
                    System.out.println(fechaInicio);
                    System.out.println(fechaFin);

                    if (nombre.isEmpty()) {
                        throw new Exception("El nombre de la competicion no puede estar vacia");
                    }
                    if (fechaInicio.isEmpty()) {
                        throw new Exception("La fecha de inicio no puede estar vacia");
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate fechaIni = LocalDate.parse(fechaInicio, formato);


                    if (fechaFin.isEmpty()) {
                        throw new Exception("La fecha de fin no puede estar vacia");
                    }
                    LocalDate fechaFinal = LocalDate.parse(fechaFin, formato);
                    String nombreJuego = vCompeticiones.getCbJuego().getSelectedItem().toString();
                    Juego j = cv.buscarJuego(nombreJuego);

                    System.out.println(j.getIdJuego());
                    Competicion c = new Competicion(nombre, fechaIni, fechaFinal, "abierto",j);
                    System.out.println(c.getNombre());

                    cv.insertarCompeticion(c);
                }
            } catch (Exception ex) {
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }
    private class bAceptarEliminarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vCompeticiones.getRbEliminar().isSelected()) {
                    Competicion c= cv.buscarCompeticion(vCompeticiones.getCbCompeticion().getSelectedItem().toString());

                    cv.eliminarCompeticion(c);
                }
            } catch (Exception ex) {
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }
    private class bAceptarEditarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (vCompeticiones.getRbEditar().isSelected()) {
                    String nombreCompeticion = vCompeticiones.getCbEditCompeticion().getSelectedItem().toString();
                    Competicion competicion = cv.buscarCompeticion(nombreCompeticion);

                    // Obtener los nuevos valores de los campos de texto
                    String nuevoNombre = vCompeticiones.getTfNuevoNombre().getText();
                    String nuevaFechaInicio = vCompeticiones.getTfNuevaFechaIni().getText();
                    String nuevaFechaFin = vCompeticiones.getTfNuevaFechaFin().getText();

                    // Verificar si los campos están vacíos
                    if (nuevoNombre.isEmpty() || nuevaFechaInicio.isEmpty() || nuevaFechaFin.isEmpty()) {
                        throw new Exception("Todos los campos son obligatorios");
                    }

                    // Convertir las fechas al formato adecuado
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaIni = LocalDate.parse(nuevaFechaInicio, formato);
                    LocalDate fechaFin = LocalDate.parse(nuevaFechaFin, formato);




                    // Actualizar la competición con los nuevos valores
                    competicion.setNombre(nuevoNombre);
                    competicion.setFechaInicio(fechaIni);
                    competicion.setFechaFin(fechaFin);

                    // Ejecutar la actualización en la base de datos
                    //cv.actualizarCompeticion(competicion);

                    // Mostrar un mensaje de éxito
                    vCompeticiones.mostrar("Competición actualizada exitosamente");
                }
            } catch (Exception ex) {
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }
    public class cbEditAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            if (vCompeticiones.getRbEditar().isSelected()) {
                if(!(vCompeticiones.getCbEditCompeticion().getSelectedItem()==null)){
                    String nombreCompeticion = vCompeticiones.getCbEditCompeticion().getSelectedItem().toString();
                    System.out.println(nombreCompeticion);

                    Competicion competicion = cv.buscarCompeticion(nombreCompeticion);
                    // Rellenar los campos de texto con los detalles de la competición seleccionada
                    vCompeticiones.getTfNuevoNombre().setText(competicion.getNombre());
                    vCompeticiones.getTfNuevaFechaIni().setText(competicion.getFechaInicio().toString());
                    vCompeticiones.getTfNuevaFechaFin().setText(competicion.getFechaFin().toString());
                    vCompeticiones.getCbNuevoEstado().setSelectedItem(competicion.getEstado());
                    vCompeticiones.getCbNuevoJuego().setSelectedItem(competicion.getJuego().getNombre().toString());
                }
                }
            }catch (Exception ex) {
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }
}