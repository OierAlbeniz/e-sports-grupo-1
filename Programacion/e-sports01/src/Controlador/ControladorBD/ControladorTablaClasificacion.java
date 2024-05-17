package Controlador.ControladorBD;

import Modelo.Equipo;
import Modelo.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona las operaciones relacionadas con la tabla de clasificación en la base de datos.
 * Permite obtener los equipos que participan en una competición específica.
 *
 * @autor Grupo4
 * @see Equipo
 * @see Jugador
 */
public class ControladorTablaClasificacion {
    private Connection con;

    /**
     * Constructor de la clase ControladorTablaClasificacion.
     *
     * @param con La conexión a la base de datos.
     */
    public ControladorTablaClasificacion(Connection con) {
        this.con = con;
    }

    /**
     * Llena una lista con los IDs de los equipos que participan en una competición específica.
     *
     * @param competicion El ID de la competición.
     * @return Una lista de IDs de equipos que participan en la competición.
     * @throws Exception Si ocurre un error durante la ejecución de la consulta.
     */
    public List<String> llenarEquiposCompeticion(Integer competicion) throws Exception {
        List<String> listaEquipos = new ArrayList<>();
        String plantilla5 = "SELECT id_equipo FROM clasificacion WHERE id_competicion=?";

        PreparedStatement statement = con.prepareStatement(plantilla5);
        statement.setInt(1, competicion);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            listaEquipos.add(rs.getString("id_equipo"));
            System.out.println(rs.getString("id_equipo"));
        }

        statement.close();
        return listaEquipos;
    }
}
