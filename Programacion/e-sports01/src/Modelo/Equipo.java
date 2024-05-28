package Modelo;

import java.time.LocalDate;
import java.util.List;
/**
 * La clase Equipo representa a un equipo deportivo.
 */
public class Equipo {
    private Integer idEquipo;
    private String nombre;
    private LocalDate fechaFundacion;
    private List<Jugador> listaJugador;
    private Patrocinador patrocinador;
    private Asistente asistente;
    private List<Entrenador> listaEntrenador;
    private List<Clasificacion> listaClasificacion;
    /**
     * Constructor de la clase Equipo que inicializa algunos campos.
     *
     * @param idEquipo       El identificador del equipo.
     * @param nombre         El nombre del equipo.
     * @param fechaFundacion La fecha de fundación del equipo.
     * @param patrocinador   El patrocinador del equipo.
     */

    public Equipo(Integer idEquipo, String nombre, LocalDate fechaFundacion, Patrocinador patrocinador) {

        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.patrocinador = patrocinador;
    }
    /**
     * Constructor de la clase Equipo que inicializa algunos campos.
     *
     * @param idEquipo       El identificador del equipo.
     * @param nombre         El nombre del equipo.
     * @param fechaFundacion La fecha de fundación del equipo.
     */
    public Equipo(Integer idEquipo, String nombre, LocalDate fechaFundacion ) {

        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
    }

    /**
     * Constructor de la clase Equipo que inicializa todos los campos.
     *
     * @param idEquipo           El identificador del equipo.
     * @param nombre             El nombre del equipo.
     * @param fechaFundacion     La fecha de fundación del equipo.
     * @param listaJugador       La lista de jugadores del equipo.
     * @param patrocinador       El patrocinador del equipo.
     * @param asistente          El asistente del equipo.
     * @param listaEntrenador    La lista de entrenadores del equipo.
     * @param listaClasificacion La lista de clasificaciones del equipo.
     */
    public Equipo(Integer idEquipo, String nombre, LocalDate fechaFundacion, List<Jugador> listaJugador, Patrocinador patrocinador, Asistente asistente, List<Entrenador> listaEntrenador, List<Clasificacion> listaClasificacion) {

        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.listaJugador = listaJugador;
        this.patrocinador = patrocinador;
        this.asistente = asistente;
        this.listaEntrenador = listaEntrenador;
        this.listaClasificacion = listaClasificacion;
    }

    public Equipo() {

    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public List<Jugador> getListaJugador() {
        return listaJugador;
    }

    public void setListaJugador(List<Jugador> listaJugador) {
        this.listaJugador = listaJugador;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public List<Entrenador> getListaEntrenador() {
        return listaEntrenador;
    }

    public void setListaEntrenador(List<Entrenador> listaEntrenador) {
        this.listaEntrenador = listaEntrenador;
    }

    public List<Clasificacion> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(List<Clasificacion> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }
}
