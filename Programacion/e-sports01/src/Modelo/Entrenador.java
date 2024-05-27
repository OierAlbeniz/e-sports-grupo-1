package Modelo;

import java.awt.*;
/**
 * La clase Entrenador representa al miembro del equipo responsable de dirigir y entrenar a los jugadores.
 */
public class Entrenador {
    private Integer idIntegrante;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Double sueldo;
    private Equipo equipo;
    /**
     * Constructor de la clase Entrenador que inicializa todos los campos.
     *
     * @param idIntegrante El identificador del entrenador.
     * @param nombre       El nombre del entrenador.
     * @param apellido1    El primer apellido del entrenador.
     * @param apellido2    El segundo apellido del entrenador.
     * @param sueldo       El sueldo del entrenador.
     * @param equipo       El equipo al que pertenece el entrenador.
     */
    public Entrenador(Integer idIntegrante, String nombre, String apellido1, String apellido2, Double sueldo, Equipo equipo) {
        this.idIntegrante = idIntegrante;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }

    public Entrenador() {

    }

    public Integer getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(Integer idIntegrante) {
        this.idIntegrante = idIntegrante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
