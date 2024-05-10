package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaPatrocinador {
    private Connection con;
    public ControladorTablaPatrocinador(Connection con) {
        this.con = con;
    }
}
