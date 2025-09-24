/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Usuario {

    private int codigo_Usuario;
    private String correo_Usuario;
    private String contraseña_Usuario;

    public Usuario() {
    }

    public Usuario(int codigo_Usuario, String correo_Usuario, String contraseña_Usuario) {
        this.codigo_Usuario = codigo_Usuario;
        this.correo_Usuario = correo_Usuario;
        this.contraseña_Usuario = contraseña_Usuario;
    }

    public int getCodigo_Usuario() {
        return codigo_Usuario;
    }

    public void setCodigo_Usuario(int codigo_Usuario) {
        this.codigo_Usuario = codigo_Usuario;
    }

    public String getCorreo_Usuario() {
        return correo_Usuario;
    }

    public void setCorreo_Usuario(String correo_Usuario) {
        this.correo_Usuario = correo_Usuario;
    }

    public String getContraseña_Usuario() {
        return contraseña_Usuario;
    }

    public void setContraseña_Usuario(String contraseña_Usuario) {
        this.contraseña_Usuario = contraseña_Usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + 
               "codigo_Usuario=" + codigo_Usuario + 
               ", correo_Usuario=" + correo_Usuario + 
               ", contraseña_Usuario=" + contraseña_Usuario + '}';
    }
}
