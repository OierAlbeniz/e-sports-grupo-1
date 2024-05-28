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

    /**
     * Constructor de ControladorVP.
     *
     * @param cv Controlador de la vista.
     * @throws Exception Excepción general.
     */

    public ControladorVP(ControladorVista cv) throws Exception {
        this.cv = cv;
        this.vsesion = new VentanaInicioSesion();
    }

    /**
     * Crea y muestra la ventana principal.
     *
     * @param user Usuario que ha iniciado sesión.
     */

    public void crearMostrar(Usuario user) {
        Integer comprobar = null;
        try {
            comprobar = cv.buscarUltimaJornada(1);
            System.out.println(comprobar + " ultima de jornada");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
            if (comprobar==0){
                vp.getpEditar().setVisible(true);
            }else{
                vp.getpEditar().setVisible(false);
            }

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
            vp.getpUsuarios().setVisible(false);

            vp.addUsuarios(new BusuarioAL());
            vp.addBSalirAL(new BSalirAl());
            vp.addCerrarSesionAL(new CerrarSesionAl());
            vp.addClasificacion(new BClasificacionAL());
            vp.addUltJornada(new BUltJornadaAL());
            vp.getJlTipo().setText("Usuario");

            vp.getTxNombre().setText(user.getNombre() + " !");
        }
    }

    /**
     * ActionListener para el botón de acción realizada.
     */

    public class RealizadaAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            aRealizada.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la visualización de información de usuarios.
     * Cuando se activa, llama al método 'crearMostrarUsuario' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para administrar la información de usuarios, y cierra la ventana actual.
     */

    public class BusuarioAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarUsuario();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la visualización de información de clasificación.
     * Cuando se activa, llama al método 'crearMostrarClasificacion' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para ver la información de clasificación, y cierra la ventana actual.
     */

    public class BClasificacionAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarClasificacion();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la inserción de resultados de partidos.
     * Cuando se activa, llama al método 'crearMostrarInsertResultados' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para insertar resultados de partidos, y cierra la ventana actual.
     */

    public class BInsertResultadosAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarInsertResultados();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la visualización de la última jornada de partidos.
     * Cuando se activa, llama al método 'crearMostrarUltJornada' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para ver la última jornada de partidos, y cierra la ventana actual.
     */

    public class BUltJornadaAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarUltJornada();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la realización de consultas.
     * Cuando se activa, llama al método 'crearConsultas' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para realizar consultas.
     */

    public class bConsultasAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearConsultas();

        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la edición de detalles de partidos.
     * Cuando se activa, llama al método 'crearMostrarEditar' de la clase 'ControladorVista'
     * para crear y mostrar la interfaz de usuario para editar detalles de partidos, y cierra la ventana actual.
     */

    public class BEditarAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarEditar();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con el cierre de inscripciones para una competición.
     * Cuando se activa, realiza validaciones en el número de equipos en cada competición, genera
     * un calendario si es necesario, y muestra mensajes apropiados. También captura excepciones y muestra mensajes de error.
     */

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
                        vp.getpEditar().setVisible(false);
                    }else{
                        aRealizada.getEdicionTexto().setText("El calendario ya estaba generado.");
                        aRealizada.setVisible(true);
                        vp.getpEditar().setVisible(false);
                    }
                }

            } catch (Exception ex) {
                vp.mostrarMensaje(ex.getMessage());
            }
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la salida de la aplicación.
     * Cuando se activa, sale de la aplicación.
     */

    public class BSalirAl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con el cierre de sesión actual.
     * Cuando se activa, llama al método 'crearMostrarVinicioSesion' de la clase 'ControladorVista'
     * para volver a la ventana de inicio de sesión, y cierra la ventana actual.
     */

    public class CerrarSesionAl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVinicioSesion();
            vp.dispose();
        }
    }

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la obtención de datos de clasificación en formato XML.
     * Cuando se activa, se conecta a una base de datos y ejecuta un procedimiento almacenado para recuperar los datos XML.
     * Cualquier excepción SQL se envuelve en una RuntimeException.
     */

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

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la obtención de todos los datos de partidos en formato XML.
     * Cuando se activa, se conecta a una base de datos y ejecuta un procedimiento almacenado para recuperar los datos XML.
     * Cualquier excepción SQL se envuelve en una RuntimeException.
     */

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

    /**
     * Clase ActionListener para manejar acciones de usuario relacionadas con la obtención de la última jornada de partidos en formato XML.
     * Cuando se activa, se conecta a una base de datos y ejecuta un procedimiento almacenado para recuperar los datos XML.
     * Cualquier excepción SQL se envuelve en una RuntimeException.
     */

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
