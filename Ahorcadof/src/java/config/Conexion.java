/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexion {
 
    Connection conexion;
 
    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/DB_Ahorcado?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "quintom";
            String password = "admin";
            conexion = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException error) {
            StackTraceElement elemento = error.getStackTrace()[0];
            System.out.println("Error en: " + elemento.getClassName() + " linea " + elemento.getLineNumber());
            System.out.println("Mensaje: " + error.getMessage());
            error.printStackTrace();
        } catch (SQLException error) {
            StackTraceElement elemento = error.getStackTrace()[0];
            System.out.println("Error en: " + elemento.getClassName() + " linea " + elemento.getLineNumber());
            System.out.println("Mensaje: " + error.getMessage());
            error.printStackTrace();
        }
        return conexion;
 
    }
 
}
