package Modelo;

import java.util.List;

/**
 * Clase que representa un Patrocinador.
 */
public class Patrocinador {
    private Integer idPatrocinador;
    private String nombre;
    private List<Equipo> listaEquipo;

    /**
     * Constructor completo para la clase Patrocinador.
     *
     * @param idPatrocinador Identificador único del patrocinador.
     * @param nombre Nombre del patrocinador.
     * @param listaEquipo Lista de equipos patrocinados.
     */
    public Patrocinador(final Integer idPatrocinador, final String nombre, final List<Equipo> listaEquipo) {
        this.idPatrocinador = idPatrocinador;
        this.nombre = nombre;
        this.listaEquipo = listaEquipo;
    }

    /**
     * Constructor vacío para la clase Patrocinador.
     */
    public Patrocinador() {
    }

    public Patrocinador(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(Integer idPatrocinador) {
        if (idPatrocinador != null && idPatrocinador >= 0) {
            this.idPatrocinador = idPatrocinador;
        } else {
            throw new IllegalArgumentException("El id del patrocinador debe ser un número positivo.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    @Override
    public String toString() {
        return "Patrocinador{" +
                "idPatrocinador=" + idPatrocinador +
                ", nombre='" + nombre + '\'' +
                ", listaEquipo=" + listaEquipo +
                '}';
    }
}
