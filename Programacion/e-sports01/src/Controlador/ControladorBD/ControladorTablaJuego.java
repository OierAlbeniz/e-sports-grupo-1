package Controlador.ControladorBD;

import Modelo.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ControladorTablaJuego {
    private Connection con;
    private Juego j;
    private List<Juego> listaJuegos;
    public ControladorTablaJuego(Connection con) {
        this.con = con;
    }

    public Juego buscarJuego(String nombre) throws Exception{

        String plantilla = "SELECT * FROM juego WHERE nombre = ?";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        sentencia.setString(1,nombre);

        ResultSet resultado = sentencia.executeQuery();

        if(resultado.next()){
            LocalDate fecha = LocalDate.parse(resultado.getString("fecha_lanzamiento"));
            j = new Juego(nombre,resultado.getString("empresa"),fecha,resultado.getInt("id_juego"));
            System.out.println("Vuelo encontrado.");
            System.out.println(j.toString());
        }
        else {
            throw new Exception("Error al buscar el vuelo");
        }
        sentencia.close();
        return j;
    }

    public List<Juego> buscarJuegos() throws SQLException {
        listaJuegos = new ArrayList<>();
        String plantilla ="SELECT * from juego";

        PreparedStatement sentencia = con.prepareStatement(plantilla);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()){
            LocalDate fecha = LocalDate.parse(resultado.getString("fecha_lanzamiento"));
            j = new Juego(resultado.getString("nombre"),resultado.getString("empresa"),fecha,resultado.getInt("id_juego"));
            listaJuegos.add(j);
        }
        sentencia.close();
        return listaJuegos;
    }

}
