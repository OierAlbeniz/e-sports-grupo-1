package Modelo;

import java.util.List;

public class Patrocinador {
    private String idPatrocinador;
    private String nombre;
    private List<Equipo> listaEquipo;


    public Patrocinador(String idPatrocinador, String nombre, List<Equipo> listaEquipo) {
        this.idPatrocinador = idPatrocinador;
        this.nombre = nombre;
        this.listaEquipo = listaEquipo;
    }

    public String getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(String idPatrocinador) {
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
