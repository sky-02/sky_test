/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author RC
 */
public class DBConnection {
     public static Connection conn=null;
    static{
        try{
        Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("driver loaded");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LOCALHOST:1521/xe", "vote","vote");       
                }
        catch(SQLException ex){ 
            System.out.println(ex);
             System.exit(1);
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);     
        }
    }
    public static Connection getConnection(){
        return conn;
    }
     public static void closeConnection(){
        try{
            if(conn!=null){
                conn.close();
            }
        }
        catch(SQLException ex){
             JOptionPane.showMessageDialog(null,"cant close connection","ERROR",JOptionPane.ERROR_MESSAGE);       
        }
    }
}
