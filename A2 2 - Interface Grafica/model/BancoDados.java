/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultrabusinessmegatop.view.Main;
/**
 *
 * @author lcarl
 */
public class BancoDados {
    
    public static Connection conn;
    
    public static Connection createConnection(){
        if (conn==null){
              
            try {
                Class.forName(DBStrings.DRIVER);

                conn = DriverManager.getConnection(
                         DBStrings.URL,
                         DBStrings.USER,
                         DBStrings.PASSWORD
                );                        
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return conn;
    }
    
}
