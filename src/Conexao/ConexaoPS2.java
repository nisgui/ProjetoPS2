/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

/**
 *
 * @author nicole
 */

import java.sql.Connection;
import java.sql.DriverManager;
public class ConexaoPS2 {
    
     public static Connection getConnection() {
        try {
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String url = "jdbc:derby://localhost:1527/projetoPS2";
            String usuario = "projeto";
            String senha = "projeto";
            
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
