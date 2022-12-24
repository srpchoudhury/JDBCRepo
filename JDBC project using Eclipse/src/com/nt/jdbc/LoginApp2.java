package com.nt.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginApp2 {

	public static void main(String[] args) {
		Console cons=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			cons=System.console();//java.util.Console does not work in eclipse ide
			//read inputs
			String user=null;
			String pwd=null;
			
			if(cons!=null) {
				System.out.print("Enter Login UserName :: ");
				user=cons.readLine();
				System.out.print("Enter Login pwd :: ");
			    pwd=new String(cons.readPassword());
			}//if
			//convert input value as required for the SQL query
			user="'"+user+"'";
			pwd="'"+pwd+"'";
			
			//load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			//create jdbc statement object
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare sql query
			//SQL> select count(*) from irctc_tab where uname='KANHA' and pwd='SHILPA';
			String query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+user+"AND PWD="+pwd;
			System.out.println(query);
			
			
			//send and execute the sql query in db s/w
			if(st!=null) {
				rs=st.executeQuery(query);
				//process the resultSet object
			}
			
			if(rs!=null) {
				rs.next();//moves the crosser to first record tp BFR
				int count=rs.getInt(1);//first column value of the first record
				//process the result
				if(count==0)
					System.out.println("INVALID CREDENTIALS");
				else
					System.out.println("VALID CREDENTIALS");
			}//if
		}//try
		
       catch(SQLException se) {
    	   se.printStackTrace();
       }catch(Exception e) {
    	   e.printStackTrace();
       }finally {
    	   try {
    		   //close jdbc objs
    		   if(rs!=null) {
    			   rs.close();
    		   }
    	   }catch(SQLException se) {
    		   se.printStackTrace();
    	   }
    	  try { 
    	   if(st!=null) {
			   st.close();
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
    	  
	   
      
    	   
       }//finally
	}//main

}//class
