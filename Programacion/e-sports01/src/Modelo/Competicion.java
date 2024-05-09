package Modelo;

public class Competicion {
    public Competicion(String fechaInicio, String fechaFin, boolean estado, Integer idCompeticion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idCompeticion = idCompeticion;
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

    private String fechaInicio;
    private  String fechaFin;
    private boolean estado;
    private Integer idCompeticion;

}
