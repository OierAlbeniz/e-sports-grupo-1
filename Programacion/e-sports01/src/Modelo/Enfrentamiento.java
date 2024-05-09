package Modelo;

public class Enfrentamiento {

    private Equipo equipoUno;
    private Equipo equipoDos;
    private Integer idEnfrentamiento;
    private Integer hora;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private Jornada jornada;

    public Enfrentamiento(Equipo equipoUno, Equipo equipoDos, Integer idEnfrentamiento, Integer hora, Integer puntosLocal, Integer puntosVisitante, Jornada jornada) {
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.idEnfrentamiento = idEnfrentamiento;
        this.hora = hora;
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
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

    private Integer getHora() {
        return hora;
    }

    private void setHora(Integer hora) {
        this.hora = hora;
    }

    private Integer getPuntosLocal() {
        return puntosLocal;
    }

    private void setPuntosLocal(Integer puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    private Integer getPuntosVisitante() {
        return puntosVisitante;
    }

    private void setPuntosVisitante(Integer puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
