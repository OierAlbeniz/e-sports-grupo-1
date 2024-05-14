package Modelo;

import java.time.LocalTime;

public class Enfrentamiento {

    private Equipo equipoUno;
    private Equipo equipoDos;
    private Integer idEnfrentamiento;
    private LocalTime hora;
    private Integer resultadoLocal;
    private Integer resultadoVisitante;
    private Jornada jornada;

    public Enfrentamiento(Equipo equipoUno, Equipo equipoDos, Integer idEnfrentamiento, LocalTime hora, Integer resultadoLocal, Integer resultadoVisitante, Jornada jornada) {
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.idEnfrentamiento = idEnfrentamiento;
        this.hora = hora;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
        this.jornada = jornada;
    }

    public Equipo getEquipoUno() {
        return equipoUno;
    }

    public void setEquipoUno(Equipo equipoUno) {
        this.equipoUno = equipoUno;
    }

    public Equipo getEquipoDos() {
        return equipoDos;
    }

    public void setEquipoDos(Equipo equipoDos) {
        this.equipoDos = equipoDos;
    }

    private Integer getIdEnfrentamiento() {
        return idEnfrentamiento;
    }

    private void setIdEnfrentamiento(Integer idEnfrentamiento) {
        this.idEnfrentamiento = idEnfrentamiento;
    }

    private LocalTime getHora() {
        return hora;
    }

    private void setHora(LocalTime hora) {
        this.hora = hora;
    }

    private Integer getResultadoLocal() {
        return resultadoLocal;
    }

    private void setResultadoLocal(Integer resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    private Integer getResultadoVisitante() {
        return resultadoVisitante;
    }

    private void setResultadoVisitante(Integer resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
