package modelo;

import config.Conexion;
import java.sql.*;

public class PalabraDAO {   
    public Palabra obtenerPalabraAleatoria() {
        Palabra palabra = null;
        String sql = "{CALL sp_ObtenerPalabraAleatoria()}";

        try (Connection con = new Conexion().Conexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                palabra = new Palabra();
                palabra.setCodigoPalabra(rs.getInt("codigoPalabra"));
                palabra.setPalabra(rs.getString("palabra"));
                palabra.setPista1(rs.getString("pista1"));
                palabra.setPista2(rs.getString("pista2"));
                palabra.setPista3(rs.getString("pista3"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener palabra: " + e.getMessage());
            e.printStackTrace();
        }
        return palabra;
    }
}