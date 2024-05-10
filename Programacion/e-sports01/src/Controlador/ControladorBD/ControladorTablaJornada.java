package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaJornada {
    private Connection con;
    public ControladorTablaJornada(Connection con) {
        this.con = con;
    }
}
