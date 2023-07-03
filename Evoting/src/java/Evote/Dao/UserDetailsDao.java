/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Dao;

import Evote.Dbutil.DBConnection;
import Evote.Dto.UserPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RC
 */
public class UserDetailsDao {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5;
    static{
        try{
    ps=DBConnection.getConnection().prepareStatement("select * from user_Details where adhar_no=?");
    ps1=DBConnection.getConnection().prepareStatement("insert into user_Details values(?,?,?,?,?,?,?,?,?)");
    ps2=DBConnection.getConnection().prepareStatement("select * from  user_Details where user_type='Voter'");
    ps3=DBConnection.getConnection().prepareStatement("delete from user_details  where adhar_no=?");
    ps4=DBConnection.getConnection().prepareStatement("select adhar_no from user_details where user_type='Voter'");
    ps5=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=? and user_type='Voter'");
        }
        catch(SQLException sql){
            System.out.println(sql);
        }
}
    public static boolean search(String id)throws SQLException{
        ps.setString(1,id);
        return ps.executeQuery().next();
    }
    public static boolean register(UserPojo u)throws SQLException{
        ps1.setString(1,u.getAdhar_no());
        ps1.setString(2,u.getPassword());
        ps1.setString(3,u.getUsername());
        ps1.setString(4,u.getAddress());
        ps1.setString(5,u.getCity());
        ps1.setString(6,u.getEmail());
        ps1.setString(7,u.getMno());
        ps1.setString(8,"Voter");
        ps1.setString(9,u.getGender());
        int i=ps1.executeUpdate();
        if(i==1)
            return true;
        return false;
    }
    public static ArrayList<UserPojo>showUsers()throws SQLException{
        ArrayList<UserPojo>list=new ArrayList();
        ResultSet rs=ps2.executeQuery();
        while(rs.next()){
            UserPojo user=new UserPojo();
            user.setAdhar_no(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMno(rs.getString(7));
            user.setGender(rs.getString(9));
            list.add(user);
        }
        return list;
    }
    public static ArrayList<String>getAllUserId()throws SQLException{
       ResultSet rs=ps4.executeQuery();
       ArrayList<String>list=new ArrayList<>();
       while(rs.next()){
           list.add(rs.getString(1));
       }
       return list;
    }
    public static UserPojo showUserDetails(String uid)throws SQLException{
        //ArrayList<UserPojo>list=new ArrayList();
        ps5.setString(1, uid);
        ResultSet rs=ps5.executeQuery();
        UserPojo user=new UserPojo();
        if(rs.next()){
            
            user.setAdhar_no(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMno(rs.getString(7));
            
        }
        return user;
    }
    public static boolean deleteUser(String uid)throws SQLException{
        ps3.setString(1, uid);
        return ps3.executeUpdate()==1;
    }
}
