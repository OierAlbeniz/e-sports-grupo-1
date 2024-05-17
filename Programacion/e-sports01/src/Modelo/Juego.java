package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Juego {
    private  String nombre;
    private  String empresa;
    private LocalDate fechalanzamiento;
    private Integer idJuego;
    private List<Competicion> listaCompeticion;


    public Juego(String nombre, String empresa, LocalDate fechalanzamiento, Integer idJuego) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.fechalanzamiento = fechalanzamiento;
        this.idJuego = idJuego;
        this.listaCompeticion = listaCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }


}
