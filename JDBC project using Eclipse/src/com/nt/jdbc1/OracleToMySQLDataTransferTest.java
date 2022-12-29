package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySQLDataTransferTest {
	private static final String MYSQL_INSERT_STUDENT="INSERT INTO  STUDENT (SNAME,SADD,AVG) VALUES (?,?,?)";
	private static final String ORACLE_SELECT_STUDENT="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
        public static void main(String[] args) {
	   Connection oraCon=null,mysqlCon=null;
	   Statement st=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   
       try {
		//register JDBC driver
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
    	   Class.forName("com.mysql.cj.jdbc.Driver");
    	   
    	   //establish the connection
    	   oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
    	   mysqlCon=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
    	   
    	   //create Statement obj
    	   if(oraCon!=null) {
    		   st=oraCon.createStatement();
    	   }
    	   if(mysqlCon!=null) {
    		   ps=mysqlCon.prepareStatement(MYSQL_INSERT_STUDENT);
    	   }
    	   
    	   //send and execute select query in oracle sb s/w and get resultset object
    	   if(st!=null) {
    		   rs=st.executeQuery(ORACLE_SELECT_STUDENT);
    	   }
    	   
    	   //GATHER each record of result set object and insert into mysql db table
    	   if(rs!=null && ps!=null) {
    		   while(rs.next()) {
    			   //gether each record from rs
    			   int no=rs.getInt(1);
    			   String name=rs.getString(2);
    			   String addrs=rs.getString(3);
    			   float avg=rs.getFloat(4);
    			   //set each record value as insert query param value to insert to mysql db table
    			
    			   ps.setString(1,name);
    			   ps.setString(2, addrs);
    			   ps.setFloat(3, avg);
    			   //execute the query
    			   ps.executeUpdate();
    			   
    		   }//while
    		   System.out.println("RECORDS ARE COPIED FROM ORACLE DB TABLE TO MYSQL DB TABLE SUCCESSFULLY");
    	   }//IF
	  }//try
       catch(SQLException se) {
    	   se.printStackTrace();
    	   System.out.println("RECORDS ARE NOT COPIED FROM ORACLE DB TABLE TO MYSQL DB TABLE SUCCESSFULLY");

       }catch(Exception e) {
    	   e.printStackTrace();
    	   System.out.println("PROBLEM IN APP EXECUTION");
       }finally {
    	   //close jdbc objs
    	   try {
    		   if(rs!=null) {
    			   rs.close();
    		   }
    	   }catch(SQLException se){
    			   se.printStackTrace();
    		   }
    	   
    	   try {
    		   if(st!=null) {
    			   st.close();
    		   }
    	   }catch(SQLException se){
    			   se.printStackTrace();
    		   }
    	   
    	   try {
    		   if(ps!=null) {
    			   ps.close();
    		   }
    	   }catch(SQLException se){
    			   se.printStackTrace();
    		   }
    	   
    	   try {
    		   if(mysqlCon!=null) {
    			   mysqlCon.close();
    		   }
    	   }catch(SQLException se){
    			   se.printStackTrace();
    		   }
    	   
       
       try {
		   if(oraCon!=null) {
			   oraCon.close();
		   }
	   }catch(SQLException se){
			   se.printStackTrace();
		   }
       
     }//finally
}//main
}//class
