package Modelo;

<<<<<<< Updated upstream
import java.util.List;

public class Competicion {
=======
import java.util.ArrayList;

public class Competicion {

>>>>>>> Stashed changes
    private String fechaInicio;
    private  String fechaFin;
    private boolean estado;
    private Integer idCompeticion;
<<<<<<< Updated upstream
    private List<Jornada> listaJornada;
    private Juego juego;

    public Competicion(String fechaInicio, String fechaFin, boolean estado, Integer idCompeticion, Juego juego) {
=======

    public Competicion(String fechaInicio, String fechaFin, boolean estado, Integer idCompeticion) {
>>>>>>> Stashed changes
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idCompeticion = idCompeticion;
        this.juego = juego;
    }

    private String getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    private String getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    private boolean isEstado() {
        return estado;
    }

    private void setEstado(boolean estado) {
        this.estado = estado;
    }

    private Integer getIdCompeticion() {
        return idCompeticion;
    }

    private void setIdCompeticion(Integer idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

<<<<<<< Updated upstream
    public List<Jornada> getListaJornada() {
        return listaJornada;
    }

    public void setListaJornada(List<Jornada> listaJornada) {
        this.listaJornada = listaJornada;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
=======
        
>>>>>>> Stashed changes
}
