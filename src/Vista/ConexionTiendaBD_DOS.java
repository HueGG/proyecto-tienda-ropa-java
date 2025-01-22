/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS-TUF
 */
public class ConexionTiendaBD_DOS {
    //Conexion con la Base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_tienda?autoReconnet=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$MySQL_20&22.";
    
    /**Método para conexión a base de datos
     * 
     */
    
    protected Connection getConnection(){
        Connection nuevaConexion = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver"); SOLO PARA MySQL 8.
            nuevaConexion = (Connection) DriverManager.getConnection(URL, USERNAME,PASSWORD); // Se estable conexión con la BD
            //JOptionPane.showMessageDialog(null, "Conexión exitosa");
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return nuevaConexion;
    }
}
