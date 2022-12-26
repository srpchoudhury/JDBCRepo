package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp {
	private static final String LOGIN_QUERY="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
	
	
		Scanner sc=null;
		Connection con=null;

        PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			//read inputs
			String user=null;
			String pwd=null;
			
			if(sc!=null) {
				System.out.print("Enter Login UserName :: ");
				user=sc.nextLine();
				System.out.print("Enter Login pwd :: ");
			    pwd=sc.nextLine();
			}//if
			
			
			//load the jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			//create jdbc Preparedstatement object
			if(con!=null) {
				ps=con.prepareStatement(LOGIN_QUERY);
			}
			
			//set values to the params of precompiled sql query
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pwd);
			}
			
			//send and execute the sql query in db s/w
			if(ps!=null) {
				rs=ps.executeQuery();
				//process the resultSet object
			}
			
			if(rs!=null) {
				rs.next();//moves the crosser to first record to BFR
				
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
    	   
       }
	}

}
