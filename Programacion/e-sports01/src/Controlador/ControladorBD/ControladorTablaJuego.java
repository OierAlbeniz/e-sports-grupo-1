package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaJuego {
    private Connection con;
    public ControladorTablaJuego(Connection con) {
        this.con = con;
    }
}
