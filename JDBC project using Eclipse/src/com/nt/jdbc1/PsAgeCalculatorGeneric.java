package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * common for all database software
 */

public class PsAgeCalculatorGeneric {
	private static final String GET_DOB="SELECT DOB FROM PERSON_INFO_DATES WHERE PID=?";
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
//		  // load JDBC driver class
//		   Class.forName("com.mysql.cj.jdbc.Driver");
//		   
//		   //establish the connection
//		   con=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
//		   
		   
		   //load jdbc driver class
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
		   
		   //create jdbc preparedStatement obj having pre-compiled sql query
		   if(con!=null) {
		   ps=con.prepareStatement(GET_DOB);
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
				  java.sql.Date sqdob=rs.getDate(1);
				  java.util.Date sysDate=new java.util.Date();
				  float age=(sysDate.getTime()-sqdob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
				 /* DecimalFormat df=new DecimalFormat();
				  df.setMaximumFractionDigits(2);*/
				 // DecimalFormat df=new DecimalFormat("#.###");
				  DecimalFormat df=new DecimalFormat("#.##");
				  
				  System.out.println("Person age :: "+df.format(age));
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
	   }//finally
   }//main
}//class
