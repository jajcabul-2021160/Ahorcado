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

    public Usuario validar(String correo, String contraseña) {
        Usuario usuario = new Usuario();
        String sql = "SELECT codigoUsuario, correoUsuario, contraseñaUsuario FROM Usuario WHERE correoUsuario = ? AND contraseñaUsuario = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setCodigoUsuario(rs.getInt("codigoUsuario"));
                usuario.setCorreoUsuario(rs.getString("correoUsuario"));
                usuario.setContraseñaUsuario(rs.getString("contraseñaUsuario"));
            }
        } catch (Exception e) {
            System.out.println("Error en validar usuario");
            e.printStackTrace();
        }
        return usuario;
    }
}