package Modelo;

import java.util.List;

public class ClasificacionCompleta {
    private List<Integer> listaIdClasificacion;
    private List<Equipo> listaEquipos;
    private List<Integer> listaPuntos;

    public ClasificacionCompleta(List<Integer> listaIdClasificacion, List<Equipo> listaEquipos, List<Integer> listaPuntos) {
        this.listaIdClasificacion = listaIdClasificacion;
        this.listaEquipos = listaEquipos;
        this.listaPuntos = listaPuntos;
    }

    public ClasificacionCompleta() {
    }

    public List<Integer> getListaIdClasificacion() {
        return listaIdClasificacion;
    }

    public void setListaIdClasificacion(List<Integer> listaIdClasificacion) {
        this.listaIdClasificacion = listaIdClasificacion;
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public List<Integer> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<Integer> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }
}
