package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaEquipo {
    private Connection con;
    public ControladorTablaEquipo(Connection con) {
        this.con = con;
    }
}
