package Controlador.ControladorVista;

import Modelo.Competicion;
import Modelo.Equipo;
import Modelo.Patrocinador;
import Vista.VentanaCompeticiones;
import Vista.VentanaEquipos;

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

    public void crearMostrar()  {
        vEquipos = new VentanaEquipos();
        vEquipos.setVisible(true);
        vEquipos.addVolver(new BVolverAL());
        vEquipos.addInicio(new BInicioAL());
        vEquipos.addrbNuevoAL(new RbNuevoAL());
        vEquipos.addrbEditarAL(new RbEditarAL());
        vEquipos.addrbEliminarAL(new RbEliminarAL());
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
        llenarComboFechaEditar();
        llenarComboEquipoEditarMenos();
        llenarComboEquipoEditarMas();
    }

    public class BVolverAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
            if (jugadoresInsertados>=2){
                String nombre = vEquipos.getTfNombre().getText();
                String patrocinador = (String) vEquipos.getCbPatrocinador().getSelectedItem();
                String competicion = (String) vEquipos.getCbCompeticion().getSelectedItem();
                LocalDate fecha = (LocalDate) vEquipos.getCbFechaFundacion().getSelectedItem();
                try {
                    cv.updateEquipoJugador(nombre,patrocinador,competicion,fecha);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                int jugadoresPorInsertar=2-jugadoresInsertados;
                JOptionPane.showMessageDialog(null,"tienes que insertar " + jugadoresPorInsertar +" jugador/es mas");
            }

        }
    }

    public class bJugadoresAL implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (vEquipos.getTfNombre().getText().isEmpty() ){
                JOptionPane.showMessageDialog(null,"el nombre del equipo tiene que estar escrito");
            }else
            {
                cv.crearMostrarJugadores();


                String nombre= vEquipos.getTfNombre().getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fecha =  LocalDate.parse((CharSequence) vEquipos.getCbFechaFundacion().getSelectedItem(),formatter);
                cv.nombreequipo(nombre);
                try {
                    String nombrePatrocinador = vEquipos.getCbPatrocinador().getSelectedItem().toString();
                    Patrocinador patrocinador = cv.buscarPatrocinadorNombre(nombrePatrocinador);
                    String nombreCompeticion = vEquipos.getCbCompeticion().getSelectedItem().toString();
                    Competicion competicion = cv.buscarCompeticion(nombreCompeticion);


                    if (jugadoresInsertados < 1) {
                        cv.crearEquipo(nombre, fecha, patrocinador, competicion);
                    }
                }catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


                jugadoresInsertados=jugadoresInsertados+1;
                System.out.println(nombre);
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


    public void buscarPatrocinador() {
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
            String nombreAntiguo = (String) vEquipos.getCbEquipos().getSelectedItem();
            String nombreNuevo = vEquipos.getTfNuevoNombre().getText();
            String fechaString = (String) vEquipos.getCbEditFecha().getSelectedItem();

            // Convertir la cadena de fecha a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fechacambio = LocalDate.parse(fechaString, formatter);

            String VincularNuevo = (String) vEquipos.getCbVincular().getSelectedItem();
            String Desvincular = (String) vEquipos.getCbDesvincular().getSelectedItem();

            try {
                cv.editarEquipo(nombreAntiguo, nombreNuevo, fechacambio, VincularNuevo, Desvincular);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
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
            if (nombreEquipo.isEmpty()) {
                JOptionPane.showMessageDialog(null,"el campo de equipo tiene que estar relleno");

            }else{
                try {

                    Equipo buscarEquipo= cv.buscarEquipo(nombreEquipo);

                    vEquipos.getTfNuevoNombre().setText(buscarEquipo.getNombre());
                    vEquipos.getCbEditFecha().addItem(buscarEquipo.getFechaFundacion());
                    vEquipos.getCbEditFecha().setSelectedItem(buscarEquipo.getFechaFundacion());

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
    }
    public void llenarComboFechaEditar(){
        vEquipos.getCbEditFecha().addItem("2008/01/01");
        vEquipos.getCbEditFecha().addItem("2009/01/01");
        vEquipos.getCbEditFecha().addItem("2010/01/01");
        vEquipos.getCbEditFecha().addItem("2011/01/01");
        vEquipos.getCbEditFecha().addItem("2012/01/01");
        vEquipos.getCbEditFecha().addItem("2013/01/01");
        vEquipos.getCbEditFecha().addItem("2014/01/01");
        vEquipos.getCbEditFecha().addItem("2015/01/01");
        vEquipos.getCbEditFecha().addItem("2016/01/01");
        vEquipos.getCbEditFecha().addItem("2017/01/01");
        vEquipos.getCbEditFecha().addItem("2018/01/01");
        vEquipos.getCbEditFecha().addItem("2019/01/01");
        vEquipos.getCbEditFecha().addItem("2020/01/01");
        vEquipos.getCbEditFecha().addItem("2021/01/01");
        vEquipos.getCbEditFecha().addItem("2022/01/01");
        vEquipos.getCbEditFecha().addItem("2023/01/01");
        vEquipos.getCbEditFecha().addItem("2024/01/01");


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

    public void llenarComboEquipoEditarMenos() {
        try {



            listaNombreCometiciones = cv.buscarCompeticiones();

            // Depurar listaNombreCometiciones
            if (listaNombreCometiciones.isEmpty()) {
                System.out.println("listaNombreCometiciones está vacía");
            } else {
                for (String nombre : listaNombreCometiciones) {
                    vEquipos.getCbDesvincular().addItem(nombre);

                }
            }



        } catch (SQLException e) {
            throw new RuntimeException("Error al llenar el combo box de competiciones", e);
        }
    }
    public void llenarComboEquipoEditarMas() {
        try {



            listaNombreCometiciones = cv.buscarCompeticiones();

            // Depurar listaNombreCometiciones
            if (listaNombreCometiciones.isEmpty()) {
                System.out.println("listaNombreCometiciones está vacía");
            } else {
                for (String nombre : listaNombreCometiciones) {
                    vEquipos.getCbVincular().addItem(nombre);

                }
            }



        } catch (SQLException e) {
            throw new RuntimeException("Error al llenar el combo box de competiciones", e);
        }
    }

    private class cbEquipoFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            llenarComboEquipo();
        }
    }

}