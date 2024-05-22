package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Equipo;
import Modelo.Juego;
import Vista.VentanaCompeticiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void crearMostrar() throws Exception {
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
        llenarCombos();
        vCompeticiones.addbAceptarBorrarAl(new bAceptarBorrarAl());
        vCompeticiones.addbAceptarEditarAl(new bAceptarNuevoAl());
        vCompeticiones.addCbEditCompeticionFl(new cbEditCompeticionFl());
    }
    public void llenarCombos() throws Exception {
        try {
            listaJuegos = cv.buscarJuegos();
            listaJuegos.forEach(o->vCompeticiones.getCbJuego().addItem(o.getNombre()));

            listaNombreCometiciones = cv.buscarCompeticiones();
            for(String nombre : listaNombreCometiciones){
                vCompeticiones.getCbCompeticion().addItem(nombre);
            }
            for(String nombre : listaNombreCometiciones){
                vCompeticiones.getCbEditCompeticion().addItem(nombre);
            }
            vCompeticiones.getCbNuevoEstado().addItem("Abierto");
            vCompeticiones.getCbNuevoEstado().addItem("Cerrado");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            listaJuegos = cv.buscarJuegos();
            for(Juego j : listaJuegos){
                vCompeticiones.getCbNuevoJuego().addItem(j.getNombre());
            }
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
            if(vCompeticiones.getRbNuevo().isSelected()){
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
            if(vCompeticiones.getRbEditar().isSelected()){
                vCompeticiones.limpiar();
                vCompeticiones.getpNuevo().setVisible(false);
                vCompeticiones.getpEditar().setVisible(true);
                vCompeticiones.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vCompeticiones.getRbEliminar().isSelected()){
                vCompeticiones.limpiar();
                vCompeticiones.getpNuevo().setVisible(false);
                vCompeticiones.getpEditar().setVisible(false);
                vCompeticiones.getpEliminar().setVisible(true);
            }
        }
    }

    private class bAceptarBorrarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombreComp = vCompeticiones.getCbCompeticion().getSelectedItem().toString();
                String idComp = cv.buscarCompeticionPorNombre(nombreComp);

                cv.borrarCompeticion(Integer.parseInt(idComp));
            }
            catch(Exception ex){
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }

    private class bAceptarNuevoAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombre = vCompeticiones.getTfNuevoNombre().getText();
                String fechaIni = vCompeticiones.getTfNuevaFechaIni().getText();
                String fechaFin = vCompeticiones.getTfFechaFin().getText();
                String estado = vCompeticiones.getCbNuevoEstado().toString();
                String juego = vCompeticiones.getCbNuevoJuego().toString();

                if(nombre.isEmpty()){
                }

            }
            catch (Exception ex){
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }

    private class cbEditCompeticionFl implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {


        }

        @Override
        public void focusLost(FocusEvent e) {
            try{
                 Competicion c = cv.obtenerCompeticion(vCompeticiones.getCbEditCompeticion().toString());
                 vCompeticiones.getTfNuevoNombre().setText(c.getNombre());
                 vCompeticiones.getTfFechaInicio().setText(String.valueOf(c.getFechaInicio()));
                 vCompeticiones.getTfNuevaFechaFin().setText(String.valueOf(c.getFechaFin()));
                 vCompeticiones.getCbNuevoEstado().setSelectedItem(c.getEstado());
                 vCompeticiones.getCbNuevoJuego().setSelectedItem(c.getJuego().getNombre());
            }
            catch (Exception ex){
                vCompeticiones.mostrar(ex.getMessage());
            }
        }
    }
}