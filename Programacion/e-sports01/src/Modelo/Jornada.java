package Modelo;

import java.util.List;

public class Jornada {

    private String fechaEnfrentamiento;
    private Integer idJornada;
    private List<Enfrentamiento> listaEnfrentamiento;
    private Competicion competicion;

    public Jornada(String fechaEnfrentamiento, String idJornada, Competicion competicion) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        this.idJornada = Integer.valueOf(idJornada);
        this.competicion = competicion;
    }

    public String getFechaEnfrentamiento() {
        return fechaEnfrentamiento;
    }

    public void setFechaEnfrentamiento(String fechaEnfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(String idJornada) {
        this.idJornada = Integer.valueOf(idJornada);
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
