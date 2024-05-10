package Controlador.ControladorBD;

import java.sql.Connection;

public class ControladorTablaUsuario {
    private Connection con;
    public ControladorTablaUsuario(Connection con) {
        this.con = con;
    }
}
