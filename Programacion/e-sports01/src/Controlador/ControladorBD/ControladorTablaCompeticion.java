package Controlador.ControladorBD;

import Modelo.Competicion;
import Modelo.Juego;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class ControladorTablaCompeticion {
    private Connection con;
    private ControladorTablaJuego ctj;
    private Competicion c;
    private List<Competicion> listaCompeticiones;
    private List<String> listaNombreCompeticiones;
    public ControladorTablaCompeticion(Connection con) {
        this.con = con;
    }
}
*/
}
