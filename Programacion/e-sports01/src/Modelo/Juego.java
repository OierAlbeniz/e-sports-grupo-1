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

    private String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String getEmpresa() {
        return empresa;
    }

    private void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    private Integer getIdJuego() {
        return idJuego;
    }

    private void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }


}
