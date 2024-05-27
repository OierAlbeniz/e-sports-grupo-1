package Controlador.ControladorVista;

import Controlador.ControladorPrincipal;

import Modelo.*;


import Modelo.Equipo;
import Modelo.Usuario;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
/**
 * Controlador principal de la vista que gestiona la interacción entre la interfaz de usuario y el controlador principal.
 */
public class ControladorVista {

    private ControladorPrincipal cp;
    private ControladorVUsuario cvu;
    private ControladorVLogin cvlogin;
    private ControladorVEditar cveditar;
    private ControladorVP cvp;
    private ControladorVPatrocinador cvpatrocinador;
    private ControladorVEquipo cvequipo;
    private ControladorVJugador cvjugador;
    private ControladorVJuego cvjuego;
    private ControladorVStaff cvstaff;
    private ControladorVConsultas cvconsultas;
    private ControladorVCompeticion cvcompeticion;
    private ControladorVClasificacion cvclasificacion;
    private ControladorVInsertResultados cvInsertResultados;
    private ControladorVUltJornada cvultJornada;
    private Usuario usuario=null;

    /**
     * Constructor de la clase ControladorVista.
     *
     * @param cp El controlador principal.
     * @throws Exception Si ocurre algún error.
     */

    public ControladorVista(ControladorPrincipal cp) throws Exception {
        this.cp = cp;
        cvp=new ControladorVP(this);
        crearMostrarVinicioSesion();
    }
    /**
     * Crea y muestra la ventana de inicio de sesión.
     */
    public void crearMostrarVinicioSesion() {
        cvlogin = new ControladorVLogin(this);
        cvlogin.crearMostrar();

    }
    /**
     * Crea y muestra la ventana principal.
     */
    public void crearMostrarVP() {
        cvp.crearMostrar(usuario);
    }
    /**
     * Crea y muestra la ventana principal luego de iniciar sesión.
     *
     * @param user El usuario que ha iniciado sesión.
     */
    public void crearMostrarVPlogin(Usuario user) {
        usuario=user;
        cvp.crearMostrar(user);
    }/**
     * Crea y muestra la ventana de edición.
     */
    public void crearMostrarEditar() {
        cveditar=new ControladorVEditar(this);
        cveditar.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de clasificación.
     */
    public void crearMostrarClasificacion() {
        cvclasificacion=new ControladorVClasificacion(this);
        cvclasificacion.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de inserción de resultados.
     */
    public void crearMostrarInsertResultados() {
        cvInsertResultados=new ControladorVInsertResultados(this);
        cvInsertResultados.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de la última jornada.
     */
    public void crearMostrarUltJornada() {
        cvultJornada=new ControladorVUltJornada(this);
        cvultJornada.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de gestión de patrocinadores.
     */
    public void crearMostrarPatrocinador() {
        cvpatrocinador=new ControladorVPatrocinador(this);
        cvpatrocinador.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de consultas.
     */
    public void crearConsultas() {
        cvconsultas=new ControladorVConsultas(this);
        cvconsultas.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de gestión de equipos.
     *
     * @throws Exception Si ocurre algún error.
     */
    public void crearMostrarEquipos() throws Exception {
        cvequipo=new ControladorVEquipo(this);
        cvequipo.crearMostrar();
    }
    /**
     * Crea y muestra la ventana de gestión de usuarios.
     */
    public void crearMostrarUsuario() {
        cvu=new ControladorVUsuario(this);
        cvu.crearMostrarUsuario();
    }
    /**
     * Crea y muestra la ventana de gestión de jugadores.
     */
    public void crearMostrarJugadores() {
        cvjugador=new ControladorVJugador(this);
        cvjugador.crearMostrar();
    }

    /**
     * Crea y muestra la ventana de gestión de entrenadores.
     */
    public void crearMostrarEntrenador() {
        cvjugador = new ControladorVJugador(this);
        cvjugador.crearMostrar();
    }

    /**
     * Crea y muestra la ventana de gestión de juegos.
     */
    public void crearMostrarJuegos() {
        cvjuego = new ControladorVJuego(this);
        cvjuego.crearMostrar();
    }

    /**
     * Crea y muestra la ventana de gestión del personal.
     */
    public void crearMostrarStaff() {
        cvstaff = new ControladorVStaff(this);
        cvstaff.crearMostrar();
    }

    /**
     * Crea y muestra la ventana de gestión de competiciones.
     */
    public void crearMostrarCompeticiones() {
        cvcompeticion = new ControladorVCompeticion(this);
        cvcompeticion.crearMostrar();
    }

    /**
     * Busca un usuario por su nombre de usuario y contraseña.
     *
     * @param user     El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario encontrado.
     * @throws Exception Si ocurre algún error.
     */
    public Usuario buscarUsuario(String user, String password) throws Exception {
        return cp.buscarUsuario(user, password);
    }

    /**
     * Busca un equipo por su nombre.
     *
     * @param nombre El nombre del equipo.
     * @return El equipo encontrado.
     * @throws Exception Si ocurre algún error.
     */
    public Equipo buscarEquipo(String nombre) throws Exception {
        return cp.buscarEquipo(nombre);
    }

    /**
     * Obtiene la cantidad de equipos.
     *
     * @return La cantidad de equipos.
     * @throws Exception Si ocurre algún error.
     */
    public Integer cantidadEquipos() throws Exception {
        return cp.cantidadEquipos();
    }

    /**
     * Obtiene una lista de equipos.
     *
     * @return La lista de equipos.
     * @throws Exception Si ocurre algún error.
     */
    public List<Equipo> llenarEquipos() throws Exception {
        return cp.llenarEquipos();
    }

    /**
     * Obtiene una lista de jugadores.
     *
     * @param x El parámetro X.
     * @return La lista de jugadores.
     * @throws Exception Si ocurre algún error.
     */
    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return cp.llenarJugadores(x);
    }

    /**
     * Obtiene una lista de competiciones.
     *
     * @return La lista de competiciones.
     * @throws Exception Si ocurre algún error.
     */
    public List<Competicion> llenarCompeticiones() throws Exception {
        return cp.llenarCompeticiones();
    }

    /**
     * Obtiene una lista de equipos por competición.
     *
     * @param x El parámetro X.
     * @return La lista de equipos por competición.
     * @throws Exception Si ocurre algún error.
     */
    public List<Equipo> llenarEquiposCompeticion(Integer x) throws Exception {
        return cp.llenarEquiposCompeticion(x);
    }

    /**
     * Genera el calendario.
     *
     * @throws Exception Si ocurre algún error.
     */
    public void generarCalendario() throws Exception {
        cp.generarCalendario();
    }

    /**
     * Crea un nuevo jugador y lo agrega al sistema.
     *
     * @param nombre          El nombre del jugador.
     * @param primerApellido  El primer apellido del jugador.
     * @param segundoApellido El segundo apellido del jugador.
     * @param sueldo          El sueldo del jugador.
     * @param nacionalidad    La nacionalidad del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param nickname        El apodo del jugador.
     * @param rol             El rol del jugador.
     * @param equipo          El equipo al que pertenece el jugador.
     * @return El jugador creado.
     * @throws Exception Si ocurre algún error.
     */
    public Usuario crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
        cp.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
        return null;
    }
    /**
     * Realiza una selección de equipos.
     *
     * @param nombre El nombre de referencia para la selección.
     * @return Una lista de equipos seleccionados.
     * @throws Exception Si ocurre algún error.
     */
    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cp.selectEquipo(nombre);
    }
    /**
     * Busca todos los juegos registrados.
     *
     * @return Una lista de juegos encontrados.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<Juego> buscarJuegos() throws SQLException {return cp.buscarJuegos();}
    /**
     * Busca un juego por su nombre.
     *
     * @param nombre El nombre del juego a buscar.
     * @return El juego encontrado.
     * @throws Exception Si ocurre algún error.
     */
    public Juego buscarJuego(String nombre) throws Exception{return cp.buscarJuego(nombre);}
    /**
     * Inserta una competición en el sistema.
     *
     * @param c La competición a insertar.
     * @throws Exception Si ocurre algún error.
     */
    public void insertarCompeticion(Competicion c) throws Exception{cp.insertarCompeticion(c);}
    /**
     * Busca todas las competiciones registradas.
     *
     * @return Una lista de nombres de competiciones.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<String> buscarCompeticiones() throws SQLException {return cp.buscarCompeticiones();}
    /**
     * Obtiene una lista de jugadores según el nombre del equipo seleccionado.
     *
     * @param equiposelecionado El nombre del equipo seleccionado.
     * @return La lista de jugadores del equipo seleccionado.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<Jugador> llenarJugadoresNombre(String equiposelecionado) throws SQLException
    {
        return  cp.llenarJugadoresNombre(equiposelecionado);
    }
    /**
     * Busca todos los patrocinadores registrados.
     *
     * @return Una lista de nombres de patrocinadores.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<String> buscarPatrocinador() throws SQLException {
        return cp.buscarPatrocinador();
    }
    /**
     * Elimina un jugador del sistema.
     *
     * @param nombre El nombre del jugador a eliminar.
     * @param equipo El equipo al que pertenece el jugador.
     * @throws Exception Si ocurre algún error.
     */
    public void eliminarJugador(String nombre,String equipo) throws Exception {
        cp.eliminarJugador(nombre,equipo);
    }
    /**
     * Crea un nuevo usuario y lo agrega al sistema.
     *
     * @param nombre      El nombre del usuario.
     * @param contrasena  La contraseña del usuario.
     * @param tipoUsuario El tipo de usuario.
     * @return El usuario creado.
     * @throws Exception Si ocurre algún error.
     */
    public Usuario crearUsuario(String nombre,String contrasena,String tipoUsuario) throws Exception {
        cp.crearUsuario(nombre,contrasena, tipoUsuario);
        return null;
    }
    /**
     * Actualiza la información de un jugador en el sistema.
     *
     * @param nombre El nombre del jugador a actualizar.
     * @param equipo El equipo al que pertenece el jugador.
     * @return El jugador actualizado.
     * @throws Exception Si ocurre algún error.
     */
    public Jugador actualizarJugador(String nombre, String equipo) throws Exception {
        cp.actualizarJugador( nombre, equipo);
        Jugador buscarDatos = cp.actualizarJugador(nombre, equipo);

        return buscarDatos;
    }
    /**
     * Confirma la edición de un jugador en el sistema.
     *
     * @param nombre          El nombre del jugador.
     * @param primerApellido  El primer apellido del jugador.
     * @param segundoApellido El segundo apellido del jugador.
     * @param sueldo          El sueldo del jugador.
     * @param nacionalidad    La nacionalidad del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param nickname        El apodo del jugador.
     * @param rol             El rol del jugador.
     * @param nuevoEquipo     El nuevo equipo al que pertenece el jugador.
     * @param nombreAntiguo   El nombre antiguo del jugador.
     * @param equipoAntiguo   El equipo antiguo al que pertenecía el jugador.
     * @throws Exception Si ocurre algún error.
     */
    public void editarJugadorConfir(String nombre,String primerApellido,String segundoApellido,double sueldo,String nacionalidad,LocalDate fechaNacimiento,String nickname,String rol,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {


        cp.editarJugadorConfir(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }
    /**
     * Notifica al controlador de vista de jugador el nombre del equipo.
     *
     * @param nombre El nombre del equipo.
     */
    public void nombreequipo(String nombre){
        cvjugador.nombreEquipo(nombre);
    }
    /**
     * Elimina un equipo del sistema.
     *
     * @param nombre El nombre del equipo a eliminar.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public void borrarEquipo(String nombre) throws SQLException {
        cp.borrarEquipo(nombre);
    }


    /**
     * Obtiene una lista de jugadores según un tipo específico.
     *
     * @param tipo El tipo de jugadores a obtener.
     * @return La lista de jugadores.
     * @throws Exception Si ocurre algún error.
     */
    public List<Jugador> llenarJugadoresS(String tipo) throws Exception {
        cp.llenarJugadoresS(tipo);
        List<Jugador> listaJugadores = cp.llenarJugadoresS(tipo);
        return listaJugadores;
    }
    /**
     * Obtiene una lista de equipos según un tipo específico.
     *
     * @param tipo El tipo de equipos a obtener.
     * @return La lista de equipos.
     * @throws Exception Si ocurre algún error.
     */
    public List<Equipo> llenarEquiposS(String tipo) throws Exception {
        cp.llenarEquiposS(tipo);
        List<Equipo> listaEquipo = cp.llenarEquiposS(tipo);
        return listaEquipo;
    }
    /**
     * Obtiene una lista de entrenadores según un tipo específico.
     *
     * @param tipo El tipo de entrenadores a obtener.
     * @return La lista de entrenadores.
     * @throws Exception Si ocurre algún error.
     */
    public List<Entrenador> llenarEntrenador(String tipo) throws Exception {
        cp.llenarEntrenador(tipo);
        List<Entrenador> listaEntrenador = cp.llenarEntrenador(tipo);
        return listaEntrenador;
    }
    /**
     * Obtiene una lista de asistentes según un tipo específico.
     *
     * @param tipo El tipo de asistentes a obtener.
     * @return La lista de asistentes.
     * @throws Exception Si ocurre algún error.
     */
    public List<Asistente> llenarAsistente(String tipo) throws Exception {
        cp.llenarAsistente(tipo);
        List<Asistente> listaAsistente = cp.llenarAsistente(tipo);
        return listaAsistente;
    }
    /**
     * Obtiene una lista de competiciones según un tipo específico.
     *
     * @param tipo El tipo de competiciones a obtener.
     * @return La lista de competiciones.
     * @throws Exception Si ocurre algún error.
     */
    public List<Competicion> llenarCompeticion(String tipo) throws Exception {
        cp.llenarCompeticion(tipo);
        List<Competicion> listaCompeticion = cp.llenarCompeticion(tipo);
        return listaCompeticion;
    }
    /**
     * Obtiene una lista de juegos según un tipo específico.
     *
     * @param tipo El tipo de juegos a obtener.
     * @return La lista de juegos.
     * @throws Exception Si ocurre algún error.
     */
    public List<Juego> llenarJuegos(String tipo) throws Exception {
        cp.llenarJuegos(tipo);
        List<Juego> listaJuego = cp.llenarJuegos(tipo);
        return listaJuego;
    }
    /**
     * Crea un nuevo equipo en el sistema.
     *
     * @param nombre      El nombre del equipo.
     * @param fecha       La fecha de creación del equipo.
     * @param patrocinador El patrocinador del equipo.
     * @param competicion La competición en la que participa el equipo.
     * @throws Exception Si ocurre algún error.
     */

    public void crearEquipo(String nombre, LocalDate fecha, Patrocinador patrocinador, Competicion competicion) throws Exception {
        cp.crearEquipo(nombre, fecha, patrocinador,competicion);
    }
    /**
     * Edita un equipo existente en el sistema.
     *
     * @param nombreAntiguo  El nombre antiguo del equipo.
     * @param nombreNuevo    El nuevo nombre del equipo.
     * @param fechacambio    La nueva fecha de cambio del equipo.
     * @param VincularNuevo  El nuevo patrocinador vinculado al equipo.
     * @param Desvincular    El patrocinador a desvincular del equipo.
     * @throws Exception Si ocurre algún error.
     */
    public void editarEquipo(String nombreAntiguo,String nombreNuevo,LocalDate fechacambio,String VincularNuevo,String Desvincular) throws Exception {
        cp.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
    }

    /**
     * Edita la información de un patrocinador en el sistema.
     *
     * @param nombre         El nombre del patrocinador.
     * @param nuevoEquipo    El nuevo equipo al que está vinculado el patrocinador.
     * @param nombreAntiguo  El nombre antiguo del patrocinador.
     * @param equipoAntiguo  El equipo antiguo al que estaba vinculado el patrocinador.
     * @throws Exception Si ocurre algún error.
     */

    public void editarPatrocinadorConfir(String nombre,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {

        cp.editarPatrocinadorConfir(nombre, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }
    /**
     * Actualiza la información de un patrocinador en el sistema.
     *
     * @param nombre El nombre del patrocinador a actualizar.
     * @param equipo El equipo al que está vinculado el patrocinador.
     * @return El patrocinador actualizado.
     * @throws Exception Si ocurre algún error.
     */
    public Patrocinador actualizarPatrocinador(String nombre, String equipo) throws Exception {
        cp.actualizarPatrocinador( nombre, equipo);
        Patrocinador buscarDatosPatrocinador = cp.actualizarPatrocinador(nombre, equipo);

        return buscarDatosPatrocinador;
    }
    /**
     * Obtiene una lista de patrocinadores según el nombre del equipo seleccionado.
     *
     * @param equiposelecionado El nombre del equipo seleccionado.
     * @return La lista de patrocinadores del equipo seleccionado.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<Patrocinador> llenarPatrocinadorNombre(String equiposelecionado) throws SQLException
    {
        return  cp.llenarPatrocinadorNombre(equiposelecionado);
    }


    /**
     * Busca una competición por su nombre.
     *
     * @param c El nombre de la competición a buscar.
     * @return La competición encontrada.
     * @throws Exception Si ocurre algún error.
     */
    public Competicion buscarCompeticion(String c) throws Exception {
        return cp.buscarCompeticion(c);
    }
    /**
     * Busca todas las jornadas de una competición.
     *
     * @param j El identificador de la competición.
     * @return Una lista de números de jornadas.
     * @throws Exception Si ocurre algún error.
     */
    public List<Integer> buscarJornadas(Integer j) throws Exception {
        return cp.buscarJornadas(j);
    }
    /**
     * Busca todos los enfrentamientos de una jornada y competición específica.
     *
     * @param j El identificador de la jornada.
     * @param c El identificador de la competición.
     * @return Una lista de enfrentamientos.
     * @throws Exception Si ocurre algún error.
     */
    public List<Enfrentamiento> buscarEnfrentamientos(Integer j, Integer c) throws Exception {
        return cp.buscarEnfrentamientos(j,c);
    }
    /**
     * Guarda los resultados de un enfrentamiento en el sistema.
     *
     * @param idEnfJor       El identificador del enfrentamiento y jornada.
     * @param resultLocal    El resultado del equipo local.
     * @param resultVisitante El resultado del equipo visitante.
     * @throws Exception Si ocurre algún error.
     */
    public void guardarResultados(Integer idEnfJor, int resultLocal, int resultVisitante) throws Exception {
        cp.guardarResultados(idEnfJor, resultLocal, resultVisitante);
    }
    /**
     * Actualiza la tabla de clasificación con los resultados de un enfrentamiento.
     *
     * @param enfrentamiento  El enfrentamiento del cual se actualizarán los resultados.
     * @param resultLocal     El resultado del equipo local.
     * @param resultVisitante El resultado del equipo visitante.
     * @throws Exception Si ocurre algún error.
     */
    public void actualizarTablaClasificacion(Enfrentamiento enfrentamiento, int resultLocal, int resultVisitante) throws Exception {
        cp.actualizarTablaClasificacion(enfrentamiento,resultLocal, resultVisitante );
    }
    /**
     * Llena los campos de resultados de un enfrentamiento.
     *
     * @param resultadoLocal     El campo de resultado local.
     * @param resultadoVisitante El campo de resultado visitante.
     * @param idEnfJor           El identificador del enfrentamiento y jornada.
     */
    public void llenarResultados(JTextField resultadoLocal, JTextField resultadoVisitante, Integer idEnfJor) {
        cp.llenarResultados(resultadoLocal, resultadoVisitante, idEnfJor);
    }
    /**
     * Obtiene las clasificaciones por competición.
     *
     * @param idCompeticion El identificador de la competición.
     * @return La lista de clasificaciones.
     * @throws Exception Si ocurre algún error.
     */

    public List<Clasificacion> obtenerClasificacionesPorCompeticion(Integer idCompeticion) throws Exception {
        return cp.obtenerClasificacionesPorCompeticion(idCompeticion);
    }
    /**
     * Busca la última jornada de una competición.
     *
     * @param competicionID El identificador de la competición.
     * @return El número de la última jornada.
     * @throws Exception Si ocurre algún error.
     */
    public Integer buscarUltimaJornada(Integer competicionID) throws Exception {
        return cp.buscarUltimaJornada(competicionID);
    }
    /**
     * Elimina una competición del sistema.
     *
     * @param c La competición a eliminar.
     * @throws Exception Si ocurre algún error.
     */
    public void eliminarCompeticion(Competicion c) throws Exception {
        cp.eliminarCompeticion(c);
    }
    /**
     * Actualiza la información de un jugador con respecto a su equipo.
     *
     * @param nombre     El nombre del jugador.
     * @param patrocinador El patrocinador del jugador.
     * @param competicion La competición del jugador.
     * @param fecha      La fecha de actualización.
     * @throws Exception Si ocurre algún error.
     */
    public void updateEquipoJugador(String nombre, String patrocinador, String competicion, LocalDate fecha) throws Exception {
        cp.updateEquipoJugador(nombre, patrocinador, competicion, fecha);
    }
    /**
     * Busca un patrocinador por su nombre.
     *
     * @param nombrePatrocinador El nombre del patrocinador a buscar.
     * @return El patrocinador encontrado.
     */
    public Patrocinador buscarPatrocinadorNombre(String nombrePatrocinador) {
        return  cp.buscarPatrocinadorNombre(nombrePatrocinador);
    }

    /**
     * Busca el identificador de un juego por su nombre.
     *
     * @param nuevoJuego El nombre del juego a buscar.
     * @return El identificador del juego.
     * @throws Exception Si ocurre algún error.
     */
    public int buscarIdJuegoPorNombre(String nuevoJuego) throws Exception {
        return cp.buscarIdJuegoPorNombre(nuevoJuego);
    }
    /**
     * Modifica la información de una competición.
     *
     * @param idCompeticion El identificador de la competición.
     * @param nuevoNombre   El nuevo nombre de la competición.
     * @param date          La nueva fecha de inicio de la competición.
     * @param date1         La nueva fecha de fin de la competición.
     * @param nuevoEstado   El nuevo estado de la competición.
     * @param idJuego       El identificador del juego asociado a la competición.
     * @throws Exception Si ocurre algún error.
     */
    public void modificarCompeticion(Integer idCompeticion, String nuevoNombre, Date date, Date date1, String nuevoEstado, int idJuego) throws Exception {
        cp.modificarCompeticion(idCompeticion,nuevoNombre, date, date1, nuevoEstado, idJuego);
    }
    /**
     * Modifica la información de un juego en el sistema.
     *
     * @param j El juego a modificar.
     * @throws Exception Si ocurre algún error.
     */
    public void modificarJuego(Juego j)throws Exception  {
        cp.modificarJuego(j);
    }

    /**
     * Elimina un juego del sistema.
     *
     * @param j El juego a eliminar.
     * @throws Exception Si ocurre algún error.
     */
    public void eliminarJuego(Juego j)throws Exception  {
        cp.eliminarJuego(j);
    }
    /**
     * Inserta un nuevo juego en el sistema.
     *
     * @param j El juego a insertar.
     * @throws Exception Si ocurre algún error.
     */
    public void insertarJuego(Juego j) throws Exception {
        cp.insertarJuego(j);
    }
    /**
     * Busca todas las competiciones asociadas a un juego.
     *
     * @param idJuego El identificador del juego.
     * @return La lista de competiciones asociadas al juego.
     * @throws Exception Si ocurre algún error.
     */
    public List<Competicion> buscarCompeticionesPorJuego(Integer idJuego) throws Exception {
        return cp.buscarCompeticionesPorJuego(idJuego);
    }
    /**
     * Vincula un juego a una competición.
     *
     * @param idJuego      El identificador del juego.
     * @param idCompeticion El identificador de la competición.
     * @throws Exception Si ocurre algún error.
     */
    public void vincularJuegoACompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cp.vincularJuegoACompeticion(idJuego, idCompeticion);
    }
    /**
     * Desvincula un juego de una competición.
     *
     * @param idJuego      El identificador del juego.
     * @param idCompeticion El identificador de la competición.
     * @throws Exception Si ocurre algún error.
     */
    public void desvincularJuegoDeCompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cp.desvincularJuegoDeCompeticion(idJuego, idCompeticion);
    }
    /**
     * Inserta un nuevo patrocinador en el sistema.
     *
     * @param p El patrocinador a insertar.
     * @throws Exception Si ocurre algún error.
     */
    public void insertarPatrocinador(Patrocinador p) throws Exception {cp.insertarPatrocinador(p);}
    /**
     * Borra un patrocinador del sistema.
     *
     * @param nombre El nombre del patrocinador a borrar.
     * @throws Exception Si ocurre algún error.
     */
    public void borrarPatrocinador(String nombre) throws Exception{cp.borrarPatrocinador(nombre);}
    /**
     * Edita la información de un patrocinador en el sistema.
     *
     * @param nombreNuevo El nuevo nombre del patrocinador.
     * @throws Exception Si ocurre algún error.
     */
    public void editarPatrocinador(String nombreNuevo) throws Exception{cp.editarPatrocinador(nombreNuevo);}
    /**
     * Busca un patrocinador por su nombre para eliminarlo.
     *
     * @param nombre El nombre del patrocinador a buscar.
     * @return El patrocinador encontrado.
     * @throws Exception Si ocurre algún error.
     */
    public Patrocinador buscarPatrocinadorEliminar(String nombre) throws Exception{return cp.buscarPatrocinadorEliminar(nombre);}
    /**
     * Crea un nuevo asistente en el sistema.
     *
     * @param nombre     El nombre del asistente.
     * @param apellido1  El primer apellido del asistente.
     * @param apellido2  El segundo apellido del asistente.
     * @param sueldo     El sueldo del asistente.
     * @param tipo       El tipo de asistente.
     * @throws Exception Si ocurre algún error.
     */
    public void crearAsistente(String nombre,String apellido1,String apellido2,Integer sueldo,String tipo) throws Exception {
        cp.crearAsistente(nombre, apellido1, apellido2, sueldo, tipo);
    }
    /**
     * Crea un nuevo entrenador en el sistema.
     *
     * @param nombre    El nombre del entrenador.
     * @param apellido1 El primer apellido del entrenador.
     * @param apellido2 El segundo apellido del entrenador.
     * @param sueldo    El sueldo del entrenador.
     * @param tipo      El tipo de entrenador.
     * @throws Exception Si ocurre algún error.
     */
    public void crearEntrenador(String nombre,String apellido1,String apellido2,Integer sueldo,String tipo) throws Exception {
        cp.crearEntrenador(nombre, apellido1, apellido2, sueldo, tipo);
    }
    /**
     * Obtiene los asistentes asociados a un equipo.
     *
     * @param nombreEquipo El nombre del equipo.
     * @param equipo       El tipo de equipo.
     * @return La lista de asistentes.
     * @throws Exception Si ocurre algún error.
     */
    public ArrayList<String> obtenerAsistentesPorEquipo(String nombreEquipo, String equipo) throws Exception {
        return cp.obtenerAsistentesPorEquipo(nombreEquipo,equipo);
    }
    /**
     * Borra un asistente de un equipo.
     *
     * @param nombreAsistente El nombre del asistente a borrar.
     * @param nombreEquipo    El nombre del equipo del cual se eliminará el asistente.
     * @throws Exception Si ocurre algún error.
     */
    public void borrarAsistente(String nombreAsistente,String nombreEquipo) throws Exception {
        cp.borrarAsistente(nombreAsistente,nombreEquipo);
    }
}