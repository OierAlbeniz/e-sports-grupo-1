package Modelo;

public class Juego {
    private  String nombre;
    private  String empresa;
    private String lanzazmiento;
    private Integer idJuego;

    private Juego(String nombre, String empresa, String lanzazmiento, Integer idJuego) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.lanzazmiento = lanzazmiento;
        this.idJuego = idJuego;
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

    private String getLanzazmiento() {
        return lanzazmiento;
    }

    private void setLanzazmiento(String lanzazmiento) {
        this.lanzazmiento = lanzazmiento;
    }

    private Integer getIdJuego() {
        return idJuego;
    }

    private void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }
}
