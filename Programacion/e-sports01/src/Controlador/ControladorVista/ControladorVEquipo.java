package Controlador.ControladorVista;

import Vista.VentanaEquipos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorVEquipo {

    private ControladorVista cv;
    private Connection con;
    private VentanaEquipos vEquipos;


    public ControladorVEquipo(ControladorVista cv) {
        this.cv = cv;
    }

    public void crearMostrar() {
        vEquipos = new VentanaEquipos();
        vEquipos.setVisible(true);
        vEquipos.addVolver(new BVolverAL());
        vEquipos.addInicio(new BInicioAL());

        vEquipos.addrbNuevoAL(new RbNuevoAL());
        vEquipos.addrbEditarAL(new RbEditarAL());
        vEquipos.addrbEliminarAL(new RbEliminarAL());
        vEquipos.limpiar();
        vEquipos.getpNuevo().setVisible(false);
        vEquipos.getpEditar().setVisible(false);
        vEquipos.getpEliminar().setVisible(false);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
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
       String nombreAntiguo= (String) vEquipos.getCbEquipos().getSelectedItem();
        String nombreNuevo = vEquipos.getTfNuevoNombre().getText();
        LocalDate fechacambio = (LocalDate) vEquipos.getCbEditFecha().getSelectedItem();
        String VincularNuevo = (String) vEquipos.getCbVincular().getSelectedItem();
        String Desvincular = (String) vEquipos.getCbDesvincular().getSelectedItem();
          try {
              cv.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
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
