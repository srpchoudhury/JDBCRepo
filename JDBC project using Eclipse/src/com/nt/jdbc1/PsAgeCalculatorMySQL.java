package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * MYSQL> SELECT ROUND(TIMESTAMPDIFF(DAY,DOB,CURDATE())/365.25,2) FROM PERSON_INFO_DATES WHERE PID=?;
 */

public class PsAgeCalculatorMySQL {
	private static final String AGE_CALCULATOR="SELECT ROUND(TIMESTAMPDIFF(DAY,DOB,CURDATE())/365.25,2) FROM PERSON_INFO_DATES WHERE PID=?";
   public static void main(String[] args) {
	 //read inputs
	   Scanner sc=null;
	   Connection con=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   try {
		   sc=new Scanner(System.in);
		   int pid=0;
		   if(sc!=null) {
			   System.out.print("Enter Person id :: ");
			   pid=sc.nextInt();
		   }
		   //load jdbc driver class
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
		   
		   //create jdbc preparedStatement obj having pre-compiled sql query
		   if(con!=null) {
		   ps=con.prepareStatement(AGE_CALCULATOR);
		   }
		   
		   //set value to query parameter
		   if(ps!=null) {
			   ps.setInt(1, pid);
		   }
		   
		   //execute the query
		   if(ps!=null) {
			   rs=ps.executeQuery();
		   }
		   
		   //process the resultSet
		   
		   if(rs!=null) {
			   if(rs.next()) {
				  float age=rs.getFloat(1);
				  System.out.println("Perso age is :: "+age);
				   
			   }else {
				   System.out.println("PERSON RECORD NOT FOUND");
			   }
		   }//if
		 }//try
	   catch(SQLException se) {
		   se.printStackTrace();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }finally {
		   //close jdbc obj
		   try {
			   if(rs!=null) {
				   rs.close();
			   }
		   }catch(SQLException se) {
			   se.printStackTrace();
		   }
		   
		   try {
			   if(ps!=null) {
				   ps.close();
			   }
		   }catch(SQLException se) {
			   se.printStackTrace();
		   }
		   
		   try {
			   if(con!=null) {
				   con.close();
			   }
		   }catch(SQLException se) {
			   se.printStackTrace();
		   }
		   
		   try {
			   if(sc!=null) {
				   sc.close();
			   }
		   }catch(Exception se) {
			   se.printStackTrace();
		   }
	   }//finaly
   }//main
}//class
