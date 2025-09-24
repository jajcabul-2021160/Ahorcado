/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario validar(String correo_Usuario, String contraseña_Usuario) {
        Usuario usuario = new Usuario();
        String sql = "SELECT codigo_Usuario, correo_Usuario, contraseña_Usuario FROM Usuarios WHERE correo_Usuario = ? AND contraseña_Usuario = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo_Usuario);
            ps.setString(2, contraseña_Usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setCodigo_Usuario(rs.getInt("codigo_Usuario"));
                usuario.setCorreo_Usuario(rs.getString("correo_Usuario"));
                usuario.setContraseña_Usuario(rs.getString("contraseña_Usuario"));
            }
        } catch (Exception e) {
            System.out.println("Error en validar usuario");
            e.printStackTrace();
        }
        return usuario;
    }
}
