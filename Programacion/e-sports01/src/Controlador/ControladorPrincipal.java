package Controlador;

import Controlador.ControladorBD.ControladorBD;
import Controlador.ControladorVista.ControladorVista;

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
 * Controlador principal que gestiona la lógica de la aplicación.
 */
public class ControladorPrincipal {
    private ControladorVista cv;
    private ControladorBD cb;
    /**
     * Constructor de la clase ControladorPrincipal.
     *
     * @throws Exception Si ocurre un error durante la inicialización de los controladores.
     */
    public ControladorPrincipal() throws Exception {
        cv = new ControladorVista(this);
        cb = new ControladorBD(this);
    }


    /**
     * Obtiene la cantidad de equipos en la base de datos.
     *
     * @return La cantidad de equipos.
     * @throws Exception Si ocurre un error durante la obtención de la cantidad de equipos.
     */
    public Integer cantidadEquipos() throws Exception {
        return cb.cantidadEquipos();
    }
    /**
     * Obtiene una lista de todos los equipos.
     *
     * @return La lista de equipos.
     * @throws Exception Si ocurre un error durante la obtención de los equipos.
     */
    public List<Equipo> llenarEquipos() throws Exception {
        return cb.llenarEquipos();
    }

    /**
     * Crea un nuevo jugador y lo inserta en la base de datos.
     *
     * @param nombre El nombre del jugador.
     * @param primerApellido El primer apellido del jugador.
     * @param segundoApellido El segundo apellido del jugador.
     * @param sueldo El sueldo del jugador.
     * @param nacionalidad La nacionalidad del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param nickname El apodo del jugador.
     * @param rol El rol del jugador.
     * @param equipo El equipo al que pertenece el jugador.
     * @throws Exception Si ocurre un error durante la creación del jugador.
     */
    public void crearJugador(String nombre, String primerApellido, String segundoApellido, Integer sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol , String equipo) throws Exception {
         cb.crearJugador(nombre,primerApellido,segundoApellido,sueldo,nacionalidad,fechaNacimiento,nickname,rol,equipo);
    }
    /**
     * Realiza una consulta SQL para seleccionar un equipo específico.
     *
     * @param nombre El nombre del equipo a seleccionar.
     * @return Una lista de los resultados de la consulta.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    public ArrayList selectEquipo(String nombre ) throws Exception {
        return cb.selectEquipo(nombre);

    }
    /**
     * Obtiene una lista de jugadores asociados a un equipo específico.
     *
     * @param x El identificador del equipo.
     * @return Una lista de jugadores asociados al equipo.
     * @throws Exception Si ocurre un error durante la obtención de los jugadores.
     */
    public List<Jugador> llenarJugadores(Integer x) throws Exception {
        return cb.llenarJugadores(x);
    }
    /**
     * Obtiene una lista de todas las competiciones disponibles.
     *
     * @return Una lista de competiciones.
     * @throws Exception Si ocurre un error durante la obtención de las competiciones.
     */
    public List<Competicion> llenarCompeticiones() throws Exception {
        return cb.llenarCompeticiones();
    }
    /**
     * Obtiene una lista de equipos asociados a una competición específica.
     *
     * @param x El identificador de la competición.
     * @return Una lista de equipos asociados a la competición.
     * @throws Exception Si ocurre un error durante la obtención de los equipos.
     */
    public List<Equipo> llenarEquiposCompeticion(Integer x) throws Exception {
        return cb.llenarEquiposCompeticion(x);
    }
    /**
     * Genera el calendario de competiciones.
     *
     * @throws Exception Si ocurre un error durante la generación del calendario.
     */
    public void generarCalendario() throws Exception {
        cb.generarCalendario();
    }
    /**
     * Busca un usuario en la base de datos.
     *
     * @param user El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario encontrado.
     * @throws Exception Si ocurre un error durante la búsqueda del usuario.
     */
    public Usuario buscarUsuario(String user,String password) throws Exception {
        return cb.buscarUsuario(user,password);
    }
    /**
     * Busca un equipo en la base de datos por su nombre.
     *
     * @param nombre El nombre del equipo.
     * @return El equipo encontrado.
     * @throws Exception Si ocurre un error durante la búsqueda del equipo.
     */
    public Equipo buscarEquipo(String nombre) throws Exception {
        return cb.buscarEquipo(nombre);
    }
    /**
     * Elimina una competición de la base de datos.
     *
     * @param c La competición a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación de la competición.
     */
    public void eliminarCompeticion(Competicion c) throws Exception {
        cb.eliminarCompeticion(c);
    }
    /**
     * Busca todos los juegos disponibles.
     *
     * @return Una lista de juegos.
     * @throws SQLException Si ocurre un error durante la búsqueda de los juegos.
     */
    public List<Juego> buscarJuegos() throws SQLException {return cb.buscarJuegos();}
    /**
     * Busca un juego en la base de datos por su nombre.
     *
     * @param nombre El nombre del juego a buscar.
     * @return El juego encontrado.
     * @throws Exception Si ocurre un error durante la búsqueda del juego.
     */
    public Juego buscarJuego(String nombre) throws Exception{return  cb.buscarJuego(nombre);}
    /**
     * Inserta una nueva competición en la base de datos.
     *
     * @param c La competición a insertar.
     * @throws Exception Si ocurre un error durante la inserción de la competición.
     */
    public void insertarCompeticion(Competicion c) throws Exception{cb.insertarCompeticion(c);}
    /**
     * Busca todas las competiciones disponibles.
     *
     * @return Una lista de nombres de competiciones.
     * @throws SQLException Si ocurre un error durante la búsqueda de las competiciones.
     */
    public List<String> buscarCompeticiones() throws SQLException {return cb.buscarCompeticiones();
    }
    /**
     * Obtiene una lista de jugadores asociados a un equipo específico.
     *
     * @param equiposelecionado El nombre del equipo seleccionado.
     * @return Una lista de jugadores asociados al equipo.
     * @throws SQLException Si ocurre un error durante la obtención de los jugadores.
     */
    public List<Jugador> llenarJugadoresNombre(String equiposelecionado) throws SQLException
    {
        return  cb.llenarJugadoresNombre(equiposelecionado);
    }
    /**
     * Busca todos los patrocinadores disponibles.
     *
     * @return Una lista de nombres de patrocinadores.
     * @throws SQLException Si ocurre un error durante la búsqueda de los patrocinadores.
     */
    public List<String> buscarPatrocinador() throws SQLException {
        return cb.buscarPatrocinador();
    }
    /**
     * Obtiene una lista de patrocinadores asociados a un equipo específico.
     *
     * @param equiposeleccionado El nombre del equipo seleccionado.
     * @return Una lista de patrocinadores asociados al equipo.
     * @throws SQLException Si ocurre un error durante la obtención de los patrocinadores.
     */
    public List<Patrocinador> llenarPatrocinadorNombre(String equiposeleccionado) throws SQLException{
        return cb.llenarPatrocinadorNombre(equiposeleccionado);
    }
    /**
     * Elimina un jugador de la base de datos.
     *
     * @param nombre El nombre del jugador a eliminar.
     * @param equipo El nombre del equipo al que pertenece el jugador.
     * @throws Exception Si ocurre un error durante la eliminación del jugador.
     */
    public void eliminarJugador(String nombre,String equipo) throws Exception {
       cb.eliminarJugador(nombre,equipo);
    }
    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param nombre El nombre del usuario.
     * @param contrasena La contraseña del usuario.
     * @param tipoUsuario El tipo de usuario.
     * @return El usuario creado.
     * @throws Exception Si ocurre un error durante la creación del usuario.
     */
    public Usuario crearUsuario(String nombre,String contrasena,String tipoUsuario) throws Exception {
        cb.crearUsuario(nombre,contrasena,tipoUsuario);
        return null;
    }
    /**
     * Actualiza la información de un jugador en la base de datos.
     *
     * @param nombre El nombre del jugador.
     * @param equipo El nombre del equipo al que pertenece el jugador.
     * @return El jugador actualizado.
     * @throws Exception Si ocurre un error durante la actualización del jugador.
     */
    public Jugador actualizarJugador(String nombre, String equipo) throws Exception {
        cb.actualizarJugador( nombre, equipo);
        Jugador buscarDatos = cb.actualizarJugador(nombre, equipo);

        return buscarDatos;
    }
    /**
     * Actualiza la información de un patrocinador en la base de datos.
     *
     * @param nombre El nombre del patrocinador.
     * @param equipo El nombre del equipo al que pertenece el patrocinador.
     * @return El patrocinador actualizado.
     * @throws Exception Si ocurre un error durante la actualización del patrocinador.
     */
    public Patrocinador actualizarPatrocinador(String nombre, String equipo) throws Exception {
        cb.actualizarPatrocinador( nombre, equipo);
        Patrocinador buscarDatos = cb.actualizarPatrocinador(nombre, equipo);

        return buscarDatos;
    }
    /**
     * Confirma la edición de un jugador en la base de datos.
     *
     * @param nombre El nombre del jugador.
     * @param primerApellido El primer apellido del jugador.
     * @param segundoApellido El segundo apellido del jugador.
     * @param sueldo El sueldo del jugador.
     * @param nacionalidad La nacionalidad del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param nickname El apodo del jugador.
     * @param rol El rol del jugador.
     * @param nuevoEquipo El nombre del nuevo equipo al que se asignará el jugador.
     * @param nombreAntiguo El nombre antiguo del jugador.
     * @param equipoAntiguo El nombre antiguo del equipo al que pertenecía el jugador.
     * @throws Exception Si ocurre un error durante la edición del jugador.
     */
    public void editarJugadorConfir(String nombre,String primerApellido,String segundoApellido,double sueldo,String nacionalidad,LocalDate fechaNacimiento,String nickname,String rol,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {


        cb.editarJugadorConfir(nombre, primerApellido, segundoApellido, sueldo, nacionalidad, fechaNacimiento, nickname, rol, nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }
    /**
     * Obtiene una lista de jugadores de acuerdo a un tipo específico.
     *
     * @param tipo El tipo de jugador a buscar.
     * @return Una lista de jugadores del tipo especificado.
     * @throws Exception Si ocurre un error durante la búsqueda de los jugadores.
     */
    public List<Jugador> llenarJugadoresS(String tipo) throws Exception {
        cb.llenarJugadoresS(tipo);
        List<Jugador> listaJugadores = cb.llenarJugadoresS(tipo);
        return listaJugadores;
    }
    /**
     * Elimina un equipo de la base de datos.
     *
     * @param nombre El nombre del equipo a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación del equipo.
     */
    public void borrarEquipo(String nombre) throws SQLException {
        cb.borrarEquipo(nombre);
    }
    /**
     * Obtiene una lista de equipos de acuerdo a un tipo específico.
     *
     * @param tipo El tipo de equipo a buscar.
     * @return Una lista de equipos del tipo especificado.
     * @throws Exception Si ocurre un error durante la obtención de los equipos.
     */
    public List<Equipo> llenarEquiposS(String tipo) throws Exception {
        cb.llenarEquiposS(tipo);
        List<Equipo> listaEquipo = cb.llenarEquiposS(tipo);
        return listaEquipo;
    }
    /**
     * Llena una lista de entrenadores según el tipo especificado.
     *
     * @param tipo El tipo de entrenador a buscar.
     * @return Una lista de objetos Entrenador.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Entrenador> llenarEntrenador(String tipo) throws Exception {
        cb.llenarEntrenador(tipo);
        List<Entrenador> listaEntrenador = cb.llenarEntrenador(tipo);
        return listaEntrenador;
    }
    /**
     * Llena una lista de asistentes según el tipo especificado.
     *
     * @param tipo El tipo de asistente a buscar.
     * @return Una lista de objetos Asistente.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Asistente> llenarAsistente(String tipo) throws Exception {
        cb.llenarAsistente(tipo);
        List<Asistente> listaAsistente = cb.llenarAsistente(tipo);
        return listaAsistente;
    }
    /**
     * Llena una lista de competiciones según el tipo especificado.
     *
     * @param tipo El tipo de competición a buscar.
     * @return Una lista de objetos Competicion.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Competicion> llenarCompeticion(String tipo) throws Exception {
        cb.llenarCompeticion(tipo);
        List<Competicion> listaCompeticion = cb.llenarCompeticion(tipo);
        return listaCompeticion;
    }
    /**
     * Llena una lista de juegos según el tipo especificado.
     *
     * @param tipo El tipo de juego a buscar.
     * @return Una lista de objetos Juego.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Juego> llenarJuegos(String tipo) throws Exception {
        cb.llenarJuegos(tipo);
        List<Juego> listaJuego = cb.llenarJuegos(tipo);
        return listaJuego;
    }
    /**
     * Crea un equipo con los detalles proporcionados.
     *
     * @param nombre El nombre del equipo.
     * @param fecha La fecha de fundación del equipo.
     * @param patrocinador El patrocinador del equipo.
     * @param competicion La competición a la que pertenece el equipo.
     * @throws Exception Si ocurre un error durante la creación del equipo.
     */
    public void crearEquipo(String nombre, LocalDate fecha, Patrocinador patrocinador, Competicion competicion) throws Exception {
        cb.crearEquipo(nombre, fecha, patrocinador,competicion);
    }
    /**
     * Edita un equipo con los detalles proporcionados.
     *
     * @param nombreAntiguo El nombre antiguo del equipo.
     * @param nombreNuevo El nuevo nombre del equipo.
     * @param fechacambio La nueva fecha de fundación del equipo.
     * @param VincularNuevo El nombre de la nueva competición a la que se vinculará el equipo.
     * @param Desvincular El nombre de la competición de la que se desvinculará el equipo.
     * @throws Exception Si ocurre un error durante la edición del equipo.
     */
    public void editarEquipo(String nombreAntiguo,String nombreNuevo,LocalDate fechacambio,String VincularNuevo,String Desvincular) throws Exception {
        cb.editarEquipo(nombreAntiguo,nombreNuevo,fechacambio,VincularNuevo,Desvincular);
    }
    /**
     * Edita un patrocinador con los detalles proporcionados.
     *
     * @param nombre El nuevo nombre del patrocinador.
     * @param nuevoEquipo El nuevo equipo asociado al patrocinador.
     * @param nombreAntiguo El nombre antiguo del patrocinador.
     * @param equipoAntiguo El equipo antiguo asociado al patrocinador.
     * @throws Exception Si ocurre un error durante la edición del patrocinador.
     */
    public void editarPatrocinadorConfir(String nombre,String nuevoEquipo,String nombreAntiguo,String equipoAntiguo) throws Exception {


        cb.editarPatrocinadorConfir(nombre,nuevoEquipo,nombreAntiguo,equipoAntiguo);
    }


    /**
     * Busca una competición por su nombre.
     *
     * @param c El nombre de la competición a buscar.
     * @return La competición encontrada, o null si no se encuentra ninguna.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */

    public Competicion buscarCompeticion(String c) throws Exception {
        return cb.buscarCompeticion(c);
    }
    /**
     * Busca las jornadas de una competición.
     *
     * @param j El identificador de la competición.
     * @return Una lista de números de jornadas.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Integer> buscarJornadas(Integer j) throws Exception {
        return cb.buscarJornadas(j);
    }
    /**
     * Busca los enfrentamientos de una competición y una jornada específicas.
     *
     * @param j El identificador de la jornada.
     * @param c El identificador de la competición.
     * @return Una lista de objetos Enfrentamiento.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public List<Enfrentamiento> buscarEnfrentamientos(Integer j, Integer c) throws Exception {
        return cb.buscarEnfrentamientos(j,c);
    }
    /**
     * Guarda los resultados de un enfrentamiento.
     *
     * @param idEnfJor El identificador del enfrentamiento en la jornada.
     * @param resultLocal El resultado del equipo local.
     * @param resultVisitante El resultado del equipo visitante.
     * @throws Exception Si ocurre un error al guardar los resultados.
     */
    public void guardarResultados(Integer idEnfJor, int resultLocal, int resultVisitante) throws Exception {
        cb.guardarResultados(idEnfJor, resultLocal, resultVisitante);
    }
    /**
     * Actualiza la tabla de clasificación según los resultados de un enfrentamiento.
     *
     * @param enfrentamiento El enfrentamiento del que se obtienen los resultados.
     * @param resultLocal El resultado del equipo local.
     * @param resultVisitante El resultado del equipo visitante.
     * @throws Exception Si ocurre un error al actualizar la tabla de clasificación.
     */
    public void actualizarTablaClasificacion(Enfrentamiento enfrentamiento, int resultLocal, int resultVisitante) throws Exception {
        cb.actualizarTablaClasificacion(enfrentamiento,resultLocal, resultVisitante );
    }
    /**
     * Llena los campos de resultados de un enfrentamiento.
     *
     * @param resultadoLocal El campo de resultado del equipo local.
     * @param resultadoVisitante El campo de resultado del equipo visitante.
     * @param idEnfJor El identificador del enfrentamiento en la jornada.
     */
    public void llenarResultados(JTextField resultadoLocal, JTextField resultadoVisitante, Integer idEnfJor) {
        cb.llenarResultados(resultadoLocal, resultadoVisitante, idEnfJor);
    }
    /**
     * Obtiene las clasificaciones de una competición específica.
     *
     * @param idCompeticion El identificador de la competición.
     * @return Una lista de objetos Clasificacion.
     * @throws Exception Si ocurre un error al obtener las clasificaciones.
     */
    public List<Clasificacion> obtenerClasificacionesPorCompeticion(Integer idCompeticion) throws Exception {
        return cb.obtenerClasificacionesPorCompeticion(idCompeticion);
    }
    /**
     * Busca la última jornada de una competición.
     *
     * @param competicionID El identificador de la competición.
     * @return El número de la última jornada.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public Integer buscarUltimaJornada(Integer competicionID) throws Exception {
        return cb.buscarUltimaJornada(competicionID);
    }
    /**
     * Actualiza la información de un equipo jugador.
     *
     * @param nombre El nombre del equipo jugador.
     * @param patrocinador El nombre del patrocinador.
     * @param competicion El nombre de la competición.
     * @param fecha La fecha de la competición.
     * @throws Exception Si ocurre un error al actualizar la información.
     */
    public void updateEquipoJugador(String nombre, String patrocinador, String competicion, LocalDate fecha) throws Exception {
        cb.updateEquipoJugador(nombre, patrocinador, competicion, fecha);
    }
    /**
     * Busca un patrocinador por su nombre.
     *
     * @param nombrePatrocinador El nombre del patrocinador a buscar.
     * @return El objeto Patrocinador encontrado.
     */
    public Patrocinador buscarPatrocinadorNombre(String nombrePatrocinador) {
        return  cb.buscarPatrocinadorNombre(nombrePatrocinador);
    }
    /**
     * Busca el identificador de un juego por su nombre.
     *
     * @param nuevoJuego El nombre del juego.
     * @return El identificador del juego.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    public int buscarIdJuegoPorNombre(String nuevoJuego) throws Exception {
        return cb.buscarIdJuegoPorNombre(nuevoJuego);
    }
    /**
     * Modifica los detalles de una competición existente.
     *
     * @param idCompeticion El identificador de la competición a modificar.
     * @param nuevoNombre El nuevo nombre de la competición.
     * @param date La nueva fecha de inicio de la competición.
     * @param date1 La nueva fecha de fin de la competición.
     * @param nuevoEstado El nuevo estado de la competición.
     * @param idJuego El identificador del juego asociado a la competición.
     * @throws Exception Si ocurre un error durante la modificación de la competición.
     */
    public void modificarCompeticion(Integer idCompeticion, String nuevoNombre, Date date, Date date1, String nuevoEstado, int idJuego) throws Exception {
        cb.modificarCompeticion(idCompeticion,nuevoNombre, date, date1, nuevoEstado, idJuego);
    }
    /**
     * Modifica los detalles de un juego existente.
     *
     * @param j El objeto Juego con los nuevos detalles.
     * @throws Exception Si ocurre un error durante la modificación del juego.
     */
    public void modificarJuego(Juego j)throws Exception  {
        cb.modificarJuego(j);
    }
    /**
     * Elimina un juego de la base de datos.
     *
     * @param j El objeto Juego a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación del juego.
     */
    public void eliminarJuego(Juego j)throws Exception  {
        cb.eliminarJuego(j);
    }
    /**
     * Inserta un nuevo juego en la base de datos.
     *
     * @param j El objeto Juego a insertar.
     * @throws Exception Si ocurre un error durante la inserción del juego.
     */
    public void insertarJuego(Juego j)throws Exception  {
        cb.insertarJuego(j);
    }
    /**
     * Busca todas las competiciones asociadas a un juego específico.
     *
     * @param idJuego El identificador del juego.
     * @return Una lista de objetos Competicion asociadas al juego.
     * @throws Exception Si ocurre un error durante la búsqueda de las competiciones.
     */
    public List<Competicion> buscarCompeticionesPorJuego(Integer idJuego) throws Exception {
        return cb.buscarCompeticionesPorJuego(idJuego);
    }
    /**
     * Vincula un juego a una competición.
     *
     * @param idJuego El identificador del juego.
     * @param idCompeticion El identificador de la competición.
     * @throws Exception Si ocurre un error durante la vinculación del juego a la competición.
     */
    public void vincularJuegoACompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cb.vincularJuegoACompeticion(idJuego, idCompeticion);
    }
    /**
     * Desvincula un juego de una competición.
     *
     * @param idJuego El identificador del juego.
     * @param idCompeticion El identificador de la competición.
     * @throws Exception Si ocurre un error durante la desvinculación del juego de la competición.
     */
    public void desvincularJuegoDeCompeticion(Integer idJuego, Integer idCompeticion) throws Exception{
        cb.desvincularJuegoDeCompeticion(idJuego, idCompeticion);
    }
    /**
     * Inserta un nuevo patrocinador en la base de datos.
     *
     * @param p El objeto Patrocinador a insertar.
     * @throws Exception Si ocurre un error durante la inserción del patrocinador.
     */
    public void insertarPatrocinador(Patrocinador p) throws Exception {cb.insertarPatrocinador(p);}
    /**
     * Elimina un patrocinador de la base de datos.
     *
     * @param nombre El nombre del patrocinador a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación del patrocinador.
     */
    public void borrarPatrocinador(String nombre) throws Exception{cb.borrarPatrocinador(nombre);}
    /**
     * Edita los detalles de un patrocinador existente en la base de datos.
     *
     * @param nombreNuevo El nuevo nombre del patrocinador.
     * @throws Exception Si ocurre un error durante la edición del patrocinador.
     */
    public void editarPatrocinador(String nombreNuevo) throws Exception{cb.editarPatrocinador(nombreNuevo);}
    /**
     * Busca un patrocinador por su nombre.
     *
     * @param nombre El nombre del patrocinador a buscar.
     * @return El objeto Patrocinador correspondiente al nombre especificado.
     */
    public Patrocinador buscarPatrocinadorEliminar(String nombre) throws Exception{return cb.buscarPatrocinadorEliminar(nombre);}
    /**
     * Crea un nuevo asistente y lo inserta en la base de datos.
     *
     * @param nombre El nombre del asistente.
     * @param apellido1 El primer apellido del asistente.
     * @param apellido2 El segundo apellido del asistente.
     * @param sueldo El sueldo del asistente.
     * @param tipo El tipo de asistente.
     * @throws Exception Si ocurre un error durante la creación del asistente.
     */
    public void crearAsistente(String nombre,String apellido1,String apellido2,Integer sueldo,String tipo) throws Exception {
        cb.crearAsistente(nombre, apellido1, apellido2, sueldo, tipo);
    }
    /**
     * Crea un nuevo entrenador y lo inserta en la base de datos.
     *
     * @param nombre El nombre del entrenador.
     * @param apellido1 El primer apellido del entrenador.
     * @param apellido2 El segundo apellido del entrenador.
     * @param sueldo El sueldo del entrenador.
     * @param tipo El tipo de entrenador.
     * @throws Exception Si ocurre un error durante la creación del entrenador.
     */
    public void crearEntrenador(String nombre,String apellido1,String apellido2,Integer sueldo,String tipo) throws Exception {
        cb.crearEntrenador(nombre, apellido1, apellido2, sueldo, tipo);
    }
    /**
     * Obtiene una lista de nombres de asistentes asociados a un equipo específico.
     *
     * @param nombreEquipo El nombre del equipo.
     * @param equipo El tipo de equipo.
     * @return Una lista de nombres de asistentes asociados al equipo.
     * @throws Exception Si ocurre un error durante la obtención de los asistentes.
     */
    public ArrayList<String> obtenerAsistentesPorEquipo(String nombreEquipo, String equipo) throws Exception {
        return cb.obtenerAsistentesPorEquipo(nombreEquipo, equipo);
    }
    /**
     * Elimina un asistente de un equipo específico.
     *
     * @param nombreAsistente El nombre del asistente a eliminar.
     * @param nombreEquipo El nombre del equipo del que se eliminará el asistente.
     * @throws Exception Si ocurre un error durante la eliminación del asistente.
     */
    public void borrarAsistente(String nombreAsistente,String nombreEquipo) throws Exception {
        cb.borrarAsistente(nombreAsistente,nombreEquipo);
    }
}
