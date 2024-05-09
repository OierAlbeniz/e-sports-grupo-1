package Modelo;

public class Jornada {

    private String fechaEnfrentamiento;
    private String Enfrentamiento;

    private Jornada(String fechaEnfrentamiento, String enfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        Enfrentamiento = enfrentamiento;
    }

    private String getFechaEnfrentamiento() {
        return fechaEnfrentamiento;
    }

    private void setFechaEnfrentamiento(String fechaEnfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    private String getEnfrentamiento() {
        return Enfrentamiento;
    }

    private void setEnfrentamiento(String enfrentamiento) {
        Enfrentamiento = enfrentamiento;
    }
}
