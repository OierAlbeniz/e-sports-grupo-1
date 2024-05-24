package Controlador.ControladorVista;


import Modelo.Competicion;
import Modelo.Equipo;

import Modelo.Jornada;
import Modelo.Usuario;
import Vista.AccionRealizada;
import Vista.VentanaInicioSesion;

import Vista.VentanaPrincipal;
import Vista.VistaPerfil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ControladorVP {
    private ControladorVista cv;
    private VentanaPrincipal vp;
    private ControladorVLogin cvl;
    private VistaPerfil vper;
    private VentanaInicioSesion vsesion;
    private AccionRealizada aRealizada;
    private Connection con;



    public ControladorVP(ControladorVista cv) throws Exception {
        this.cv = cv;
        this.vsesion = new VentanaInicioSesion();
    }

    public void crearMostrar(Usuario user) {
        if (user.getTipo().equals("administrador")) {
            vp = new VentanaPrincipal();
            aRealizada=new AccionRealizada();
            aRealizada.addRealizadaAL(new RealizadaAL());
            vp.setVisible(true);
            vp.addeditar(new BEditarAL());

            vp.addUsuarios(new BusuarioAL());
            vp.addcerrarInsc(new BCerrarInscAL());
            vp.addConsultas(new bConsultasAL());
            vp.addBSalirAL(new BSalirAl());
            vp.addCerrarSesionAL(new CerrarSesionAl());
            vp.addClasificacion(new BClasificacionAL());
            vp.addInsertResultados(new BInsertResultadosAL());
            vp.addUltJornada(new BUltJornadaAL());
            vp.addClasificacionXml(new bClasificacionXmlAl());
            vp.addTodasJornadasXml(new bTodasJornadasXmlAl());
            vp.addUltimaJornadaXml(new bUltimaJornadaXmlAl());


            vp.getTxNombre().setText(user.getNombre() + " !");
        } else {
            vp = new VentanaPrincipal();
            vp.setVisible(true);

            aRealizada=new AccionRealizada();
            aRealizada.addRealizadaAL(new RealizadaAL());
            vp.getpEditar().setVisible(false);
            vp.getpCerrarInsc().setVisible(false);
            vp.getpConsulta().setVisible(false);
            vp.getpInsertResultados().setVisible(false);

            vp.addUsuarios(new BusuarioAL());
            vp.addBSalirAL(new BSalirAl());
            vp.addCerrarSesionAL(new CerrarSesionAl());
            vp.addClasificacion(new BClasificacionAL());
            vp.addUltJornada(new BUltJornadaAL());
            vp.getJlTipo().setText("Usuario");

            vp.getTxNombre().setText(user.getNombre() + " !");
        }
    }

    public class RealizadaAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            aRealizada.dispose();
        }
    }
    public class BusuarioAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarUsuario();
            vp.dispose();
        }
    }

    public class BClasificacionAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarClasificacion();
            vp.dispose();
        }
    }
    public class BInsertResultadosAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarInsertResultados();
            vp.dispose();
        }
    }
    public class BUltJornadaAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarUltJornada();
            vp.dispose();
        }
    }

    public class bConsultasAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearConsultas();

        }
    }

    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vp.dispose();
        }
    }

    public class BCerrarInscAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                boolean cerrarInsc = false;
                List<Competicion> listaCompeticiones = cv.llenarCompeticiones();
                boolean par=true;
                for (Competicion competicion : listaCompeticiones) {
                    int x=0;
                    List<Equipo> listaEquiposCompeticion = cv.llenarEquiposCompeticion(x);
                    int numEquipos = listaEquiposCompeticion.size();

                    if (numEquipos % 2 == 0) {
                        System.out.println("El número de equipos en la competición " + competicion.getNombre() + " es par.");
                    } else {
                        par=false;
                        System.out.println("El número de equipos en la competición " + competicion.getNombre() + " es par.");
                        throw new Exception("El número de equipos en la competición " + competicion.getNombre() + " es impar.");
                    }
                    if(par=true){
                        cerrarInsc=true;
                    }
                    x=x+1;
                }
                Integer comprobar = cv.buscarUltimaJornada(1);
                System.out.println(comprobar);
                if (cerrarInsc){
                    if (comprobar==0){
                        cv.generarCalendario();
                        aRealizada.getEdicionTexto().setText("¡ Calendario generado !");
                        aRealizada.setVisible(true);
                    }else{
                        aRealizada.getEdicionTexto().setText("El calendario ya estaba generado.");
                        aRealizada.setVisible(true);
                    }
                }

            } catch (Exception ex) {
                vp.mostrarMensaje(ex.getMessage());
            }
        }
    }

    public class BSalirAl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    public class CerrarSesionAl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVinicioSesion();
            vp.dispose();
        }
    }


    private class bClasificacionXmlAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
                String user = "equipo16";
                String passwd = "equipo16";
                con = DriverManager.getConnection(url, user, passwd);
            String sql = "{call PKG_XML.obtener_xml_clasificacion()}";
            CallableStatement statement = con.prepareCall(sql);
            statement.execute();
            System.out.println("Procedimiento Obtener Clasificacion XML ejecutado correctamente");


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    private class bTodasJornadasXmlAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
                String user = "equipo16";
                String passwd = "equipo16";
                con = DriverManager.getConnection(url, user, passwd);
                String sql = "{call PKG_XML.obtener_xml_todas_jornadas()}";
                CallableStatement statement = con.prepareCall(sql);
                statement.execute();
                System.out.println("Procedimiento Obtener Todas las Jornadas XML ejecutado correctamente");


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class bUltimaJornadaXmlAl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
                String user = "equipo16";
                String passwd = "equipo16";
                con = DriverManager.getConnection(url, user, passwd);
                String sql = "{call PKG_XML.obtener_xml_ultima_jornada()}";
                CallableStatement statement = con.prepareCall(sql);
                statement.execute();
                System.out.println("Procedimiento Obtener Ultima Jornada XML ejecutado correctamente");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

