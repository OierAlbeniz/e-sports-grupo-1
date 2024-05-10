package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaCompeticion {
    private Connection con;
    public ControladorTablaCompeticion(Connection con) {
        this.con = con;
    }
}
