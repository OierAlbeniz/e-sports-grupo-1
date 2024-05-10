package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Equipo {
    private String idEquipo;
    private String nombre;
    private LocalDate fechaFundacion;
    private List<Jugador> listaJugador;
    private Patrocinador patrocinador;
    private List<Entrenador> listaEntrenador;
    private List<Clasificacion> listaClasificacion;

    public Equipo(String idEquipo, String nombre, LocalDate fechaFundacion, Patrocinador patrocinador) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.patrocinador = patrocinador;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
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
