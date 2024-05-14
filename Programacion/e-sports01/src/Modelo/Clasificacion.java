package Modelo;

import javax.swing.*;
import java.util.List;

public class Clasificacion {
    private String idClasificacion;
    private List<String> listaPuntos;
    private Competicion competicion;
    private List<Equipo> listaEquipo;

    public Clasificacion(String idClasificacion, List<String> listaPuntos, Competicion competicion, List<Equipo> listaEquipo) {
        this.idClasificacion = idClasificacion;
        this.listaPuntos = listaPuntos;
        this.competicion = competicion;
        this.listaEquipo = listaEquipo;
    }

    public String getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(String idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public List<String> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<String> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
}
