package Controlador.ControladorBD;

import java.sql.Connection;

/**
 * La clase ControladorTablaAsistente gestiona las operaciones relacionadas con la tabla de asistentes en la base de datos.
 */
public class ControladorTablaAsistente {
    private Connection con;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param con La conexión a la base de datos.
     */
    public ControladorTablaAsistente(Connection con) {
        this.con = con;
    }
}
