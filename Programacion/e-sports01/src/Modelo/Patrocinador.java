package Modelo;

import java.util.List;

public class Patrocinador {
    private Integer idPatrocinador;
    private String nombre;
    private List<Equipo> listaEquipo;


    public Patrocinador(Integer idPatrocinador, String nombre, List<Equipo> listaEquipo) {
        this.idPatrocinador = idPatrocinador;
        this.nombre = nombre;
        this.listaEquipo = listaEquipo;
    }

    public Patrocinador() {

    }

    public Integer getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(Integer idPatrocinador) {
        this.idPatrocinador = idPatrocinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
}
