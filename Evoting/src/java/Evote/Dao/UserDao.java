/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Dao;

import Evote.Dbutil.DBConnection;
import Evote.Dto.LoginPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RC
 */
public class UserDao {
     private static PreparedStatement ps;
    static{
        try{
    ps=DBConnection.getConnection().prepareStatement("select user_type from user_Details where adhar_no=? and password=?");
        }
        catch(SQLException sql){
            System.out.println(sql);
        }
}
    public static String validateUSer(LoginPojo login)throws SQLException{
      ps.setString(1,login.getUserid());
      ps.setString(2,login.getPassword());
      ResultSet rs=ps.executeQuery();
      if(rs.next()){
        return rs.getString(1);
      }
      return null;
    }
}
