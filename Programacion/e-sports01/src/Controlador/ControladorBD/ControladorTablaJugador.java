package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaJugador {
    private Connection con;
    public ControladorTablaJugador(Connection con) {
        this.con = con;
    }
}