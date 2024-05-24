package Controlador.ControladorVista;

import Modelo.Patrocinador;
import Vista.AccionRealizada;
import Vista.VentanaPatrocinadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;

public class ControladorVPatrocinador {

    private ControladorVista cv;
    private List<String> listaPatrocinadores;
    private Patrocinador p;
    private VentanaPatrocinadores vPatrocinadores;
    private AccionRealizada aRealizada;

    public ControladorVPatrocinador(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar()  {
        vPatrocinadores = new VentanaPatrocinadores();
        vPatrocinadores.setVisible(true);
        vPatrocinadores.addVolver(new bVolverAl());
        vPatrocinadores.addInicio(new bInicioAl());
        aRealizada=new AccionRealizada();
        aRealizada.addRealizadaAL(new RealizadaAL());
        vPatrocinadores.addAceptarNuevoAL(new bAceptarNuevoAl());
        vPatrocinadores.addAceptarEliminarAL(new bAceptarEliminarAL());
        vPatrocinadores.addCbEditarPatrocinadoresFl(new cbEditarPatrocinadoresFl());
        vPatrocinadores.addAceptarEditarAL(new bAceptarEditarAl());
        vPatrocinadores.addrbNuevoAL(new rbNuevoAl());
        vPatrocinadores.addrbEliminarAL(new rbEliminarAl());
        vPatrocinadores.addrbEditarAL(new rbEditarAl());
        vPatrocinadores.getpEditar().setVisible(false);
        vPatrocinadores.getpEliminar().setVisible(false);
        vPatrocinadores.getpNuevo().setVisible(false);

        llenarCombos();

    }

    public class RealizadaAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            aRealizada.dispose();
        }
    }
    private class rbNuevoAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbNuevo().isSelected()){
                vPatrocinadores.getpNuevo().setVisible(true);
                vPatrocinadores.getpEditar().setVisible(false);
                vPatrocinadores.getpEliminar().setVisible(false);
            }
        }
    }

    private class rbEliminarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbEliminar().isSelected()){
                vPatrocinadores.getpNuevo().setVisible(false);
                vPatrocinadores.getpEditar().setVisible(false);
                vPatrocinadores.getpEliminar().setVisible(true);
            }
        }
    }

    private class rbEditarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vPatrocinadores.getRbEditar().isSelected()){
                vPatrocinadores.getpNuevo().setVisible(false);
                vPatrocinadores.getpEditar().setVisible(true);
                vPatrocinadores.getpEliminar().setVisible(false);
            }
        }
    }

    private void llenarCombos() {
        try {
            listaPatrocinadores = cv.buscarPatrocinador();

            listaPatrocinadores.forEach(o->vPatrocinadores.getCbEliminarPatrocinador().addItem(o));
            vPatrocinadores.getCbEliminarPatrocinador().setSelectedIndex(-1);

            listaPatrocinadores.forEach(o->vPatrocinadores.getCbEditarPatrocinadores().addItem(o));
            vPatrocinadores.getCbEditarPatrocinadores().setSelectedIndex(-1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private class bAceptarNuevoAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombre = vPatrocinadores.getTfNombrePatrocinador().getText();

                if(nombre.isEmpty()){
                    throw new Exception("El nombre del patrocinador no puede estar vacio");
                }

                p = new Patrocinador(nombre);
                cv.insertarPatrocinador(p);
                aRealizada.getEdicionTexto().setText("¡PATROCINADOR INSERTADO!");
                aRealizada.setVisible(true);

            }
            catch (Exception ex){
                vPatrocinadores.mostrar(ex.getMessage());
            }
        }
    }


    private class bAceptarEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombre = vPatrocinadores.getCbEliminarPatrocinador().getSelectedItem().toString();
                cv.borrarPatrocinador(nombre);
                aRealizada.getEdicionTexto().setText("¡PATROCINADOR ELIMINADO!");
                aRealizada.setVisible(true);

            }
            catch (Exception ex){
                vPatrocinadores.mostrar(ex.getMessage());
            }
        }
    }

    private class cbEditarPatrocinadoresFl implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                String nombre = vPatrocinadores.getCbEditarPatrocinadores().getSelectedItem().toString();
                p = cv.buscarPatrocinadorEliminar(nombre);

                vPatrocinadores.getTfNuevoNombre().setText(p.getNombre());

            }
            catch (Exception ex){
                vPatrocinadores.mostrar(ex.getMessage());
            }
        }
    }

    private class bAceptarEditarAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nombreNuevo = vPatrocinadores.getTfNuevoNombre().getText();
                cv.editarPatrocinador(nombreNuevo);
                aRealizada.getEdicionTexto().setText("¡PATROCINADOR EDITADO!");
                aRealizada.setVisible(true);
            }
            catch (Exception ex){
                vPatrocinadores.mostrar(ex.getMessage());
            }
        }
    }

    private class bVolverAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vPatrocinadores.dispose();
        }
    }

    private class bInicioAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vPatrocinadores.dispose();
        }
    }
}
