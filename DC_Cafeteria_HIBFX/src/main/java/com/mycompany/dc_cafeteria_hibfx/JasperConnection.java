package com.mycompany.dc_cafeteria_hibfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doctor32
 */
public class JasperConnection {
    private static Connection conexion;
    
    static {
        String url="jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user="root";
        String pass="";
        
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión realizada con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(JasperConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
    
}
