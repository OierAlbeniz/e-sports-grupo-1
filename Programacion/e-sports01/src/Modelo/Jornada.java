package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Jornada {

    private String idJornada;
    private LocalDate fechaEnfrentamiento;
    private List<Enfrentamiento> listaEnfrentamiento;
    private Competicion competicion;

    public Jornada(String idJornada, LocalDate fechaEnfrentamiento, Competicion competicion) {
        this.idJornada = idJornada;
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        this.competicion = competicion;
    }

    public LocalDate getFechaEnfrentamiento() {
        return fechaEnfrentamiento;
    }

    public void setFechaEnfrentamiento(LocalDate fechaEnfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    public String getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(String idJornada) {
        this.idJornada = idJornada;
    }

    public List<Enfrentamiento> getListaEnfrentamiento() {
        return listaEnfrentamiento;
    }

    public void setListaEnfrentamiento(List<Enfrentamiento> listaEnfrentamiento) {
        this.listaEnfrentamiento = listaEnfrentamiento;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }
}
