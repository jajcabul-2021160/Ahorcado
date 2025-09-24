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
                palabra.setCodigo_Palabra(rs.getInt("codigo_Palabra"));
                palabra.setPalabra(rs.getString("palabra"));
                palabra.setPista_1(rs.getString("pista_1"));
                palabra.setPista_2(rs.getString("pista_2"));
                palabra.setPista_3(rs.getString("pista_3"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener palabra: " + e.getMessage());
            e.printStackTrace();
        }
        return palabra;
    }
}