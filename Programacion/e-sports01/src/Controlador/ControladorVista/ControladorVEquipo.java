package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Equipo;
import Modelo.Jugador;
import Vista.VentanaCompeticiones;
import Vista.VentanaEquipos;
import Vista.VentanaJugadores;

import javax.accessibility.AccessibleAction;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorVEquipo {

    private ControladorVista cv;

    private Connection con;
    private VentanaEquipos vEquipos;
    private VentanaCompeticiones vCompeticiones;


    int jugadoresInsertados=0;

    private List<Competicion> listaCompeticiones;
    private List<String> listaNombreCometiciones;
    private List<String> listaPatrocinadores;



    public ControladorVEquipo(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() throws Exception {
        vEquipos = new VentanaEquipos();
        vEquipos.setVisible(true);
        vEquipos.addVolver(new BVolverAL());
        vEquipos.addInicio(new BInicioAL());
        vEquipos.addrbNuevoAL(new RbNuevoAL());
        vEquipos.addrbEditarAL(new RbEditarAL());
        vEquipos.addrbEliminarAL(new RbEliminarAL());
        vEquipos.addbEntrenador(new bEntrenadorAL());
        vEquipos.addBjugadores(new bJugadoresAL());
        vEquipos.addbAceptarEliminar(new bAceptarEliminarAL());
        vEquipos.addbAceptarNuevo(new bAceptarNuevoAL());
        vEquipos.getCbEquipo().addFocusListener(new cbEquipoFocusListener() );
        vEquipos.getCbEquipos().addFocusListener(new cbEquipoEliminarFL() );
        vEquipos.addbAceptarEditar(new bAceptarEditarAL());
        vEquipos.limpiar();
        vEquipos.getpNuevo().setVisible(false);
        vEquipos.getpEditar().setVisible(false);
        vEquipos.getpEliminar().setVisible(false);
        llenarCombos();
        buscarPatrocinador();
        llenarComboEquipo();
        llenarComboEquipoEditar();
    }
    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vEquipos.dispose();
        }
    }
    public class BInicioAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vEquipos.dispose();
        }
    }


    public class RbNuevoAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbNuevo().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(true);
                vEquipos.getpEditar().setVisible(false);
                vEquipos.getpEliminar().setVisible(false);
            }
        }
    }


    public class bAceptarNuevoAL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jugadoresInsertados>=1){
               String nombre= vEquipos.getTfNombre().getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fecha =  LocalDate.parse((CharSequence) vEquipos.getCbFechaFundacion().getSelectedItem(),formatter);

                String patrocinador = (String) vEquipos.getCbPatrocinador().getSelectedItem();
                String competicion = (String) vEquipos.getCbCompeticion().getSelectedItem();
                try {
                    cv.crearEquipo(nombre, fecha, patrocinador,competicion);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                jugadoresInsertados= jugadoresInsertados-2;
                JOptionPane.showMessageDialog(null,"tienes que insertar " + jugadoresInsertados +" mas");
            }

        }
    }

    public class bEntrenadorAL implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarStaff();
           // String nombre =vEquipos.getTfNombre().getText();
           // cv.nombreequipo(nombre);
        }
    }

    public class bJugadoresAL implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

          if (vEquipos.getTfNombre().getText().isEmpty()){


              JOptionPane.showMessageDialog(null,"el nombre del equipo tiene que estar escrito");
          }else
          {
              cv.crearMostrarJugadores();
              jugadoresInsertados=jugadoresInsertados+1;
              String nombre =vEquipos.getTfNombre().getText();
              cv.nombreequipo(nombre);
          }



        }
    }
    public class RbEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbEditar().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(false);
                vEquipos.getpEditar().setVisible(true);
                vEquipos.getpEliminar().setVisible(false);
            }
        }
    }
    public class RbEliminarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vEquipos.getRbEliminar().isSelected()){
                vEquipos.limpiar();
                vEquipos.getpNuevo().setVisible(false);
                vEquipos.getpEditar().setVisible(false);
                vEquipos.getpEliminar().setVisible(true);
            }
        }
    }

    public class bAceptarEliminarAL implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreBorrar = (String) vEquipos.getCbEquipo().getSelectedItem();
            try {
                cv.borrarEquipo(nombreBorrar);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }




    public void llenarCombos() {
        try {



            listaNombreCometiciones = cv.buscarCompeticiones();

            // Depurar listaNombreCometiciones
            if (listaNombreCometiciones.isEmpty()) {
                System.out.println("listaNombreCometiciones está vacía");
            } else {
                for (String nombre : listaNombreCometiciones) {
                    vEquipos.getCbCompeticion().addItem(nombre);

                }
            }



        } catch (SQLException e) {
            throw new RuntimeException("Error al llenar el combo box de competiciones", e);
        }
    }

    public void buscarPatrocinador() throws Exception {
        try {
            listaPatrocinadores = cv.buscarPatrocinador();
            for (String nombre : listaPatrocinadores) {
                vEquipos.getCbPatrocinador().addItem(nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al llenar el combo box de patrocinadores", e);
        }

    }
  public  class   bAceptarEditarAL implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {

          JOptionPane.showMessageDialog(null,"hola");
      }
  }
    private class cbEquipoEliminarFL implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No hacer nada al ganar el foco
        }

        @Override
        public void focusLost(FocusEvent e) {
            String nombreEquipo = (String) vEquipos.getCbEquipos().getSelectedItem();
            if (!nombreEquipo.isEmpty()) {
                JOptionPane.showMessageDialog(null,"el campo de equipo tiene que estar relleno");

            }else{
                Equipo buscarEquipo= cv.buscarEquipo(nombreEquipo);
            }


        }
    }



    public void llenarComboEquipo(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o-> vEquipos.getCbEquipo().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public void llenarComboEquipoEditar(){



        String nombre = null;
        try {
            ArrayList<Equipo> listaEquipos = cv.selectEquipo(nombre);
            listaEquipos.forEach(o-> vEquipos.getCbEquipos().addItem(o.getNombre()));


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private class cbEquipoFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // No hacer nada al ganar el foco
        }

        @Override
        public void focusLost(FocusEvent e) {

            llenarComboEquipo();
        }
    }

}
