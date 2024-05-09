package Modelo;

public class Juego {
    public  String nombre;
    public  String empresa;
    public String lanzazmiento;
    public Integer idJuego;

    public Juego(String nombre, String empresa, String lanzazmiento, Integer idJuego) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.lanzazmiento = lanzazmiento;
        this.idJuego = idJuego;
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

    public String getLanzazmiento() {
        return lanzazmiento;
    }

    public void setLanzazmiento(String lanzazmiento) {
        this.lanzazmiento = lanzazmiento;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }
}
