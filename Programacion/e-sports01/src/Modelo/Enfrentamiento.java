package Modelo;

public class Enfrentamiento {

    private String equipoUno;
    private String equipoDos;
    private Integer idEnfrentamiento;
    private Integer hora;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private Integer idJorComp;

    private Enfrentamiento(String equipoUno, String equipoDos, Integer idEnfrentamiento, Integer hora, Integer puntosLocal, Integer puntosVisitante, Integer idJorComp) {
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.idEnfrentamiento = idEnfrentamiento;
        this.hora = hora;
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
        this.idJorComp = idJorComp;
    }

    private String getEquipoUno() {
        return equipoUno;
    }

    private void setEquipoUno(String equipoUno) {
        this.equipoUno = equipoUno;
    }

    private String getEquipoDos() {
        return equipoDos;
    }

    private void setEquipoDos(String equipoDos) {
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

    private Integer getIdJorComp() {
        return idJorComp;
    }

    private void setIdJorComp(Integer idJorComp) {
        this.idJorComp = idJorComp;
    }
}
