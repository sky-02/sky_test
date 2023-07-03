/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Dao;

import Evote.Dbutil.DBConnection;
import Evote.Dto.CandidateInfo;
import Evote.Dto.VotingDto;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author RC
 */
public class VotingDao {
      private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8;
    static{
        try{
    ps1=DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
    ps2=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from user_details,candidate where user_details.adhar_no=candidate.userid and candidate_id=?");
    ps3=DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
    ps4=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from user_details,candidate where user_details.adhar_no=candidate.userid and candidate.city=(select city from user_Details where adhar_no=?)");
    ps5=DBConnection.getConnection().prepareStatement("select candidate_id,count(*) as vote from voting group by candidate_id order by 2 desc"); 
    ps6= DBConnection.getConnection().prepareStatement("select count(*)from voting");
    ps7=DBConnection.getConnection().prepareStatement("select party,count(*) as vote from candidate,voting where voting.candidate_id=candidate.candidate_id group by party order by 2 desc");
    ps8=DBConnection.getConnection().prepareStatement("select gender,count(*) as vote from user_Details,voting where voting.voter_id=user_Details.adhar_no group by gender order by 2 desc");
        }
        catch(SQLException sql){
            System.out.println(sql);
        }
}
    public static String getCandidateId(String vid)throws SQLException{
        ps1.setString(1, vid);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }

public static CandidateInfo getVote(String cid)throws Exception{
ps2.setString(1, cid);
 ResultSet rs=ps2.executeQuery();
 CandidateInfo c=new CandidateInfo();
 byte[]buffer;
 ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
      int byteRead;
      byte[]img;
      String base64img;     
if(rs.next()){
    c.setCandidate_id(rs.getString(1));
    c.setUsername(rs.getString(2));
    c.setParty(rs.getString(3));
    Blob b=rs.getBlob(4);
    InputStream in=b.getBinaryStream();
    buffer =new byte[24096];//creating byte array step 3
          byteRead=-1;
          while((byteRead=in.read(buffer))!=-1){//step 4
              outputStream.write(buffer,0, byteRead);//step 5
          }
          img=outputStream.toByteArray();//step 6
          Base64.Encoder en=Base64.getEncoder();//creating encoder object
          base64img=en.encodeToString(img);//by calling encodeToString return string form of img array
          c.setSymbol(base64img);
}
return c;
}
public static ArrayList<CandidateInfo> getDetails(String uid)throws Exception{
ps4.setString(1, uid);
 ResultSet rs=ps4.executeQuery();  
 ArrayList<CandidateInfo>candidate=new ArrayList();
while(rs.next()){
 byte[]buffer;
 ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
    int byteRead;
    byte[]img;
    String base64img; 
    CandidateInfo c=new CandidateInfo();
    c.setCandidate_id(rs.getString(1));
    c.setUsername(rs.getString(2));
    c.setParty(rs.getString(3));
    Blob b=rs.getBlob(4);
    InputStream in=b.getBinaryStream();
    buffer =new byte[24096];//creating byte array step 3
          byteRead=-1;
          while((byteRead=in.read(buffer))!=-1){//step 4
              outputStream.write(buffer,0, byteRead);//step 5
          }
          img=outputStream.toByteArray();//step 6
          Base64.Encoder en=Base64.getEncoder();//creating encoder object
          base64img=en.encodeToString(img);//by calling encodeToString return string form of img array
           c.setCandidate_id(rs.getString(1));
           c.setUsername(rs.getString(2));
           c.setParty(rs.getString(3));
           c.setSymbol(base64img);
           candidate.add(c);
}
return candidate;
}
public static boolean addVote(VotingDto v)throws SQLException{
ps3.setString(1,v.getCandidateID());
ps3.setString(2,v.getVoterId());
return ps3.executeUpdate()==1;
}

public static Map<String,Integer> getResult()throws SQLException{
  ResultSet rs=ps5.executeQuery();
  Map<String,Integer> vote=new LinkedHashMap<>();
  while(rs.next()){
      vote.put(rs.getString(1),rs.getInt(2));
  }
  return vote;
}
public static int getVoteCount()throws SQLException{
  ResultSet rs=ps6.executeQuery();
  if(rs.next()){
     return rs.getInt(1);
  }
  return 0;
}
public static Map<String,Integer> getPartyResult()throws SQLException{
  ResultSet rs=ps7.executeQuery();
  Map<String,Integer> vote=new LinkedHashMap<>();
  while(rs.next()){
      vote.put(rs.getString(1),rs.getInt(2));
  }
  return vote;
}
public static Map<String,Integer> getGenderResult()throws SQLException{
  ResultSet rs=ps8.executeQuery();
  Map<String,Integer> vote=new LinkedHashMap<>();
  while(rs.next()){
      vote.put(rs.getString(1),rs.getInt(2));
  }
  return vote;
}
}


