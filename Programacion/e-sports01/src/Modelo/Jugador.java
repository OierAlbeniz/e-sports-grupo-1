package Modelo;

import java.time.LocalDate;
/**
 * La clase Jugador representa a un jugador de un equipo deportivo.
 */

public class Jugador {
    private Integer idIntegrante;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Double sueldo;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String nickname;
    private String rol;
    private String equipo;
    /**
     * Constructor de la clase Jugador.
     *
     * @param idIntegrante    El identificador del jugador.
     * @param nombre          El nombre del jugador.
     * @param apellido1       El primer apellido del jugador.
     * @param apellido2       El segundo apellido del jugador.
     * @param sueldo          El sueldo del jugador.
     * @param nacionalidad    La nacionalidad del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param nickname        El apodo o nickname del jugador.
     * @param rol             El rol o posición del jugador en el equipo.
     * @param equipo          El equipo al que pertenece el jugador.
     */
    public Jugador(Integer idIntegrante, String nombre, String apellido1, String apellido2, Double sueldo, String nacionalidad, LocalDate fechaNacimiento, String nickname, String rol, String equipo) {
        this.idIntegrante = idIntegrante;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sueldo = sueldo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.nickname = nickname;
        this.rol = rol;
        this.equipo = equipo;
    }

    public Jugador() {

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

    public  void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public  void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public  void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public  void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public  void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public  void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNickname() {
        return nickname;
    }

    public  void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRol() {
        return rol;
    }

    public  void setRol(String rol) {
        this.rol = rol;
    }

    public String getEquipo() {
        return equipo;
    }

    public  void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}

