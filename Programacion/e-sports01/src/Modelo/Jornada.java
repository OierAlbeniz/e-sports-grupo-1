package Modelo;

public class Jornada {

    public String fechaEnfrentamiento;
    public String Enfrentamiento;

    public Jornada(String fechaEnfrentamiento, String enfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        Enfrentamiento = enfrentamiento;
    }

    public String getFechaEnfrentamiento() {
        return fechaEnfrentamiento;
    }

    public void setFechaEnfrentamiento(String fechaEnfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    public String getEnfrentamiento() {
        return Enfrentamiento;
    }

    public void setEnfrentamiento(String enfrentamiento) {
        Enfrentamiento = enfrentamiento;
    }
}
